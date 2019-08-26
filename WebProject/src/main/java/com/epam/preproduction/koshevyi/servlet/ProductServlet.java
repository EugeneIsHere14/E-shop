package com.epam.preproduction.koshevyi.servlet;

import com.epam.preproduction.koshevyi.bean.ProductBean;
import com.epam.preproduction.koshevyi.dto.ProductDto;
import com.epam.preproduction.koshevyi.dto.ProductDtoFiller;
import com.epam.preproduction.koshevyi.service.CategoryService;
import com.epam.preproduction.koshevyi.service.ManufacturerService;
import com.epam.preproduction.koshevyi.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Servlet for product page view.
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ProductServlet.class);

    private static final String MANUFACTURERS_ATTR = "manufacturers";
    private static final String CATEGORIES_ATTR = "categories";
    private static final String PRODUCTS_ATTR = "products";
    private static final String CURRENT_PAGE_ATTR = "currentPage";
    private static final String PAGE_QUANTITY_ATTR = "pageQuantity";
    private static final String FILTER_ADDRESS_BAR_ATTR = "addressBar";

    private ProductService productService;
    private List<String> manufacturers;
    private List<String> categories;
    private int itemsQuantity;

    @Override
    public void init(ServletConfig config) {
        productService = (ProductService) config.getServletContext().getAttribute(PRODUCT_SERVICE_CONTEXT_ATTR);
        CategoryService categoryService = (CategoryService) config.getServletContext().getAttribute(CATEGORY_SERVICE_CONTEXT_ATTR);
        ManufacturerService manufacturerService = (ManufacturerService) config.getServletContext().getAttribute(MANUFACTURER_SERVICE_CONTEXT_ATTR);

        manufacturers = manufacturerService.getAllManufacturers();
        categories = categoryService.getAllCategories();
        itemsQuantity = productService.getProductsQuantity();

        LOGGER.info("Product Servlet was initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute(MANUFACTURERS_ATTR, manufacturers);
        req.setAttribute(CATEGORIES_ATTR, categories);

        ProductDtoFiller dtoFiller = new ProductDtoFiller();
        ProductDto productDto = dtoFiller.fillDto(req);

        int currentPage;
        if (Objects.isNull(req.getParameter(CURRENT_PAGE_ATTR))) {
            currentPage = 1;
        } else {
            try {
                currentPage = Integer.parseInt(req.getParameter(CURRENT_PAGE_ATTR));
            } catch (NumberFormatException ex) {
                currentPage = 1;
            }
        }

        int wantedItemQuantityPerPage = productDto.getWantedItemsQuantity();

        itemsQuantity= productService.getFilteredProductsQuantity(productDto);

        double doubleItemsQuantity = itemsQuantity;
        double result = (doubleItemsQuantity / wantedItemQuantityPerPage);
        int resultInt = (int) result;

        int pageQuantity = (itemsQuantity / wantedItemQuantityPerPage);
        if (result - resultInt > 0) {
            pageQuantity++;
        }

        List<ProductBean> productBeanList = productService.getAllProductsPerPage(productDto);
        req.setAttribute(PRODUCTS_ATTR, productBeanList);
        req.setAttribute(CURRENT_PAGE_ATTR, currentPage);
        req.setAttribute(PAGE_QUANTITY_ATTR, pageQuantity);

        getAddressBarString(req);

        req.getRequestDispatcher(PRODUCT_PAGE).forward(req, resp);
    }

    private void getAddressBarString(HttpServletRequest req) {
        String addressBarValue = req.getQueryString();
        if (Objects.nonNull(addressBarValue) && !addressBarValue.isEmpty()) {
            addressBarValue = addressBarValue.replaceFirst("&currentPage=\\d+?", "");
        }
        req.setAttribute(FILTER_ADDRESS_BAR_ATTR, addressBarValue);
    }
}