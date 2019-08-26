package com.epam.preproduction.koshevyi.service;

import com.epam.preproduction.koshevyi.bean.OrderProductBean;
import com.epam.preproduction.koshevyi.entity.Order;
import com.epam.preproduction.koshevyi.entity.OrderedProduct;
import com.epam.preproduction.koshevyi.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * This interface contains methods for
 * obtaining orders from repository and
 * adding them to it.
 */
public interface OrderService {

    /**
     * Creates new order and. Returns true after
     * addition of order.
     *
     * @param newOrder Order class instance
     * @param userForId User instance which provides id
     * @param orderedProducts Set of products which were
     *                        chosen by user
     * @return true after addition of order
     */
    boolean addOrder(Order newOrder, User userForId, Set<OrderedProduct> orderedProducts);

    /**
     * Returns List instance with products which are
     * related to current order.
     *
     * @param orderId id for relative products search
     * @return List instance with products which are
     *         related to current order
     */
    List<OrderProductBean> getOrderProducts(int orderId);

    /**
     * Returns String[] instance which contains order
     * status and description.
     *
     * @param statusId id which are used for search
     * @return String[] instance which contains order
     *         status and description
     */
    String[] findOrderStatusAndDescriptionByStatusId(int statusId);

    /**
     * Calculates and returns total cost of order.
     *
     * @param orderId id which are used for search
     *                relative order
     * @return total cost of order
     */
    double getSumOrderCost(int orderId);
}