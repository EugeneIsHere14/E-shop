package com.epam.preproduction.koshevyi.service.impl;

import com.epam.preproduction.koshevyi.bean.OrderProductBean;
import com.epam.preproduction.koshevyi.entity.Order;
import com.epam.preproduction.koshevyi.db.TransactionManager;
import com.epam.preproduction.koshevyi.entity.OrderedProduct;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.repository.OrderRepository;
import com.epam.preproduction.koshevyi.service.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private TransactionManager transactionManager;

    public OrderServiceImpl(OrderRepository orderRepository, TransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean addOrder(Order newOrder, User userForId, Set<OrderedProduct> orderedProducts) {
        return transactionManager.performTransaction(connection -> orderRepository.addOrder(newOrder, userForId, orderedProducts, connection));
    }

    @Override
    public List<OrderProductBean> getOrderProducts(int orderId) {
        return transactionManager.performTransaction(connection -> orderRepository.getOrderProductBeanList(orderId, connection));
    }

    @Override
    public String[] findOrderStatusAndDescriptionByStatusId(int statusId) {
        return transactionManager.performTransaction(connection -> orderRepository.
                findOrderStatusDescriptionByOrderStatusId(statusId, connection));
    }

    @Override
    public double getSumOrderCost(int orderId) {
        return transactionManager.performTransaction(connection ->orderRepository.getSumOrderCost(orderId, connection));
    }
}