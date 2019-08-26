package com.epam.preproduction.koshevyi.repository.impl;

import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.repository.UserRepository;
import com.epam.preproduction.koshevyi.util.mapper.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.epam.preproduction.koshevyi.util.listProvider.QueryResultListProvider.getQueryResult;
import static com.epam.preproduction.koshevyi.util.listProvider.QueryResultListProvider.getQueryResultList;
import static java.util.Base64.getEncoder;

public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT_USER = "INSERT INTO shop.users (email, login, name, password, sales_sub, goods_sub, role_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    @Override
    public List<User> getUserList(Connection connection) throws SQLException {
        return getQueryResultList(connection, GET_ALL_USERS, UserMapper::map);
    }

    @Override
    public boolean addNewUser(User user, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(INSERT_USER);
        int k = 1;
        preparedStatement.setString(k++, user.getEmail());
        preparedStatement.setString(k++, user.getLogin());
        preparedStatement.setString(k++, user.getName());
        preparedStatement.setString(k++, getEncoder().encodeToString(user.getPassword().getBytes()));
        preparedStatement.setString(k++, user.getSalesSubscription());
        preparedStatement.setString(k++, user.getGoodsSubscription());
        preparedStatement.setInt(k, user.getRoleId());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public User findUserByLogin(String login, Connection connection) throws SQLException {
        return (User) getQueryResult(connection, FIND_USER_BY_LOGIN, UserMapper::map, login);
    }
}