package com.epam.preproduction.koshevyi.servlet.cart;

import com.epam.preproduction.koshevyi.cart.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Servlet for clearing the shopping cart from items.
 */
@WebServlet("/clearCart")
public class ClearingCartServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ClearingCartServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("Start ClearingCartServlet doPost() method.");
        HttpSession session = req.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART);
        shoppingCart.clearCart();

        session.setAttribute(CART, shoppingCart);
        session.setAttribute(TOTAL_PRICE, shoppingCart.getTotalCost());
        session.setAttribute(ALL_ITEMS_QUANTITY, shoppingCart.getAllProductsCount());

        LOGGER.info("End ClearingCartServlet doPost() method.");
    }
}