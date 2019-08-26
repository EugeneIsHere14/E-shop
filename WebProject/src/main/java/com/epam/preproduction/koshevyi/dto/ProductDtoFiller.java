package com.epam.preproduction.koshevyi.dto;

import javax.servlet.http.HttpServletRequest;

/**
 * This class has method for filling in ProductDto
 * instance by taking values from the form which
 * user fills.
 */
public class ProductDtoFiller {

    private static final String ITEM_NAME = "ItemName";
    private static final String MIN_PRICE = "PriceFrom";
    private static final String MAX_PRICE = "PriceTo";
    private static final String CATEGORY = "CategoryName";
    private static final String MANUFACTURER = "ManufName";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String ITEMS_NUM_PER_PAGE = "ItemsNumber";
    private static final String SORT_BY_NAME = "sortName";
    private static final String SORT_BY_PRICE = "sortPrice";

    /**
     * Fills with values from HttpServletRequest
     * and returns the ProductDto instance.
     *
     * @param req HttpServletRequest instance
     * @return returns the ProductDto instance
     */
    public ProductDto fillDto(HttpServletRequest req) {
        String name = req.getParameter(ITEM_NAME);
        String minPrice = req.getParameter(MIN_PRICE);
        String maxPrice = req.getParameter(MAX_PRICE);
        String[] categories = req.getParameterValues(CATEGORY);
        String[] manufacturers = req.getParameterValues(MANUFACTURER);
        int currentPage = parseRequest(req, CURRENT_PAGE, 1);
        int itemsQuantity = parseRequest(req, ITEMS_NUM_PER_PAGE, 3);
        String sortDirectionName = req.getParameter(SORT_BY_NAME);
        String sortDirectionPrice = req.getParameter(SORT_BY_PRICE);

        return new ProductDto(name, minPrice, maxPrice, categories, manufacturers, currentPage, itemsQuantity,
                sortDirectionName, sortDirectionPrice);
    }

    private int parseRequest(HttpServletRequest req, String param, int defaultParam) {
        int result;
        try {
            result = Integer.parseInt(req.getParameter(param));
        } catch (NumberFormatException ex) {
            result = defaultParam;
        }
        return result;
    }
}