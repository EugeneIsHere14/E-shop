package com.epam.preproduction.koshevyi.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.preproduction.koshevyi.constant.Constants.ADMIN_PAGE;

/**
 * Servlet for admin page.
 */
@WebServlet("/adminPage")
public class AdminPageServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AdminPageServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Start AdminPageServlet doGet() method.");

        req.getRequestDispatcher(ADMIN_PAGE).forward(req, resp);

        LOGGER.info("End AdminPageServlet doGet() method.");
    }
}