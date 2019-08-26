package com.epam.preproduction.koshevyi.servlet;

import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.service.UserService;
import com.epam.preproduction.koshevyi.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.epam.preproduction.koshevyi.constant.Constants.*;
import static java.util.Base64.getEncoder;

/**
 * Servlet for user login.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);

    private static final String LOGIN_FAILED_MSG = "Login failed";
    private static final String LOGIN_SUCCESSFUL_MSG = "Login successful";
    private static final String LOGIN_ERROR_MSG = "Login error!";
    private static final String PASSWORD_ERROR_MSG = "Password error!";
    private static final String LOGIN_PARAM = "LoginUp";
    private static final String PASSWORD_PARAM = "PasswordUp";
    private static final String LOGIN_VALUE = "LoginUpValue";

    private UserService userService;
    private Map<String, String> messageMap;

    @Override
    public void init(ServletConfig config) {
        userService = (UserService) config.getServletContext().getAttribute(USER_SERVICE_CONTEXT_ATTR);
        UserValidator userValidator = (UserValidator) config.getServletContext().getAttribute(USER_VALIDATOR_CONTEXT_ATTR);
        messageMap = userValidator.getErrorMessageMap();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Start LoginServlet doGet() method.");

        for (Map.Entry<String, String> pair : messageMap.entrySet()) {
            req.setAttribute(pair.getKey(), pair.getValue());
        }
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
        req.getSession().removeAttribute(FALSE_LOGIN_ATTR);
        req.getSession().removeAttribute(FALSE_PASSWORD_ATTR);

        LOGGER.info("End LoginServlet doGet() method.");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Start LoginServlet doPost() method.");

        HttpSession session = req.getSession();
        String login = req.getParameter(LOGIN_PARAM);
        String password = getEncoder().encodeToString(req.getParameter(PASSWORD_PARAM).getBytes());

        User user = userService.findUserByLogin(login);

        if (!checkLogin(session, login) || !checkPassword(session, password, user.getPassword())) {
            messageMap.put(LOGIN_VALUE, req.getParameter(LOGIN_PARAM));
            LOGGER.info(LOGIN_FAILED_MSG);

            LOGGER.info("End LoginServlet doPost() method.");
            resp.sendRedirect(LOGIN_SERVLET);
        } else {
            session.setAttribute(CURRENT_USER_ATTR, user);
            setAvatar(session, req, user.getLogin());
            LOGGER.info(LOGIN_SUCCESSFUL_MSG);

            String page = (String) session.getAttribute(PAGE_AFTER_LOGIN_ATTR);
            if (Objects.nonNull(page)) {
                session.removeAttribute(PAGE_AFTER_LOGIN_ATTR);
                resp.sendRedirect(page);
                return;
            }

            LOGGER.info("End LoginServlet doPost() method.");
            resp.sendRedirect(PAGE_MAIN);
        }
    }

    private boolean checkLogin(HttpSession session, String login) {
        if (login.isEmpty()) {
            LOGGER.debug(LOGIN_ERROR_MSG);
            session.setAttribute(FALSE_LOGIN_ATTR, "Login is empty!");
            return false;
        } else if (Objects.isNull(userService.findUserByLogin(login))) {
            LOGGER.debug(LOGIN_ERROR_MSG);
            session.setAttribute(FALSE_LOGIN_ATTR, "Login does not exist!");
            return false;
        }
        return true;
    }

    private boolean checkPassword(HttpSession session, String password, String passwordInDb) {
        if (password.isEmpty()) {
            LOGGER.debug(PASSWORD_ERROR_MSG);
            session.setAttribute(FALSE_PASSWORD_ATTR, "Password is empty!");
            return false;
        } else if (!password.equals(passwordInDb)) {
            LOGGER.debug(PASSWORD_ERROR_MSG);
            session.setAttribute(FALSE_PASSWORD_ATTR, "Wrong password!");
            return false;
        }
        return true;
    }

    private void setAvatar(HttpSession session, HttpServletRequest request, String login) {
        String applicationPath = request.getSession().getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;

        File fileUploadDirectory = new File(uploadPath);

        File[] allFiles = fileUploadDirectory.listFiles();

        if (Objects.nonNull(allFiles)) {
            for (File file : allFiles) {
                if (file.getName().startsWith(login + "_")) {
                    session.setAttribute(AVATAR_ATTR, "http://localhost:8090/WebProject/"
                            + UPLOAD_DIR + "/" + file.getName());
                }
            }
        }
    }
}