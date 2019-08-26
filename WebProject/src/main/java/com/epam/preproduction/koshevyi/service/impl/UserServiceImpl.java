package com.epam.preproduction.koshevyi.service.impl;

import com.epam.preproduction.koshevyi.db.TransactionManager;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.repository.UserRepository;
import com.epam.preproduction.koshevyi.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserRepository userRepository, TransactionManager transactionManager) {
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean addNewUser(User newUser) {
        if (findUserByLogin(newUser.getLogin()) == null) {
            return transactionManager.performTransaction(connection -> userRepository.addNewUser(newUser, connection));
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return transactionManager.performTransaction(connection -> userRepository.getUserList(connection));
    }

    @Override
    public User findUserByLogin(String login) {
        return transactionManager.performTransaction(connection -> userRepository.findUserByLogin(login, connection));
    }
}