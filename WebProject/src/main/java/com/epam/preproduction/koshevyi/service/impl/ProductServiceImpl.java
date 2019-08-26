package com.epam.preproduction.koshevyi.service.impl;

import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.builder.SqlBuilder;
import com.epam.preproduction.koshevyi.db.TransactionManager;
import com.epam.preproduction.koshevyi.dto.ProductDto;
import com.epam.preproduction.koshevyi.repository.ProductRepository;
import com.epam.preproduction.koshevyi.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;
    private TransactionManager transactionManager;

    public ProductServiceImpl(ProductRepository productRepository, TransactionManager transactionManager) {
        this.productRepository = productRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public ProductBean getProductById(int productId) {
        return transactionManager.performTransaction(connection -> productRepository.getProductById(connection, productId));
    }

    @Override
    public List<ProductBean> getAllProducts() {
        return transactionManager.performTransaction(connection -> productRepository.getProductBeanList(connection));
    }

    @Override
    public List<ProductBean> getAllProductsPerPage(ProductDto productDto) {
        SqlBuilder sqlBuilder = new SqlBuilder(productDto);
        String query = sqlBuilder.getSelectLimitQueryBuilder().toString();
        List<Object> params = sqlBuilder.getParameters();
        LOGGER.info("Built query: " + query);
        LOGGER.info("Params number: " + params.size());
        for (Object param : params) {
            LOGGER.info("Parameter:  " + param);
        }

        return transactionManager.performTransaction(connection -> productRepository
                .getProductBeanListPerPage(connection, query, params));
    }

    @Override
    public int getProductsQuantity() {
        return transactionManager.performTransaction(connection -> productRepository.getProductsQuantity(connection));
    }

    @Override
    public int getFilteredProductsQuantity(ProductDto productDto) {
        SqlBuilder sqlBuilder = new SqlBuilder(productDto);
        String query = sqlBuilder.getCountQueryBuilder().toString();
        LOGGER.info("Built Count query: " + query);

        List<Object> params = sqlBuilder.getParameters();

        return transactionManager.performTransaction(connection -> productRepository
                .getFilteredProductsQuantity(connection, query, params));
    }
}