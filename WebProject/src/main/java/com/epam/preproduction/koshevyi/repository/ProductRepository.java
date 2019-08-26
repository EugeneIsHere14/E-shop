package com.epam.preproduction.koshevyi.repository;

import com.epam.preproduction.koshevyi.bean.ProductBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface provides methods for obtaining products from DB.
 */
public interface ProductRepository {

    /**
     * Returns ProductBean instance which is not null
     * if product with given id exists and
     * equals null - if such product doesn't exist.
     *
     * @param connection Connection instance
     * @param productId id of desired product
     * @return ProductBean instance which is not null
     *         if product with given id exists and
     *         equals null - if such product doesn't exist
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    ProductBean getProductById(Connection connection, int productId) throws SQLException;

    /**
     * Returns list of products from DB.
     *
     * @param connection Connection instance
     * @return list of products from DB
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    List<ProductBean> getProductBeanList(Connection connection) throws SQLException;

    /**
     * Returns list of products for current page from DB.
     *
     * @param connection Connection instance
     * @param query SQL query
     * @param params list of parameters which are needed for
     *               PreparedStatement execution
     * @return list of products for current page from DB
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    List<ProductBean> getProductBeanListPerPage(Connection connection, String query, List<Object> params)
            throws SQLException;

    /**
     * Returns current quantity of products from DB.
     *
     * @param connection Connection instance
     * @return current quantity of products from DB
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    int getProductsQuantity(Connection connection) throws SQLException;

    /**
     * Returns quantity of products from DB under filter conditions.
     *
     * @param connection Connection instance
     * @param query SQL query
     * @param params list of parameters which are needed for
     *               PreparedStatement execution
     * @return quantity of products from DB under filter conditions
     * @throws SQLException in case of problems with connection or SQL syntax
     */
    int getFilteredProductsQuantity(Connection connection, String query, List<Object> params) throws SQLException;
}