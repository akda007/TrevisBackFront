export function showError(value) {
    let toastInfo = {
        text: value,
        duration: 3000,
        gravity: "top", // `top` or `bottom`
        position: "center", // `left`, `center` or `right`
        stopOnFocus: true, // Prevents dismissing of toast on hover
        style: {
          background: "linear-gradient(to right, #ff8177 0%, #ff867a 0%, #ff8c7f 21%, #f99185 52%, #cf556c 78%, #b12a5b 100%)",
        },
        onClick: function(){} // Callback after click
    }
    Toastify(toastInfo).showToast();
}

export function showMessage(value) {
    let toastInfo = {
        text: value,
        duration: 3000,
        gravity: "top", // `top` or `bottom`
        position: "center", // `left`, `center` or `right`
        stopOnFocus: true, // Prevents dismissing of toast on hover
        style: {
          background: "linear-gradient(90deg, rgba(169,255,158,1) 0%, rgba(57,201,79,1) 51%, rgba(0,255,205,1) 100%)",
        },
        onClick: function(){} // Callback after click
    }
    Toastify(toastInfo).showToast();
}
