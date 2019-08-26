package com.epam.preproduction.koshevyi.repository.impl;

import com.epam.preproduction.koshevyi.bean.OrderProductBean;
import com.epam.preproduction.koshevyi.entity.Order;
import com.epam.preproduction.koshevyi.entity.OrderedProduct;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.repository.OrderRepository;
import com.epam.preproduction.koshevyi.util.mapper.OrderProductBeanMapper;

import java.sql.*;
import java.util.List;
import java.util.Set;

import static com.epam.preproduction.koshevyi.util.listProvider.QueryResultListProvider.getQueryResultList;

public class OrderRepositoryImpl implements OrderRepository {
    private static final String INSERT_ORDER = "INSERT INTO shop.orders VALUES (default, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ORDERED_PRODUCT = "INSERT INTO shop.order_product (product_id, order_id, quantity, price) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDER_ITEMS = "SELECT orders.id, products.name, products.picture, " +
            "order_product.quantity, products.price FROM order_product join orders ON " +
            "order_product.order_id = orders.id JOIN products ON order_product.product_id = products.id where orders.id = ?;";
    private static final String SELECT_ORDER_STATUS_BY_ORDER_STATUS_ID = "SELECT description, status from order_statuses " +
            "where id = ?";
    private static final String SELECT_ORDER_SUM_COST = "SELECT SUM(price * quantity) FROM order_product WHERE order_id = ?";

    @Override
    public boolean addOrder(Order newOrder, User userForId, Set<OrderedProduct> orderedProducts, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
        int k = 1;
        preparedStatement.setInt(k++, newOrder.getOrderStatusId());
        preparedStatement.setTimestamp(k++, newOrder.getOrderTime());
        preparedStatement.setString(k++, newOrder.getPayType());
        preparedStatement.setString(k++, newOrder.getCardNumber());
        preparedStatement.setString(k++, newOrder.getAddress());
        preparedStatement.setInt(k, userForId.getId());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            newOrder.setId(resultSet.getInt(1));
        }

        addOrderedProduct(orderedProducts, newOrder.getId(), connection);

        return true;
    }

    @Override
    public List<OrderProductBean> getOrderProductBeanList(int orderId, Connection connection) throws SQLException {
        return getQueryResultList(connection, SELECT_ALL_ORDER_ITEMS, OrderProductBeanMapper::map, orderId);

    }

    @Override
    public String[] findOrderStatusDescriptionByOrderStatusId(int statusId, Connection connection) throws SQLException {
        String[] status = new String[2];

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(SELECT_ORDER_STATUS_BY_ORDER_STATUS_ID);
        preparedStatement.setInt(1, statusId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            status[0] = resultSet.getString(1);
            status[1] = resultSet.getString(2);
        }
        return status;
    }

    @Override
    public double getSumOrderCost(int orderId, Connection connection) throws SQLException {
        double sum = 0;

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(SELECT_ORDER_SUM_COST);
        preparedStatement.setInt(1, orderId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            sum = resultSet.getDouble(1);
        }
        return sum;
    }

    private void addOrderedProduct(Set<OrderedProduct> orderedProducts, int orderId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(INSERT_ORDERED_PRODUCT);
        for (OrderedProduct product : orderedProducts) {
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, product.getProductQuantity());
            preparedStatement.setBigDecimal(4, product.getProductPrice());

            preparedStatement.executeUpdate();
        }
    }
}