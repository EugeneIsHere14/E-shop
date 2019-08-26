package com.epam.preproduction.koshevyi.servlet;

import com.epam.preproduction.koshevyi.captcha.entity.Captcha;
import com.epam.preproduction.koshevyi.captcha.strategy.CaptchaStrategy;
import com.epam.preproduction.koshevyi.dto.UserDto;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.service.UserService;
import com.epam.preproduction.koshevyi.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.epam.preproduction.koshevyi.constant.Constants.*;
import static java.util.Objects.nonNull;

/**
 * Servlet for user registration.
 */
@WebServlet("/registration")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30, maxRequestSize = 1024 * 1024 * 50)
public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationServlet.class);

    private static final String CAPTCHA_MAP_SIZE_MSG = "Captcha Map Size: {}";
    private static final String CAPTCHA_VALUE_MSG = "Captcha value:  {}";
    private static final String IO_EXCEPTION_MSG = "IO Exception: {}";
    private static final String SERVLET_EXCEPTION_MSG = "Servlet exception while file uploading: {}";
    private static final String FILE_WRITTEN_MSG = "File was written.";
    private static final String ACCOUNT_CREATED_MSG = "Account was successfully created!";
    private static final String ACCOUNT_CREATION_DENIED_MSG = "Account creation denied!";
    private static final String TIME_EXPIRED_MSG = "Time for registration is expired!";

    private UserService userService;
    private UserValidator userValidator;
    private Map<String, String> messageMap;
    private CaptchaStrategy captchaStrategy;
    private long expireTime;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE_CONTEXT_ATTR);
        userValidator = (UserValidator) config.getServletContext().getAttribute(USER_VALIDATOR_CONTEXT_ATTR);
        messageMap = userValidator.getErrorMessageMap();
        captchaStrategy = (CaptchaStrategy) config.getServletContext().getAttribute(CAPTCHA_STRATEGY_CONTEXT_ATTR);
        expireTime = (long) config.getServletContext().getAttribute(CAPTCHA_EXPIRE_TIME);

        LOGGER.info("Registration Servlet was initialized.");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        captchaStrategy.addCaptcha(req, resp);

        LOGGER.info(CAPTCHA_MAP_SIZE_MSG, captchaStrategy.getCaptchas().size());

        for (Map.Entry<String, String> pair : messageMap.entrySet()) {
            req.setAttribute(pair.getKey(), pair.getValue());
        }

        req.getRequestDispatcher(REGISTRATION_PAGE).forward(req, resp);

        req.getSession().removeAttribute(ALERT_EXPIRE_ATTR);
        req.getSession().removeAttribute(FALSE_LOGIN_ATTR);
        req.getSession().removeAttribute(FALSE_PASSWORD_ATTR);
        messageMap.remove(LOGIN_MISMATCH_ATTR);
        messageMap.remove(PASSWORD_MISMATCH_ATTR);
        messageMap.remove(CAPTCHA_ERR_ATTR);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Captcha captcha = captchaStrategy.getCaptcha(req);
        if (nonNull(captcha) && !(System.currentTimeMillis() - captcha.getCreationTime() > expireTime)) {
            messageMap.clear();

            UserDto userDTO = new UserDto(req.getParameter(USER_EMAIL), req.getParameter(USER_LOGIN),
                    req.getParameter(USER_NAME), req.getParameter(USER_PASSWORD),
                    req.getParameter(USER_CONFIRM_PASSWORD), req.getParameter(USER_SUBSCRIPTION1),
                    req.getParameter(USER_SUBSCRIPTION2));

            String captchaExpectedValue = captcha.getValue();
            String captchaActualValue = req.getParameter(TAG_CAPTCHA_INPUT_VALUE);

            LOGGER.info(CAPTCHA_MAP_SIZE_MSG, captchaStrategy.getCaptchas().size());

            for (Captcha c : captchaStrategy.getCaptchas().values()) {
                LOGGER.info(CAPTCHA_VALUE_MSG, c.getValue());
            }

            doValidation(userDTO, captchaExpectedValue, captchaActualValue, req, resp);
        } else {
            restoreFormInputValues(req);
            req.getSession().setAttribute(ALERT_EXPIRE_ATTR, TIME_EXPIRED_MSG);
            LOGGER.info(TIME_EXPIRED_MSG);

            resp.sendRedirect(REGISTRATION_SERVLET);
        }
    }

    private void doValidation(UserDto userDTO, String captchaExpectedValue, String captchaActualValue,
                              HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!userValidator.validateRegForm(userDTO, captchaExpectedValue, captchaActualValue)) {
            restoreFormInputValues(req);
            LOGGER.info(ACCOUNT_CREATION_DENIED_MSG);

            resp.sendRedirect(REGISTRATION_SERVLET);
        } else {
            try {
                uploadFile(req, userDTO.getLogin());
            } catch (ServletException e) {
                LOGGER.debug(SERVLET_EXCEPTION_MSG, e);
            }

            User newUser = userDTO.createNewUser();
            userService.addNewUser(newUser);
            LOGGER.info(ACCOUNT_CREATED_MSG);

            resp.sendRedirect(PAGE_MAIN);
        }
    }

    private void restoreFormInputValues(HttpServletRequest req) {
        messageMap.put(EMAIL_ATTR, String.valueOf(req.getParameter(USER_EMAIL)));
        messageMap.put(LOGIN_ATTR, String.valueOf(req.getParameter(USER_LOGIN)));
        messageMap.put(NAME_ATTR, String.valueOf(req.getParameter(USER_NAME)));

        if (nonNull(req.getParameter(USER_SUBSCRIPTION1))) {
            messageMap.put(SUBSCR1_ATTR, CHECKBOX_VALUE);
        }
        if (nonNull(req.getParameter(USER_SUBSCRIPTION2))) {
            messageMap.put(SUBSCR2_ATTR, CHECKBOX_VALUE);
        }
    }

    private void uploadFile(HttpServletRequest request, String login) throws IOException, ServletException {
        String applicationPath = request.getSession().getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;

        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }

        String fileName;

        for (Part part : request.getParts()) {
            fileName = extractFileName(part, login);
            try {
                if (!fileName.isEmpty() && fileName.length() > (login + "_").length()) {
                    part.write(uploadPath + File.separator + fileName);
                    LOGGER.info(FILE_WRITTEN_MSG);
                }
            } catch (IOException e) {
                LOGGER.debug(IO_EXCEPTION_MSG, e);
            }

        }
    }

    private String extractFileName(Part part, String login) {
        String fileName = "";
        String contentDisposition = part.getHeader("content-disposition");

        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.contains("filename=")) {
                fileName = login + "_" + item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return fileName;
    }
}