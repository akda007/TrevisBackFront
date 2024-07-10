var passwordInput = document.getElementById("password-input");
const submitButton = document.getElementById("submit-button");

const session = JSON.parse(localStorage.getItem("sessionInfo"));

document.addEventListener("DOMContentLoaded", () => {
    if (session == null) {
        alert("You need to be logged to be able to do this!");
        window.location = "../login"
    }
})

const update = async (password) => {
    const res = await fetch(`http://localhost:8080/user/${session.user.id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${session.token}`
        },
        body: JSON.stringify({password: password})
    });

    if (res.status != 200) {
        showError("Password is too weak!");
        return;
    }

    const body = await res.json();

    window.location = "../mainPage"
}

submitButton.addEventListener("click", () => {
    const newPassword = passwordInput.value;

    let result = verifyPassword(newPassword);

    if (result <= 2) {
        return;
    }

    update(newPassword);
});

passwordInput.addEventListener("keyup", () => {
    const newPassword = passwordInput.value;

    let result = verifyPassword(newPassword);

    if (result == 0) 
        {
            document.querySelector(".progress-bar").style.width = "0%";
            document.getElementById("password-strength").textContent = "Weak";
        } 
        else if (result == 1) 
        {
            document.querySelector(".progress-bar").style.backgroundColor = "#ff5454";
            document.querySelector(".progress-bar").style.width = "25%";
            document.getElementById("password-strength").textContent = "Weak"; 
        } 
        else if (result == 2) 
        {
            document.querySelector(".progress-bar").style.backgroundColor = "#ff9654";
            document.querySelector(".progress-bar").style.width = "50%";
            document.getElementById("password-strength").textContent = "Medium";
        } 
        else if (result == 3) 
        {
            document.querySelector(".progress-bar").style.backgroundColor = "#ffd754";
            document.querySelector(".progress-bar").style.width = "75%";
            document.getElementById("password-strength").textContent = "Medium";
        } 
        else if (result == 4) 
        {
            document.querySelector(".progress-bar").style.backgroundColor = "#71ff54";
            document.querySelector(".progress-bar").style.width = "100%";
            document.getElementById("password-strength").textContent = "Strong";
        }
})


function verifyPassword(password) {
    var count = 0;

    if(password.length > 7) 
        count++;

    if (hasUpperCase(password))
        count++;

    if (hasLowerCase(password)) 
        count++;

    if (hasDigit(password)) 
        count++;

    return count;
}

function hasUpperCase(password) {
    return password !== password.toLowerCase();
}

function hasLowerCase(password) {
    return password !== password.toUpperCase();
}

function hasDigit(password) {
    return /\d/.test(password);
}