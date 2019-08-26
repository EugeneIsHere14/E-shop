package com.epam.preproduction.koshevyi.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Given interface provides method which
 * corresponds to certain UserRepository
 * method and its return type.
 *
 * @param <E> return type of UserRepository method.
 */
@FunctionalInterface
public interface TransactionOperation<E> {

    /**
     * Returns the result of performing certain
     * UserRepository method.
     *
     * @param connection Connection instance
     * @return the result of performing certain
     *         UserRepository method
     * @throws SQLException in case of problems with connection
     */
    E operation(Connection connection) throws SQLException;
}