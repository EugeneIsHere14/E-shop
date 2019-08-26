package com.epam.preproduction.koshevyi.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class executes different operations in transactions.
 */
public class TransactionManager {

    private static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);

    private DataSource dataSource;

    /**
     * Creates connection pool, works with data from database
     * and returns result of incoming operation.
     *
     * @param operation operation that doing some actions
     *                  with data from database.
     * @return result of operation.
     */
    public <E> E performTransaction(TransactionOperation<E> operation) {
        Connection connection = null;
        if (dataSource == null) {
            try {
                Context initialContext = new InitialContext();
                dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/SHOP");
            } catch (NamingException e) {
                LOGGER.error("Cannot obtain a connection from the pool!", e);
            }
        }
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            E result = operation.operation(connection);

            connection.commit();

            return result;

        } catch (SQLException ex1) {
            if (connection != null)
                try {
                    LOGGER.error("Problems with getting connection!", ex1);
                    connection.rollback();
                } catch (SQLException ex2) {
                    LOGGER.error("Problems with rollback!", ex2);
                }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Cannot close connection!", e);
            }
        }
        return null;
    }
}