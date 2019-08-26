package com.epam.preproduction.koshevyi.util.mapper;

import com.epam.preproduction.koshevyi.bean.OrderProductBean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This util class defines method for mapping
 * database result set rows to OrderProductBean.
 */
public class OrderProductBeanMapper {

    /**
     * Returns the OrderProductBean instance which
     * will be retrieved after mapping actions.
     *
     * @param resultSet ResultSet instance
     * @return the OrderProductBean instance which
     * will be retrieved after mapping actions
     */
    public static OrderProductBean map(ResultSet resultSet) {
        try {
            OrderProductBean bean = new OrderProductBean();
            bean.setOrderId(resultSet.getInt(1));
            bean.setProductName(resultSet.getString(2));
            bean.setProductPicture(resultSet.getString(3));
            bean.setProductQuantity(resultSet.getInt(4));
            bean.setProductPrice(resultSet.getBigDecimal(5));
            return bean;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
