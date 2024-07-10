document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#signoutButton").addEventListener("click", () => {
        localStorage.removeItem("sessionInfo")
        window.location = "../login"
    });
})