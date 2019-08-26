package com.epam.preproduction.koshevyi.listener;

import com.epam.preproduction.koshevyi.captcha.provider.CaptchaStrategyProvider;
import com.epam.preproduction.koshevyi.captcha.remover.CaptchaRemover;
import com.epam.preproduction.koshevyi.captcha.strategy.CaptchaStrategy;
import com.epam.preproduction.koshevyi.db.TransactionManager;
import com.epam.preproduction.koshevyi.repository.*;
import com.epam.preproduction.koshevyi.repository.impl.*;
import com.epam.preproduction.koshevyi.service.*;
import com.epam.preproduction.koshevyi.service.impl.*;
import com.epam.preproduction.koshevyi.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * This class initializes modules of applications.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Servlet context initialization starts");

        ServletContext servletContext = sce.getServletContext();

        TransactionManager transactionManager = new TransactionManager();
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository, transactionManager);
        UserValidator userValidator = new UserValidator(userService);

        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl(productRepository, transactionManager);

        ManufacturerRepository manufacturerRepository = new ManufacturerRepositoryImpl();
        ManufacturerService manufacturerService = new ManufacturerServiceImpl(manufacturerRepository, transactionManager);

        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
        CategoryService categoryService = new CategoryServiceImpl(categoryRepository, transactionManager);

        OrderRepository orderRepository = new OrderRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository, transactionManager);

        String captchaStrategyName = servletContext.getInitParameter(INIT_PARAM_CAPTCHA_STORING);
        CaptchaStrategy captchaStrategy = new CaptchaStrategyProvider().getCaptchaStrategy(captchaStrategyName);

        long expireTime = Long.parseLong(servletContext.getInitParameter(INIT_PARAM_CAPTCHA_EXPIRE_TIME));

        String securityFile = servletContext.getInitParameter(INIT_PARAM_SECURITY_FILE);

        servletContext.setAttribute(USER_VALIDATOR_CONTEXT_ATTR, userValidator);
        servletContext.setAttribute(USER_SERVICE_CONTEXT_ATTR, userService);
        servletContext.setAttribute(PRODUCT_SERVICE_CONTEXT_ATTR, productService);
        servletContext.setAttribute(MANUFACTURER_SERVICE_CONTEXT_ATTR, manufacturerService);
        servletContext.setAttribute(CATEGORY_SERVICE_CONTEXT_ATTR, categoryService);
        servletContext.setAttribute(ORDER_SERVICE_CONTEXT_ATTR, orderService);
        servletContext.setAttribute(CAPTCHA_STRATEGY_CONTEXT_ATTR, captchaStrategy);
        servletContext.setAttribute(CAPTCHA_EXPIRE_TIME, expireTime);
        servletContext.setAttribute(SECURITY_FILE, securityFile);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        CaptchaRemover captchaRemover = new CaptchaRemover(expireTime, captchaStrategy);
        scheduledExecutorService.scheduleAtFixedRate(captchaRemover, 0, expireTime, TimeUnit.MILLISECONDS);

        LOGGER.info("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("ContextListener contextDestroyed() method finished");
    }
}