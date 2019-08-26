package com.epam.preproduction.koshevyi.servlet.order;

import com.epam.preproduction.koshevyi.bean.OrderProductBean;
import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.cart.ShoppingCart;
import com.epam.preproduction.koshevyi.entity.Order;
import com.epam.preproduction.koshevyi.entity.OrderedProduct;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.service.OrderService;
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
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Servlet for order history view.
 */
@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(OrderHistoryServlet.class);

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) {
        orderService = (OrderService) config.getServletContext().getAttribute(ORDER_SERVICE_CONTEXT_ATTR);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("Start OrderHistoryServlet doPost() method.");

        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART);

        User user = (User) session.getAttribute(CURRENT_USER_ATTR);
        String payType = req.getParameter(PAY_TYPE);
        String deliveryAddress = req.getParameter(DELIVERY_ADDRESS);
        String cardNumber = req.getParameter(CARD_NUMBER);

        Order order = new Order(2, new Timestamp(System.currentTimeMillis()), user, payType, cardNumber, deliveryAddress);
        Set<ProductBean> orderedProducts = shoppingCart.getProducts();

        Set<OrderedProduct> orderedProductSet = new HashSet<>();

        int totalOrderItemQty = 0;

        for (ProductBean product : orderedProducts) {
            OrderedProduct orderedProduct = new OrderedProduct(product.getId(), order.getId(), shoppingCart.getCart().get(product), product.getPrice());
            totalOrderItemQty += shoppingCart.getCart().get(product);

            orderedProductSet.add(orderedProduct);
        }

        String[] status = orderService.findOrderStatusAndDescriptionByStatusId(order.getOrderStatusId());

        orderService.addOrder(order, user, orderedProductSet);

        double totalOrderCost = orderService.getSumOrderCost(order.getId());

        session.setAttribute(TOTAL_ORDER_COST, totalOrderCost);
        session.setAttribute(TOTAL_ORDER_ITEM_QTY, totalOrderItemQty);
        session.setAttribute(STATUS_DESC, status[0]);
        session.setAttribute(ORDER_STATUS, status[1]);
        session.setAttribute(DELIVERY_ADDRESS, deliveryAddress);
        session.setAttribute(CARD_NUMBER, cardNumber);

        List<OrderProductBean> orderProductBeanList = orderService.getOrderProducts(order.getId());
        session.setAttribute(ORDER_ITEMS, orderProductBeanList);

        resp.sendRedirect(ORDER_HISTORY_SERVLET);

        LOGGER.info("End OrderHistoryServlet doPost() method.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Start OrderHistoryServlet doGet() method.");

        req.getRequestDispatcher(ORDER_HISTORY_PAGE).forward(req, resp);

        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART);
        if (Objects.nonNull(shoppingCart)) {
            shoppingCart.clearCart();

            session.setAttribute(CART, shoppingCart);
            session.setAttribute(ALL_ITEMS_QUANTITY, shoppingCart.getAllProductsCount());
            session.setAttribute(TOTAL_PRICE, shoppingCart.getTotalCost());
        }

        LOGGER.info("End OrderHistoryServlet doGet() method.");
    }
}