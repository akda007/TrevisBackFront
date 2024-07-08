var buttons = document.querySelectorAll(".edit-buttons");

buttons.forEach((button) => {
    button.addEventListener('click', () => {
        if(button.getAttribute("name") === "edit") {
            window.location.href = "registerService.html"
        } else if(button.getAttribute("name") === "delete") {
            
        }
    })
})