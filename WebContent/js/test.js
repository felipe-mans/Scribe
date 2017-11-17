/**
 * 
 */
var popupBox = document.getElementById('formContent');

// Get the button that opens the pop up Box
var login = document.getElementById('loginButton');

// Get the <span> element that closes the modal
//var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal 
login.onclick = function() {
    popupBox.style.width = "50%";
}

// When the user clicks on <span> (x), close the modal
//span.onclick = function() {
  //  modal.style.display = "none";
//}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == popupBox) {
        popupBox.style.display = "none";
    }
}