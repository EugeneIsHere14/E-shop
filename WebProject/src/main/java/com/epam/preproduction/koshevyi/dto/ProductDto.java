package com.epam.preproduction.koshevyi.dto;

import java.util.Arrays;

/**
 * Product DTO class which is used for
 * transferring values from UI fields.
 */
public class ProductDto {

    private String name;
    private String minPrice;
    private String maxPrice;
    private String[] categories;
    private String[] manufacturers;
    private Integer currentPage;
    private Integer wantedItemsQuantity;
    private String sortDirectionName;
    private String sortDirectionPrice;

    public ProductDto(String name, String minPrice, String maxPrice, String[] categories, String[] manufacturers,
                      Integer currentPage, Integer wantedItemsQuantity, String sortDirectionName, String sortDirectionPrice) {

        this.name = name;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.categories = categories;
        this.manufacturers = manufacturers;
        this.currentPage = currentPage;
        this.wantedItemsQuantity = wantedItemsQuantity;
        this.sortDirectionName = sortDirectionName;
        this.sortDirectionPrice = sortDirectionPrice;

    }

    public String getName() {
        return name;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public String[] getCategories() {
        return categories;
    }

    public String[] getManufacturers() {
        return manufacturers;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getWantedItemsQuantity() {
        return wantedItemsQuantity;
    }

    public String getSortDirectionName() {
        return sortDirectionName;
    }

    public String getSortDirectionPrice() {
        return sortDirectionPrice;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", categories=" + Arrays.toString(categories) +
                ", manufacturers=" + Arrays.toString(manufacturers) +
                ", currentPage=" + currentPage +
                ", wantedItemsQuantity=" + wantedItemsQuantity +
                ", sortDirectionName='" + sortDirectionName + '\'' +
                ", sortDirectionPrice='" + sortDirectionPrice + '\'' +
                '}';
    }
}