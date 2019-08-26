package com.epam.preproduction.koshevyi.bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class which represents product in a form
 * suitable for mapping on the UI.
 */
public class ProductBean {

    private int id;
    private String name;
    private String category;
    private String manufacturer;
    private BigDecimal price;
    private String description;
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductBean that = (ProductBean) o;
        return id == that.id &&
                name.equals(that.name) &&
                category.equals(that.category) &&
                manufacturer.equals(that.manufacturer) &&
                price.equals(that.price) &&
                description.equals(that.description) &&
                Objects.equals(picture, that.picture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, manufacturer, price, description, picture);
    }
}