var buttons = document.querySelectorAll("div.button-container");
console.log(buttons);

buttons.forEach((button) => {
    button.addEventListener('click', () => {
        if(button.getAttribute("name") === "register-user") {
            window.location.href = "registerUser.html";
        } else if(button.getAttribute("name") === "register-service") {
            window.location.href = "registerService.html";
        } else if(button.getAttribute("name") === "show-services") {
            window.location.href = "servicesTable.html";
        }
    })
})