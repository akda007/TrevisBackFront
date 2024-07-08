const inputLogin = document.getElementById("input_login");
const inputPassword = document.getElementById("input_password");
const loginButton = document.getElementById("login_button");
const errorMessage = document.querySelector(".error-modal")

function showError(text) {
    errorMessage.innerHTML = `<p>${text}</p>`;
    errorMessage.style.opacity = "100%";

    setTimeout(() => {
        errorMessage.style.opacity = "0";
    }, 4000);
}

const userLogin = (login, pass) => {
    const reqBody = {
        username: login,
        passord: pass
    }

    let res = undefined;

    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(reqBody)
    }).then(x => {
        return x.json()
    }).then(x => {
        res = x;
    }).catch(x => {
        showError(x.message)
    });

    if (res == undefined) {
        return;
    }

    localStorage.setItem("sessionInfo", JSON.stringify(res));

    if (res.user.firstLogin == true) {
        window.location = "../updatePassword"
    } else {
        window.location = "../mainPage"
    }
}

loginButton.addEventListener("click", () => {
    userLogin(inputLogin.value, inputPassword.value);
});