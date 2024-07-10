const inputLogin = document.getElementById("input_login");
const inputPassword = document.getElementById("input_password");
const loginButton = document.getElementById("login_button");
const errorMessage = document.querySelector(".error-modal")


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

    if (!res.ok) {
        showError(res.status);
        return;
    }

    let data = await res.json();

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