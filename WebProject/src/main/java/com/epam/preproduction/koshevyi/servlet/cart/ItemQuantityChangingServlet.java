package com.epam.preproduction.koshevyi.servlet.cart;

import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.cart.ShoppingCart;
import com.epam.preproduction.koshevyi.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Servlet for changing items quantity in the cart.
 */
@WebServlet("/changeItemQuantity")
public class ItemQuantityChangingServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ItemQuantityChangingServlet.class);

    private ProductService productService;

    @Override
    public void init(ServletConfig config) {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE_CONTEXT_ATTR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Start ItemQuantityChangingServlet doPost() method.");
        HttpSession session = req.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART);

        String productId = req.getParameter(CART_PRODUCT_ID);
        int newItemQuantity = Integer.parseInt(req.getParameter(ITEM_QUANTITY));
        ProductBean product = productService.getProductById(Integer.parseInt(productId));
        shoppingCart.changeItemQuantity(Integer.parseInt(productId), newItemQuantity);

        session.setAttribute(CART, shoppingCart);
        session.setAttribute(TOTAL_PRICE, shoppingCart.getTotalCost());
        session.setAttribute(ALL_ITEMS_QUANTITY, shoppingCart.getAllProductsCount());
        session.setAttribute(ITEM_QUANTITY, shoppingCart.getCart().get(product));

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put(ALL_ITEMS_QUANTITY, shoppingCart.getAllProductsCount());
        jsonObject.put(TOTAL_PRICE, shoppingCart.getTotalCost());
        jsonObject.put(ITEM_QUANTITY, shoppingCart.getCart().get(product));
        objectMapper.writeValue(resp.getWriter(), jsonObject);

        LOGGER.info("End ItemQuantityChangingServlet doPost() method.");
    }
}