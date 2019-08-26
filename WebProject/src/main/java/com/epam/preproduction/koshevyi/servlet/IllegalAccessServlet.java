package com.epam.preproduction.koshevyi.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.preproduction.koshevyi.constant.Constants.ILLEGAL_ACCESS_PAGE;

/**
 * Servlet to transfer to illegal access error page.
 */
@WebServlet("/illegalAccess")
public class IllegalAccessServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(IllegalAccessServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Start IllegalAccessServlet doGet() method.");

        req.getRequestDispatcher(ILLEGAL_ACCESS_PAGE).forward(req, resp);

        LOGGER.info("Finish IllegalAccessServlet doGet() method.");
    }
}