package com.epam.preproduction.koshevyi.validator;

import com.epam.preproduction.koshevyi.dto.UserDto;
import com.epam.preproduction.koshevyi.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * This class contains methods for validation all
 * input before user registration.
 */
public class UserValidator {

    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private static final String REGEXP_EMAIL = "^[A-Za-z][a-z0-9_]{3,}@[a-z]{2,}\\.[a-z]{2,}";
    private static final String REGEXP_LOGIN = "^([a-zA-Z\\u0430-\\u044F\\u0424-\\u042F][a-zA-Z0-9\\u0430-\\u044F\\u0424-\\u042F_\\-]{2,}[a-zA-Z0-9\\u0430-\\u044F\\u0424-\\u042F])$";
    private static final String REGEXP_NAME = "^([A-Z\\u0424-\\u042F][a-z\\u0430-\\u044F]+[A-Z\\u0424-\\u042F\\-]*[a-z\\u0430-\\u044F]+)(\\s)([A-Z\\u0424-\\u042F][a-z\\u0430-\\u044F]+[A-Z\\u0424-\\u042F\\-]*[a-z\\u0430-\\u044F]+)";
    private static final String REGEXP_PASSWORD = "^(?=.*?[A-Z\\u0424-\\u042F])(?=.*?[a-z\\u0430-\\u044F])(?=.*?[0-9])(?=.*?[-_]).{8,}";

    private static final String SUCCESSFUL_REGISTRATION = "Registration successful!";
    private static final String FAILED_REGISTRATION = "Registration failed!";
    private static final String PASSWORD_MISMATCH = "Passwords don't match each other!";
    private static final String INVALID_CAPTCHA = "Captcha is invalid!";
    private static final String LOGIN_EXIST = "User with this login exists!";

    private Map<String, String> errorMessageMap = new HashMap<>();
    private Map<String, Boolean> registrationValues = new HashMap<>();
    private UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    /**
     * Checks correctness of all data which is necessary
     * for user registration
     *
     * @param userDTO         an instance of UserDto class
     * @param captchaExpected user input value
     * @param captchaActual   actual captcha value
     * @return true if validateRegFormInputs(userDTO) &&
     * validateCaptcha(captchaExpected, captchaActual)
     * return true, else returns false
     */
    public boolean validateRegForm(UserDto userDTO, String captchaExpected, String captchaActual) {
        if (validateRegFormInputs(userDTO) && validateCaptcha(captchaExpected, captchaActual)) {
            LOGGER.info(SUCCESSFUL_REGISTRATION);
            return true;
        } else {
            LOGGER.info(FAILED_REGISTRATION);
            return false;
        }
    }

    /**
     * Checks user input.
     *
     * @param userDTO an instance of UserDto class
     * @return true if input is correct, otherwise returns false
     */
    private boolean validateRegFormInputs(UserDto userDTO) {
        boolean flag = true;

        registrationValues.put(USER_NAME_ATTR, userDTO.getEmail().matches(REGEXP_EMAIL));
        registrationValues.put(USER_LOGIN, userDTO.getLogin().matches(REGEXP_LOGIN));
        registrationValues.put(USER_NAME, userDTO.getName().matches(REGEXP_NAME));
        registrationValues.put(USER_PASSWORD, userDTO.getPassword().matches(REGEXP_PASSWORD));
        registrationValues.put(USER_CONFIRM_PASSWORD, userDTO.getConfirmPassword().matches(REGEXP_PASSWORD));

        for (Map.Entry<String, Boolean> pair : registrationValues.entrySet()) {
            if (!pair.getValue()) {
                flag = false;
                errorMessageMap.put(pair.getKey() + "error", pair.getKey() + " is incorrect!");
                LOGGER.info("{} is incorrect!", pair.getKey());
            }
        }

        if (Objects.nonNull(userService.findUserByLogin(userDTO.getLogin()))) {
            errorMessageMap.put(LOGIN_MISMATCH_ATTR, LOGIN_EXIST);
            LOGGER.info(LOGIN_EXIST);
            flag = false;
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            flag = false;
            LOGGER.info(PASSWORD_MISMATCH);
            errorMessageMap.put(PASSWORD_MISMATCH_ATTR, PASSWORD_MISMATCH);
        }

        return flag;
    }

    /**
     * Compares value from the captcha with value from the input field.
     *
     * @param captchaExpected user input value
     * @param captchaActual   actual captcha value
     * @return true if values are equal, else returns false
     */
    private boolean validateCaptcha(String captchaExpected, String captchaActual) {
        if (Objects.nonNull(captchaExpected) && captchaExpected.equals(captchaActual)) {
            return true;
        }
        errorMessageMap.put(CAPTCHA_ERR_ATTR, INVALID_CAPTCHA);
        LOGGER.info(INVALID_CAPTCHA);
        return false;
    }

    /**
     * Returns the map with error messages.
     *
     * @return the map with error messages
     */
    public Map<String, String> getErrorMessageMap() {
        return errorMessageMap;
    }
}