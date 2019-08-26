function removeErrorText(form) {
    var errors = form.getElementsByClassName("error-message");
    for (var i = 0; i < errors.length; i++) {
        errors[i].remove()
    }
}