package com.epam.preproduction.koshevyi.service;

import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.dto.ProductDto;

import java.util.List;

/**
 * This interface contains methods for
 * obtaining products from repository.
 */
public interface ProductService {

    /**
     * Provides ProductBean instance which is not null
     * if product with given id exists and
     * equals null - if such product doesn't exist.
     *
     * @param productId id of desired product
     * @return ProductBean instance which is not null
     *         if product with given id exists and
     *         equals null - if such product doesn't exist
     */
    ProductBean getProductById(int productId);

    /**
     * Provides list of products.
     *
     * @return list of products
     */
    List<ProductBean> getAllProducts();

    /**
     * Provides list of products for current page.
     *
     * @param productDto ProductDto instance
     * @return list of products for current page
     */
    List<ProductBean> getAllProductsPerPage(ProductDto productDto);

    /**
     * Returns current quantity of products.
     *
     * @return current quantity of products
     */
    int getProductsQuantity();

    /**
     * Returns quantity of products under filter conditions.
     *
     * @param productDto ProductDto instance
     * @return quantity of products under filter conditions
     */
    int getFilteredProductsQuantity(ProductDto productDto);
}