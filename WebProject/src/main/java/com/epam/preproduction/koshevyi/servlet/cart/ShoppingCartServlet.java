package com.epam.preproduction.koshevyi.servlet.cart;

import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.cart.ShoppingCart;
import com.epam.preproduction.koshevyi.cart.impl.ShoppingCartImpl;
import com.epam.preproduction.koshevyi.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Servlet that represents shopping cart
 * and all it functionality.
 */
@WebServlet("/cart")
public class ShoppingCartServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ShoppingCartServlet.class);

    private ProductService productService;

    @Override
    public void init(ServletConfig config) {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE_CONTEXT_ATTR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Start ShoppingCartServlet doPost() method.");
        HttpSession session = req.getSession();

        String productId = req.getParameter(CART_PRODUCT_ID);
        ProductBean product = productService.getProductById(Integer.parseInt(productId));

        ShoppingCart shoppingCart = Optional.ofNullable((ShoppingCart) session.getAttribute(CART))
                .orElseGet(ShoppingCartImpl::new);

        shoppingCart.addProduct(product);

        session.setAttribute(CART, shoppingCart);
        session.setAttribute(TOTAL_PRICE, shoppingCart.getTotalCost());
        session.setAttribute(ALL_ITEMS_QUANTITY, shoppingCart.getAllProductsCount());
        session.setAttribute(ITEM_QUANTITY, shoppingCart.getCart().get(product));

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonObject = objectMapper.createObjectNode();
        jsonObject.put("Product", String.valueOf(product));
        jsonObject.put(ALL_ITEMS_QUANTITY, shoppingCart.getAllProductsCount());
        jsonObject.put(ITEM_QUANTITY, shoppingCart.getCart().get(product));
        objectMapper.writeValue(resp.getWriter(), jsonObject);

        LOGGER.info("End ShoppingCartServlet doPost() method.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        LOGGER.info("Start ShoppingCartServlet doGet() method.");

        req.getRequestDispatcher(CART_PAGE).forward(req, resp);

        LOGGER.info("End ShoppingCartServlet doGet() method.");
    }
}