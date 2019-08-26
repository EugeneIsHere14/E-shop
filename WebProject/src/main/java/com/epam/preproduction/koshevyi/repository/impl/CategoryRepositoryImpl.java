package com.epam.preproduction.koshevyi.repository.impl;

import com.epam.preproduction.koshevyi.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.preproduction.koshevyi.util.listProvider.QueryResultListProvider.getQueryResultList;

public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String NAME_COLUMN = "name";
    private static final String GET_ALL_CATEGORIES = "SELECT name FROM categories";

    @Override
    public List<String> getCategoriesList(Connection connection) throws SQLException {
        return getQueryResultList(connection, GET_ALL_CATEGORIES, resultSet -> resultSet.getString(NAME_COLUMN));
    }
}