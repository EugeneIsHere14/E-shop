package com.epam.preproduction.koshevyi.servlet.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.preproduction.koshevyi.constant.Constants.ORDER_PAGE;

/**
 * Servlet for making order.
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Start OrderServlet doGet() method.");

        req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);

        LOGGER.info("End OrderServlet doGet() method.");
    }
}