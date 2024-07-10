const session = JSON.parse(localStorage.getItem("sessionInfo"))
const inputUsername = document.getElementById("login")
const inputDepartment = document.getElementById("department")
const inputRole = document.getElementById("role")

const signup = async (username, department, role) => {
    const res = await fetch("http://localhost:8080/user", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${session.token}`
        },
        body: JSON.stringify({
            username: username,
            departmentId: department,
            role: role
        })
    })

    if (!res.ok) {
        showError(res.code)
        return;
    }

    window.location = "../mainPage"

}

const loadDepartments = async () => {
    const res = await fetch("http://localhost:8080/department", {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${session.token}`,
            "Content-Type": "application/json",
        }
    })

    const body = await res.json();

    Array.from(body).forEach(department => {
        inputDepartment.insertAdjacentHTML('beforeend',
            `<option value="${department.id}">${department.name}</option>`
        )
    });
}

document.addEventListener("DOMContentLoaded", () => {
    loadDepartments();
});

document.getElementById("submit_bt").addEventListener("click", () =>  {
    signup(inputUsername.value, Number(inputDepartment.value), Number(inputRole.value));
});