
function showError(value) {
    let toastInfo = {
        text: "",
        duration: 3000,
        gravity: "top", // `top` or `bottom`
        position: "center", // `left`, `center` or `right`
        stopOnFocus: true, // Prevents dismissing of toast on hover
        style: {
          background: "linear-gradient(to right, #ff8177 0%, #ff867a 0%, #ff8c7f 21%, #f99185 52%, #cf556c 78%, #b12a5b 100%)",
        },
        onClick: function(){} // Callback after click
      }
      
    switch(value) {
        case 401:
            toastInfo.text = "Invalid username!"
            break;
        
        case 403:
            toastInfo.text = "Invalid password!"
            break;

        default:
            toastInfo.text = value
    }

    Toastify(toastInfo).showToast();
}

