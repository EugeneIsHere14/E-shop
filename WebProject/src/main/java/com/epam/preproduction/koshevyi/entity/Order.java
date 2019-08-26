package com.epam.preproduction.koshevyi.entity;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Order entity class.
 */
public class Order {

    private int id;
    private int orderStatusId;
    private Timestamp orderTime;
    private User user;
    private String payType;
    private String cardNumber;
    private String address;

    public Order() {
    }

    public Order(int orderStatusId, Timestamp orderTime, User user, String payType, String cardNumber, String address) {
        this.orderStatusId = orderStatusId;
        this.orderTime = orderTime;
        this.user = user;
        this.payType = payType;
        this.cardNumber = cardNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                orderStatusId == order.orderStatusId &&
                orderTime.equals(order.orderTime) &&
                user.equals(order.user) &&
                payType.equals(order.payType) &&
                Objects.equals(cardNumber, order.cardNumber) &&
                address.equals(order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatusId, orderTime, user, payType, cardNumber, address);
    }
}