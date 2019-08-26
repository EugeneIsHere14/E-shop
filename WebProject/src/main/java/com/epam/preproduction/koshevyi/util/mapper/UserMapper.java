package com.epam.preproduction.koshevyi.util.mapper;

import com.epam.preproduction.koshevyi.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This util class defines method for mapping
 * database result set rows to User entity.
 */
public class UserMapper {

    /**
     * Returns the User instance which
     * will be retrieved after mapping actions.
     *
     * @param resultSet ResultSet instance
     * @return the User instance which
     * will be retrieved after mapping actions
     */
    public static User map(ResultSet resultSet) {
        try {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setEmail(resultSet.getString(2));
            user.setLogin(resultSet.getString(3));
            user.setName(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setSalesSubscription(resultSet.getString(6));
            user.setGoodsSubscription(resultSet.getString(7));
            user.setRoleId(resultSet.getInt(8));
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}