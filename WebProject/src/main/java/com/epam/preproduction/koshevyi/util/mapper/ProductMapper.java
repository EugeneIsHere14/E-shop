package com.epam.preproduction.koshevyi.util.mapper;

import com.epam.preproduction.koshevyi.bean.ProductBean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This util class defines method for mapping
 * database result set rows to ProductBean.
 */
public class ProductMapper {

    /**
     * Returns the ProductBean instance which
     * will be retrieved after mapping actions.
     *
     * @param resultSet ResultSet instance
     * @return the ProductBean instance which
     * will be retrieved after mapping actions
     */
    public static ProductBean map(ResultSet resultSet) {
        try {
            ProductBean productBean = new ProductBean();
            productBean.setId(resultSet.getInt(1));
            productBean.setName(resultSet.getString(2));
            productBean.setCategory(resultSet.getString(3));
            productBean.setManufacturer(resultSet.getString(4));
            productBean.setPrice(resultSet.getBigDecimal(5));
            productBean.setDescription(resultSet.getString(6));
            productBean.setPicture(resultSet.getString(7));
            return productBean;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}