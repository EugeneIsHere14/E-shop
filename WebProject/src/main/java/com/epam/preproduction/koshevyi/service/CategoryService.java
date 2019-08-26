package com.epam.preproduction.koshevyi.service;

import java.util.List;

/**
 * This interface contains methods for
 * obtaining categories from repository.
 */
public interface CategoryService {

    /**
     * Provides list of categories.
     *
     * @return list of categories
     */
    List<String> getAllCategories();
}