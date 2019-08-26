package com.epam.preproduction.koshevyi.builder;

import com.epam.preproduction.koshevyi.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;

/**
 * This class contains methods for building SQL
 * SELECT and COUNT queries depending on the
 * user's choice of filters on UI.
 */
public class SqlBuilder {

    private StringBuilder queryBuilderSelectLimit;
    private StringBuilder queryBuilderCount;
    private ProductDto productDto;
    private List<Object> parameters;
    private boolean flag;

    private static final String GET_ALL_PRODUCTS_PER_PAGE = "SELECT products.id, products.name, categories.name, manufacturers.name, " +
            "products.price, products.description, products.picture FROM products JOIN categories ON products.category_id = " +
            "categories.id JOIN manufacturers ON products.manufacturer_id = manufacturers.id";
    private static final String GET_FILTERED_PRODUCTS_COUNT = "SELECT COUNT(products.id) " +
            "FROM products JOIN categories ON products.category_id = " +
            "categories.id JOIN manufacturers ON products.manufacturer_id = manufacturers.id";
    private static final String LIKE_PART = " WHERE products.name LIKE ?";
    private static final String MIN_PRICE_AND = " AND products.price >= ?";
    private static final String MIN_PRICE_WHERE = " WHERE products.price >= ?";
    private static final String MAX_PRICE_AND = " AND products.price <= ?";
    private static final String MAX_PRICE_WHERE = " WHERE products.price <= ?";
    private static final String CATEGORIES_IN_AND = " AND categories.name IN (";
    private static final String CATEGORIES_IN_WHERE = " WHERE categories.name IN (";
    private static final String MANUFACTURERS_IN_AND = " AND manufacturers.name IN (";
    private static final String MANUFACTURERS_IN_WHERE = " WHERE manufacturers.name IN (";
    private static final String ORDER_BY_PRICE_FIRST = " ORDER BY products.price ";
    private static final String ORDER_BY_PRICE_SECOND = ", products.price ";
    private static final String ORDER_BY_NAME_FIRST = " ORDER BY products.name ";
    private static final String ORDER_BY_NAME_SECOND = ", products.name ";
    private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";

    public SqlBuilder(ProductDto productDto) {
        this.queryBuilderSelectLimit = new StringBuilder(GET_ALL_PRODUCTS_PER_PAGE);
        this.queryBuilderCount = new StringBuilder(GET_FILTERED_PRODUCTS_COUNT);
        this.productDto = productDto;
        parameters = new ArrayList<>();
    }

    /**
     * Returns the list of parameters which are
     * needed for PreparedStatement execution.
     *
     * @return list of parameters for PreparedStatement
     */
    public List<Object> getParameters() {
        return parameters;
    }

    private void buildSearchByName(StringBuilder stringBuilder) {
        if (nonNull(productDto.getName()) && !productDto.getName().isEmpty()) {
            parameters.add("%" + productDto.getName() + "%");
            stringBuilder.append(LIKE_PART);
        }
    }

    private void buildSearchByMinPrice(StringBuilder stringBuilder, String baseQuery) {
        if (nonNull(productDto.getMinPrice()) && !productDto.getMinPrice().isEmpty()) {
            if (stringBuilder.length() > baseQuery.length()) {
                parameters.add(productDto.getMinPrice());
                stringBuilder.append(MIN_PRICE_AND);
                return;
            }
            parameters.add(productDto.getMinPrice());
            stringBuilder.append(MIN_PRICE_WHERE);
        }
    }

    private void buildSearchByMaxPrice(StringBuilder stringBuilder, String baseQuery) {
        if (nonNull(productDto.getMaxPrice()) && !productDto.getMaxPrice().isEmpty()) {
            if (stringBuilder.length() > baseQuery.length()) {
                parameters.add(productDto.getMaxPrice());
                stringBuilder.append(MAX_PRICE_AND);
                return;
            }
            parameters.add(productDto.getMaxPrice());
            stringBuilder.append(MAX_PRICE_WHERE);
        }
    }

