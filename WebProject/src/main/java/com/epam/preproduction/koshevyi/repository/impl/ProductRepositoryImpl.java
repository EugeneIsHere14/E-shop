package com.epam.preproduction.koshevyi.repository.impl;

import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.repository.ProductRepository;
import com.epam.preproduction.koshevyi.util.mapper.ProductMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.preproduction.koshevyi.util.listProvider.QueryResultListProvider.getQueryResult;
import static com.epam.preproduction.koshevyi.util.listProvider.QueryResultListProvider.getQueryResultList;
import static com.epam.preproduction.koshevyi.util.mapper.ProductMapper.map;

public class ProductRepositoryImpl implements ProductRepository {

    private static final String GET_ALL_PRODUCTS = "SELECT products.id, products.name, categories.name, manufacturers.name, " +
            "products.price, products.description, products.picture FROM products JOIN categories ON products.category_id = " +
            "categories.id JOIN manufacturers ON products.manufacturer_id = manufacturers.id";
    private static final String GET_PRODUCTS_COUNT = "SELECT COUNT(id) FROM products";
    private static final String GET_PRODUCT_BY_ID = "SELECT products.id, products.name, categories.name, manufacturers.name, " +
            "products.price, products.description, products.picture FROM products JOIN categories ON products.category_id = " +
            "categories.id JOIN manufacturers ON products.manufacturer_id = manufacturers.id where products.id = ?";

    @Override
    public ProductBean getProductById(Connection connection, int productId) throws SQLException {
        return (ProductBean) getQueryResult(connection, GET_PRODUCT_BY_ID, ProductMapper::map, productId);
    }

    @Override
    public List<ProductBean> getProductBeanList(Connection connection) throws SQLException {
        return getQueryResultList(connection, GET_ALL_PRODUCTS, ProductMapper::map);
    }

    @Override
    public List<ProductBean> getProductBeanListPerPage(Connection connection,
                                                       String query, List<Object> params) throws SQLException {
        List<ProductBean> beans = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = connection.prepareStatement(query);
        fillPreparedStatement(preparedStatement, params);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            beans.add(map(resultSet));
        }

        resultSet.close();
        preparedStatement.close();

        return beans;
    }

    @Override
    public int getProductsQuantity(Connection connection) throws SQLException {
        int count = 0;

        Statement statement;
        ResultSet resultSet;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(GET_PRODUCTS_COUNT);
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();

        return count;
    }

    @Override
    public int getFilteredProductsQuantity(Connection connection, String query, List<Object> params) throws SQLException {
        int count = 0;

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = connection.prepareStatement(query);
        fillPreparedStatement(preparedStatement, params);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        resultSet.close();
        preparedStatement.close();

        return count;
    }

    private void fillPreparedStatement(PreparedStatement preparedStatement, List<Object> params) throws SQLException {
        if (params != null && !params.isEmpty()) {
            int k = 1;
            for (Object param : params) {
                preparedStatement.setObject(k++, param);
            }
        }
    }
}