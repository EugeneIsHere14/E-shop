package com.epam.preproduction.koshevyi.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static com.epam.preproduction.koshevyi.constant.Constants.PAGE_MAIN;

/**
 * Servlet for user logout.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(LogoutServlet.class);

    private static final String LOGOUT = "Logout is successful. Redirected to main page.";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (Objects.nonNull(session)) {
            session.invalidate();
        }
        resp.sendRedirect(PAGE_MAIN);
        LOGGER.info(LOGOUT);
    }
}