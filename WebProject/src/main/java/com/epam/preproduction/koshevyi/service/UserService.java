package com.epam.preproduction.koshevyi.service;

import com.epam.preproduction.koshevyi.entity.User;

import java.util.List;

/**
 * This interface contains methods for
 * obtaining users from repository
 * or adding users to repository
 */
public interface UserService {

    /**
     * Adds new user.
     *
     * @param newUser instance of User which
     *                will be created
     * @return true after user adding
     */
    boolean addNewUser(User newUser);

    /**
     * Provides list of existing users.
     *
     * @return list of existing users
     */
    List<User> getAllUsers();

    /**
     * Returns User instance which is not null
     * if user with given login exists and
     * equals null - if such user doesn't exist.
     *
     * @param login String value of login
     * @return Returns User instance which is not null
     *         if user with given login exists and
     *         equals null - if such user doesn't exist
     */
    User findUserByLogin(String login);
}