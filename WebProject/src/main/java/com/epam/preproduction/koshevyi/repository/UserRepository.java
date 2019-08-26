package com.epam.preproduction.koshevyi.repository;

import com.epam.preproduction.koshevyi.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface provides methods for obtaining users
 * from DB and adding new users to DB.
 */
public interface UserRepository {

    /**
     * Returns list of users from DB.
     *
     * @param connection Connection instance
     * @return list of users from DB
     * @throws SQLException in case of problems with connection
     */
    List<User> getUserList(Connection connection) throws SQLException;

    /**
     * Adds new user to DB.
     *
     * @param user instance of User to add to DB
     * @param connection Connection instance
     * @return true after user adding to DB
     * @throws SQLException in case of problems with connection
     */
    boolean addNewUser(User user, Connection connection) throws SQLException;

    /**
     * Returns User instance which is not null
     * if user with given login exists and
     * equals null - if such user doesn't exist.
     *
     * @param login desired user login
     * @param connection Connection instance
     * @return User instance which is not null
     *         if user with given login exists and
     *         equals null - if such user doesn't exist.
     * @throws SQLException in case of problems with connection
     */
    User findUserByLogin(String login, Connection connection) throws SQLException;
}