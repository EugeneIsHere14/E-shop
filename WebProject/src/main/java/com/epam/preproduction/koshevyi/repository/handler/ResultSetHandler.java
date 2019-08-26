package com.epam.preproduction.koshevyi.repository.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Given interface provides method for ResultSet
 * handling in the required form.
 *
 * @param <T> type of instance which must be returned
 *           due to the method execution
 */
@FunctionalInterface
public interface ResultSetHandler<T> {

    /**
     * Returns result of ResultSet work in particular case.
     *
     * @param resultSet ResultSet instance
     * @return result of ResultSet work
     * @throws SQLException in case of connection problems or
     *         problems with executing SQL-query
     */
    T handle(ResultSet resultSet) throws SQLException;
}