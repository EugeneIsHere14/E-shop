package com.epam.preproduction.koshevyi.service;

import java.util.List;

/**
 * This interface contains methods for
 * obtaining manufacturers from repository.
 */
public interface ManufacturerService {

    /**
     * Provides list of manufacturers.
     *
     * @return list of manufacturers
     */
    List<String> getAllManufacturers();
}