    private void buildSearchByCategories(StringBuilder stringBuilder, String baseQuery) {
        if (nonNull(productDto.getCategories()) && productDto.getCategories().length > 0) {
            if (stringBuilder.length() > baseQuery.length()) {
                handleParamsFromArray(stringBuilder, CATEGORIES_IN_AND, productDto.getCategories());
                return;
            }
            handleParamsFromArray(stringBuilder, CATEGORIES_IN_WHERE, productDto.getCategories());
        }
    }

    private void buildSearchByManufacturers(StringBuilder stringBuilder, String baseQuery) {
        if (nonNull(productDto.getManufacturers()) && productDto.getManufacturers().length > 0) {
            if (stringBuilder.length() > baseQuery.length()) {
                handleParamsFromArray(stringBuilder, MANUFACTURERS_IN_AND, productDto.getManufacturers());
                return;
            }
            handleParamsFromArray(stringBuilder, MANUFACTURERS_IN_WHERE, productDto.getManufacturers());
        }
    }

    private void buildSortDirectionByPrice(StringBuilder stringBuilder) {
        if (nonNull(productDto.getSortDirectionPrice()) && !productDto.getSortDirectionPrice().isEmpty()) {
            if (!flag) {
                flag = true;
                stringBuilder.append(ORDER_BY_PRICE_FIRST).append(productDto.getSortDirectionPrice());
                return;
            }
            stringBuilder.append(ORDER_BY_PRICE_SECOND).append(productDto.getSortDirectionPrice());
        }
    }

    private void buildSortDirectionByName(StringBuilder stringBuilder) {
        if (nonNull(productDto.getSortDirectionName()) && !productDto.getSortDirectionName().isEmpty()) {
            if (!flag) {
                flag = true;
                stringBuilder.append(ORDER_BY_NAME_FIRST).append(productDto.getSortDirectionName());
                return;
            }
            stringBuilder.append(ORDER_BY_NAME_SECOND).append(productDto.getSortDirectionName());
        }
    }

    private void buildLimit(StringBuilder stringBuilder) {
        parameters.add(productDto.getWantedItemsQuantity());
        parameters.add(productDto.getCurrentPage() * productDto.getWantedItemsQuantity() - productDto.getWantedItemsQuantity());
        stringBuilder.append(LIMIT_OFFSET);
    }

    private void handleParamsFromArray(StringBuilder stringBuilder, String query, String[] paramsArray) {
        stringBuilder.append(query);
        for (String s : paramsArray) {
            stringBuilder.append("?,");
            parameters.add(s);
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append(")");
    }

    private void buildCommonMethodsInvokeChain(StringBuilder queryBuilder, String query) {
        buildSearchByName(queryBuilder);
        buildSearchByMinPrice(queryBuilder, query);
        buildSearchByMaxPrice(queryBuilder, query);
        buildSearchByCategories(queryBuilder, query);
        buildSearchByManufacturers(queryBuilder, query);
        buildSortDirectionByPrice(queryBuilder);
        buildSortDirectionByName(queryBuilder);
    }

    /**
     * Builds and then returns StringBuilder instance which
     * contains ready for use SQL SELECT query with LIMIT
     * for the PreparedStatement.
     *
     * @return StringBuilder instance of the
     * SQL SELECT query with LIMIT
     */
    public StringBuilder getSelectLimitQueryBuilder() {
        buildCommonMethodsInvokeChain(queryBuilderSelectLimit, GET_ALL_PRODUCTS_PER_PAGE);
        buildLimit(queryBuilderSelectLimit);

        return queryBuilderSelectLimit;
    }

    /**
     * Builds and then returns StringBuilder instance which
     * contains ready for use SQL SELECT COUNT query
     * for the PreparedStatement.
     *
     * @return StringBuilder instance of the
     * SQL SELECT COUNT query
     */
    public StringBuilder getCountQueryBuilder() {
        buildCommonMethodsInvokeChain(queryBuilderCount, GET_FILTERED_PRODUCTS_COUNT);

        return queryBuilderCount;
    }
}