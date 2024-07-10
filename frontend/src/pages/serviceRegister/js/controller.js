import { showError, showMessage } from "../../../scripts/infoToasts.js";

const session = JSON.parse(localStorage.getItem("sessionInfo"));

const nameInput = document.querySelector("#username")
const internInput = document.querySelector("#intern")
const descriptionInput = document.querySelector("#description")
const submitBt = document.querySelector("#submit_bt")


//Todo: finish implementation
const register = async (name, intern, desc) => {
    const res = await fetch("http://localhost:8080/service", {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${session.token}`,
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name,
            description: desc,
            intern
        })
    })
    if (!res.ok) { 
        showError("Something went wrong")
        return;
    }
    showMessage("Service created!")
}

submitBt.addEventListener("click", () => {
    register(nameInput.value, internInput.checked, descriptionInput.value)
    nameInput.value = ""
    descriptionInput.value = ""
    nameInput.focus()
});