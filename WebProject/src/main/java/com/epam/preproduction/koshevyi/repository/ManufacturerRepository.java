package com.epam.preproduction.koshevyi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface provides methods for obtaining manufacturers of products from DB.
 */
public interface ManufacturerRepository {

    /**
     * Returns list of manufacturers from DB.
     *
     * @param connection Connection instance
     * @return list of manufacturers from DB
     * @throws SQLException in case of problems with connection
     */
    List<String> getManufacturersList(Connection connection) throws SQLException;
}