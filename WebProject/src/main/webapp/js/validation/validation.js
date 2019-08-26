function showError(container, errorMessage) {
  container.className = 'error';
  var msgElem = document.createElement('span');
  msgElem.style.color = 'red';
  msgElem.className = "error-message";
  msgElem.innerHTML = errorMessage;
  container.appendChild(msgElem);
}

function validate(form) {

  removeErrorText(form);

  var fields = form.getElementsByClassName("field");

  var regExpLogin = /^([a-zA-Zа-яА-Я][a-zA-Zа-яА-Я0-9_\-]{2,}[a-zA-Zа-яА-Я0-9])$/;
  var regExpPass =  /^(?=.*?[A-ZА-Я])(?=.*?[a-zа-я])(?=.*?[0-9])(?=.*?[-_]).{8,}$/;
  var regExpConfPass = /^([a-zA-Zа-яА-Я0-9][a-zA-Zа-яА-Я0-9_\-]{2,}[a-zA-Zа-яА-Я0-9])$/;
  var regExpName = /^([A-ZА-Я][a-zа-я]+[A-ZА-Я\-]*[a-zа-я]+)(\s)([A-ZА-Я][a-zа-я]+[A-ZА-Я\-]*[a-zа-я]+)/;
  var regExpEmail = /^[A-Za-z][a-z0-9_]{3,}@[a-z]{2,}\.[a-z]{2,}/;
  var regExps = [regExpEmail, regExpLogin, regExpName, regExpPass, regExpConfPass];

  for (var i = 0; i < fields.length; i++) {
    if (!fields[i].value) {
      showError(fields[i].parentNode, fields[i].name + ' is empty!');
      return false;
    }

    if (fields[i].name == 'Confirm Password' && fields[i].value &&
      fields[i].value != fields[i - 1].value) {
      showError(fields[i].parentNode, ' Passwords do not match each other!');
      return false;
    }

    var result = regExps[i].test(fields[i].value);

    if (fields[i].value && !result) {
      showError(fields[i].parentNode, ' ' + fields[i].name + ' does not match the pattern!');
      return false;
    }
  }

  var checkbox = document.querySelector('input[type=checkbox]');

  if (checkbox.checked) {
    form.submit();
    return true;
  } else {
    showError(checkbox.parentNode, '<br> You have to agree with ' + checkbox.name + ' before Sign Up!');
    return false;
  }
}