const sessionInfo = JSON.parse(localStorage.getItem("sessionInfo"));

const username1 = document.getElementById("username1");
const username2 = document.getElementById("username2");
const signoutBt = document.getElementById("signoutButton");



document.addEventListener("DOMContentLoaded", () => {
    username1.innerText = sessionInfo.user.username;
    username2.innerText = sessionInfo.user.username;
});

