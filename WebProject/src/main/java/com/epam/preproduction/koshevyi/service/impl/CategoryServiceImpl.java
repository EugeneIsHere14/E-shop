package com.epam.preproduction.koshevyi.service.impl;

import com.epam.preproduction.koshevyi.db.TransactionManager;
import com.epam.preproduction.koshevyi.repository.CategoryRepository;
import com.epam.preproduction.koshevyi.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private TransactionManager transactionManager;

    public CategoryServiceImpl(CategoryRepository categoryRepository, TransactionManager transactionManager) {
        this.categoryRepository = categoryRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<String> getAllCategories() {
        return transactionManager.performTransaction(connection -> categoryRepository.getCategoriesList(connection));
    }
}