function validate(form) {

    removeErrorText(form);

    if (validateEmail(form) && validateLogin(form) && validateName(form) &&
        validatePassword(form) && validateCheckBox(form)) {
        form.submit();
    }
}

function validateEmail(form) {
    removeErrorText(form);

    var email = $("input[name='E-mail']");
    var regExpEmail = /^[A-Za-z][a-z0-9_]{3,}@[a-z]{2,}\.[a-z]{2,}/;

    if (!email.val()) {
        email.after('<span class="error-message">E-mail is empty!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else if (!regExpEmail.test(email.val())) {
        email.after('<span class="error-message">E-mail does not match the pattern!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else {
        return true;
    }
}

function validateLogin(form) {
    removeErrorText(form);

    var login = $("input[name='Login']");
    var regExpLogin = /^([a-zA-Zа-яА-Я][a-zA-Zа-яА-Я0-9_\-]{2,}[a-zA-Zа-яА-Я0-9])$/;

    if (!login.val()) {
        login.after('<span class="error-message">Login is empty!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else if (!regExpLogin.test(login.val())) {
        login.after('<span class="error-message">Login does not match the pattern!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else {
        return true;
    }
}

function validateName(form) {
    removeErrorText(form);

    var name = $("input[name='Name']");
    var regExpName = /^([A-ZА-Я][a-zа-я]+[A-ZА-Я\-]*[a-zа-я]+)(\s)([A-ZА-Я][a-zа-я]+[A-ZА-Я\-]*[a-zа-я]+)/;

    if (!name.val()) {
        name.after('<span class="error-message">Name is empty!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else if (!regExpName.test(name.val())) {
        name.after('<span class="error-message">Name does not match the pattern!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else {
        return true;
    }
}

function validatePassword(form) {
    removeErrorText(form);

    var password = $("input[name='Password']");
    var confirmPassword = $("input[name='Confirm Password']");
    var regExpPass =  /^(?=.*?[A-ZА-Я])(?=.*?[a-zа-я])(?=.*?[0-9])(?=.*?[-_]).{8,}$/;

    if (!password.val()) {
        password.after('<span class="error-message">Password is empty!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else if (!regExpPass.test(password.val())) {
        password.after('<span class="error-message">Password does not match the pattern!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else if (password.val() != confirmPassword.val()) {
        confirmPassword.after('<span class="error-message">Passwords do not match each other!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else {
        return true;
    }
}

function validateCheckBox(form) {
    removeErrorText(form);

    var checkbox = $("input[name='Terms of use']");

    var subBut = $("input[name='Sign Up']");

    if (!checkbox.is(':checked')) {
        subBut.before('<span class="error-message">You have to agree Terms of use before Sign Up!</span>');
        $(".error-message").css("color", "red");
        return false;
    } else {
        return true;
    }
}