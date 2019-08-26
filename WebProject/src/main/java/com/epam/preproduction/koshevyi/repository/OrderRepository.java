package com.epam.preproduction.koshevyi.repository;

import com.epam.preproduction.koshevyi.bean.OrderProductBean;
import com.epam.preproduction.koshevyi.entity.Order;
import com.epam.preproduction.koshevyi.entity.OrderedProduct;
import com.epam.preproduction.koshevyi.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * This interface provides methods for obtaining orders
 * and details about them from DB and adding them to it.
 */
public interface OrderRepository {

    /**
     * Adds order to DB. Returns true after
     * adding of order.
     *
     * @param newOrder Order instance
     * @param userForId User instance which provides id
     * @param orderedProducts Set of products which were
     *                        chosen by user
     * @param connection Connection instance
     * @return Returns true after adding of order
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    boolean addOrder(Order newOrder, User userForId, Set<OrderedProduct> orderedProducts,
                     Connection connection) throws SQLException;

    /**
     * Returns List instance with products which are
     * related to current order.
     *
     * @param orderId id for relative products search
     * @param connection Connection instance
     * @return List instance with products which are
     *         related to current order
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    List<OrderProductBean> getOrderProductBeanList(int orderId, Connection connection) throws SQLException;

    /**
     * Returns String[] instance which contains order
     * status and description.
     *
     * @param connection Connection instance
     * @param statusId id which are used for search
     * @return String[] instance which contains order
     *         status and description
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    String[] findOrderStatusDescriptionByOrderStatusId(int statusId, Connection connection) throws SQLException;

    /**
     * Calculates and returns total cost of order.
     *
     * @param orderId id which are used for search
     *                relative order
     * @param connection Connection instance
     * @return returns total cost of order
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    double getSumOrderCost(int orderId, Connection connection) throws SQLException;
}