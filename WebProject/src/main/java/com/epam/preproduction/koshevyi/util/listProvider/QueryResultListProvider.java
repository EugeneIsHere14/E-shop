package com.epam.preproduction.koshevyi.util.listProvider;

import com.epam.preproduction.koshevyi.repository.handler.ResultSetHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This util class has methods for
 * obtaining List or instance
 * with required data from DB.
 */
public class QueryResultListProvider {

    /**
     * Fills and returns instance with required
     * data from DB using ResultSetHandler interface.
     *
     * @param connection       Connection instance
     * @param query            SQL-query
     * @param resultSetHandler ResultSetHandler<E> interface instance
     * @param param            parameter for PreparedStatement
     * @param <E>              type of instance which must be returned
     * @return instance with required data from DB
     * @throws SQLException in case of connection problems or
     *                      problems with executing SQL-query
     */
    public static <E> Object getQueryResult(Connection connection, String query,
                                            ResultSetHandler<E> resultSetHandler, Object param) throws SQLException {
        Object object = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, param);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            object = resultSetHandler.handle(resultSet);
        }

        return object;
    }

    /**
     * Fills and returns List instance with required
     * data from DB using ResultSetHandler interface.
     *
     * @param connection       Connection instance
     * @param query            SQL-query
     * @param resultSetHandler ResultSetHandler<E> interface instance
     * @param <E>              type of List which must be returned
     * @return List instance with required data from DB
     * @throws SQLException in case of connection problems or
     *                      problems with executing SQL-query
     */
    public static <E> List<E> getQueryResultList(Connection connection, String query,
                                                 ResultSetHandler<E> resultSetHandler) throws SQLException {
        List<E> list = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            list.add(resultSetHandler.handle(resultSet));
        }
        resultSet.close();
        statement.close();

        return list;
    }

    /**
     * Fills and returns List instance with required
     * data from DB using ResultSetHandler interface.
     *
     * @param connection       Connection instance
     * @param query            SQL-query
     * @param resultSetHandler ResultSetHandler<E> interface instance
     * @param <E>              type of List which must be returned
     * @param params parameter for PreparedStatement
     * @return List instance with required data from DB
     * @throws SQLException in case of connection problems or
     *                      problems with executing SQL-query
     */
    public static <E> List<E> getQueryResultList(Connection connection, String query, ResultSetHandler<E> resultSetHandler,
                                                 Object... params) throws SQLException {
        List<E> list = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, params[0]);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(resultSetHandler.handle(resultSet));
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }
}