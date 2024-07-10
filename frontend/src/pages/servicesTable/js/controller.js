const session = JSON.parse(localStorage.getItem("sessionInfo"));
const dataList = document.getElementById("dataList");
const searchContainer = document.querySelector("#search-input");
const currentPage = document.querySelector("#currentPage");
const editName = document.querySelector("#edit-name")
const editDesc = document.querySelector("#edit-desc")
const editIntern = document.querySelector("#edit-intern")

let maxPage = 1;
let page = 1
let query = "";

let firstElement = true;
let editId;

const insertService = (service) => {
    const elementId = `collapse${service.id}`

    dataList.insertAdjacentHTML("beforeend", 
        `<div class="accordion-item" item-id="${service.id}" item-name="${service.name}" item-desc="${service.description}" item-intern="${service.intern}">
            <h2 class="accordion-header">
            <button class="accordion-button ${firstElement ? "" : "collapsed"}" type="button" data-bs-toggle="collapse" data-bs-target="#${elementId}" aria-expanded="${firstElement}" aria-controls="${elementId}">
                ${service.name}
            </button>
            </h2>
            <div id="${elementId}" class="accordion-collapse collapse ${firstElement ? "show" : ""}" data-bs-parent="#dataList">
            <div class="accordion-body">
                <p><strong>${service.name}</strong></p>
                <p>Description: ${service.description}</p>
                <p><strong>${service.manager.username}</strong></p>
                <p>${service.manager.department.name}</p>

                <button type="button" class="btn service-button edit" data-bs-toggle="modal" data-bs-target="#editModal" item-id="${service.id}">
                      <img class="edit-buttons" src="../../resources/images/edit.png" alt="" name="edit">
                </button>

                <button class="btn service-button delete" item-id="${service.id}"><img class="edit-buttons" src="../../resources/images/trash.png" alt="" name="delete" ></button>
            </div>
            </div>
        </div>`
    )

    firstElement = false;
}


const loadServiceList = async (page, query) => {
    dataList.innerHTML = "";
    firstElement = true
    
    const res = await fetch(`http://localhost:8080/service?page=${page}&size=10&query=${query}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${session.token}`,
            "Content-Type": "application/json"
        }
    })

    if (!res.ok) {
        alert("Unable to load data!");
        return;
    }

    const body = await res.json();

    maxPage = body.totalPages;

    currentPage.innerHTML = `1..${page}..${maxPage}`;

    Array.from(body.data).forEach(service => {
        insertService(service)
    });

    loadButtonEvents()
}

const deleteService = async (id) => {
    const res = await fetch("http://localhost:8080/service/" + id, {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${session.token}`,
            "Content-Type": "application/json"
        },
    })

    if (!res.ok) {
        alert("Unable to delete");
        return
    }

    loadServiceList(page, query);
}

document.addEventListener("DOMContentLoaded", () => {
    loadServiceList(page, "");
});

document.querySelector("#nextPage").addEventListener("click", () => {
    if (page == maxPage) {
        return;
    }
    loadServiceList(++page, query);
    
});
document.querySelector("#previousPage").addEventListener("click", () => {
    if (page == 1) {
        return;
    }
    loadServiceList(--page, query);
    
});

searchContainer.addEventListener("change", () => {
    query = searchContainer.value
    loadServiceList(page, query)
})

const loadButtonEvents = () => {
    Array.from(document.querySelectorAll(".service-button.edit")).forEach(x => {
        x.addEventListener("click", (e) => {
            e.stopPropagation()
            editId = e.currentTarget.getAttribute("item-id")

            const service = document.querySelector(`[item-id="${editId}"]`)

            editName.value = service.getAttribute("item-name");
            editDesc.value = service.getAttribute("item-desc")
            editIntern.checked = service.getAttribute("item-intern") === "true";
        })
    })

    Array.from(document.querySelectorAll(".service-button.delete")).forEach(x => {
        x.addEventListener("click", (e) => {
            deleteService(e.currentTarget.getAttribute("item-id"))
        })
    })
}

const editService = async (id, name, desc, intern) => {
    const res = await fetch("http://localhost:8080/service/" + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${session.token}`
        },
        body: JSON.stringify({
            name,
            description: desc,
            intern
        })

    })

    loadServiceList(page, query)
}

document.querySelector("#edit-submit").addEventListener("click", () => {
    

    editService(editId, name.value, editDesc.value, editIntern.checked)
});



