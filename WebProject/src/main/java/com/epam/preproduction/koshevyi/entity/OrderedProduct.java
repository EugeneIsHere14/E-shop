package com.epam.preproduction.koshevyi.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Unmodifiable entity class which represents
 * product associated with order.
 */
public class OrderedProduct {

    private int productId;
    private int orderId;
    private int productQuantity;
    private BigDecimal productPrice;

    public OrderedProduct(int productId, int orderId, int productQuantity, BigDecimal productPrice) {
        this.productId = productId;
        this.orderId = orderId;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return productId == that.productId &&
                orderId == that.orderId &&
                productQuantity == that.productQuantity &&
                productPrice.equals(that.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId, productQuantity, productPrice);
    }
}