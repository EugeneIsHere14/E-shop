package com.epam.preproduction.koshevyi.bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class which represents details
 * about order in a form suitable for
 * mapping on the UI.
 */
public class OrderProductBean {

    private int orderId;
    private String productName;
    private String productPicture;
    private int productQuantity;
    private BigDecimal productPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductBean that = (OrderProductBean) o;
        return orderId == that.orderId &&
                productQuantity == that.productQuantity &&
                productName.equals(that.productName) &&
                productPicture.equals(that.productPicture) &&
                productPrice.equals(that.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productName, productPicture, productQuantity, productPrice);
    }
}