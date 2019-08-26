package com.epam.preproduction.koshevyi.repository.impl;

import com.epam.preproduction.koshevyi.repository.ManufacturerRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.preproduction.koshevyi.util.listProvider.QueryResultListProvider.getQueryResultList;

public class ManufacturerRepositoryImpl implements ManufacturerRepository {

    private static final String NAME_COLUMN = "name";
    private static final String GET_ALL_MANUFACTURERS = "SELECT name FROM manufacturers";

    @Override
    public List<String> getManufacturersList(Connection connection) throws SQLException {
        return getQueryResultList(connection, GET_ALL_MANUFACTURERS, resultSet -> resultSet.getString(NAME_COLUMN));
    }
}