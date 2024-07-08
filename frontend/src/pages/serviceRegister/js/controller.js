const session = JSON.parse(localStorage.getItem("sessionInfo"));

const nameInput = document.querySelector("#username")
const internInput = document.querySelector("#intern")
const descriptionInput = document.querySelector("#description")
const submitBt = document.querySelector("#submit_bt")


//Todo: finish implementation
const register = async (name, intern, desc) => {
    const res = fetch("http://")
}

submitBt.addEventListener("click", () => {
    register(nameInput.value, internInput.value, descriptionInput.value)
});