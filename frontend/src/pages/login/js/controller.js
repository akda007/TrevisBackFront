import { showError } from "../../../scripts/infoToasts.js";

const inputLogin = document.getElementById("input_login");
const inputPassword = document.getElementById("input_password");
const loginButton = document.getElementById("login_button");
const errorMessage = document.querySelector(".error-modal")
const eyeIcon = document.querySelector(".input-container img")


const userLogin = async (login, pass) => {
    const reqBody = {
        username: login,
        password: pass
    }

    let res = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(reqBody)
    }).catch( x => {
        showError(null);
        return
    })

    let data = await res.json();

    if(res.status == 404) {
        showError("Username not found");
        return;
    }
    if(res.status == 401) {
        showError("Password incorrect");
        return;
    }
    

    localStorage.setItem("sessionInfo", JSON.stringify(data));

    if (data.user.firstLogin) {
        window.location = "../updatePassword"
    } else {
        window.location = "../mainPage"
    }
}


loginButton.addEventListener("click", () => {
    userLogin(inputLogin.value, inputPassword.value);
});


eyeIcon.addEventListener("click", () => {
    inputPassword.type = inputPassword.type === "password" ? "text" : "password"
})