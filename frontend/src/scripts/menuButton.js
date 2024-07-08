var menuButton = document.getElementById("menu-button");
var menu = document.getElementById("menu");

menuButton.addEventListener('click', () => {
    if (menu.style.display === "none") {
        menu.style.display = "flex";
    } else {
        menu.style.display = "none"
    }
})