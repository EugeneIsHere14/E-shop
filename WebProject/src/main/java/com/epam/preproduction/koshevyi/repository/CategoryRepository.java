package com.epam.preproduction.koshevyi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface provides methods for obtaining categories of products from DB.
 */
public interface CategoryRepository {

    /**
     * Returns list of categories from DB.
     *
     * @param connection Connection instance
     * @return list of categories from DB
     * @throws SQLException in case of problems with connection
     */
    List<String> getCategoriesList(Connection connection) throws SQLException;
}