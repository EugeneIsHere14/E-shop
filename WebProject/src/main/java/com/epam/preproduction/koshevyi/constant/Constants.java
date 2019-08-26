package com.epam.preproduction.koshevyi.constant;

/**
 * This class defines constants which
 * are used over the application.
 */
public class Constants {

    //pages
    public static final String PAGE_MAIN = "index.jsp";
    public static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration.jsp";
    public static final String PRODUCT_PAGE = "WEB-INF/jsp/products.jsp";
    public static final String CART_PAGE = "WEB-INF/jsp/cart.jsp";
    public static final String ORDER_PAGE = "WEB-INF/jsp/order.jsp";
    public static final String ORDER_HISTORY_PAGE = "WEB-INF/jsp/orderHistory.jsp";
    public static final String ILLEGAL_ACCESS_PAGE = "WEB-INF/jsp/illegalAccessPage.jsp";
    public static final String LOGIN_PAGE = "WEB-INF/jsp/loginPage.jsp";
    public static final String ADMIN_PAGE = "WEB-INF/jsp/adminPage.jsp";

    //servlets
    public static final String REGISTRATION_SERVLET = "registration";
    public static final String LOGIN_SERVLET = "login";
    public static final String ORDER_HISTORY_SERVLET = "orderHistory";
    public static final String ILLEGAL_ACCESS_SERVLET = "illegalAccess";

    public static final String USER_EMAIL = "E-mail";
    public static final String USER_LOGIN = "Login";
    public static final String USER_NAME = "Name";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_CONFIRM_PASSWORD = "Confirm Password";
    public static final String USER_SUBSCRIPTION1 = "Subscription1";
    public static final String USER_SUBSCRIPTION2 = "Subscription2";

    public static final String USER_SERVICE_CONTEXT_ATTR = "userService";
    public static final String USER_VALIDATOR_CONTEXT_ATTR = "validator";
    public static final String CAPTCHA_STRATEGY_CONTEXT_ATTR = "captchaAttr";
    public static final String PRODUCT_SERVICE_CONTEXT_ATTR = "productService";
    public static final String MANUFACTURER_SERVICE_CONTEXT_ATTR = "manufacturerService";
    public static final String CATEGORY_SERVICE_CONTEXT_ATTR = "categoryService";
    public static final String ORDER_SERVICE_CONTEXT_ATTR = "orderService";

    public static final String INIT_PARAM_CAPTCHA_STORING = "CaptchaStore";
    public static final String INIT_PARAM_CAPTCHA_EXPIRE_TIME = "CaptchaExpireTime";
    public static final String INIT_PARAM_SECURITY_FILE = "SecurityFile";
    public static final String INIT_PARAM_ENCODING = "encoding";
    public static final String INIT_PARAM_DEFAULT_LOCALE = "DefaultLocale";
    public static final String INIT_PARAM_SUPPORTED_LOCALES = "SupportLocales";
    public static final String INIT_PARAM_LOCALISATION_COOKIE_LIFESPAN = "LocalisationCookieLifespan";
    public static final String INIT_PARAM_LOCALE_HOLDER_TYPE = "LocaleHolder";

    public static final String CAPTCHA_ID = "captchaId";
    public static final String CAPTCHA_EXPIRE_TIME = "expTime";
    public static final String SECURITY_FILE = "securityFile";

    public static final String TAG_CAPTCHA_INPUT_VALUE = "Captcha Value";

    public static final String CHECKBOX_VALUE = "checked";

    public static final String UPLOAD_DIR = "avatars";

    //Form fields attributes
    public static final String CAPTCHA_ERR_ATTR = "CaptchaError";
    public static final String PASSWORD_MISMATCH_ATTR = "PasswordMismatch";
    public static final String LOGIN_MISMATCH_ATTR = "LoginMismatch";
    public static final String EMAIL_ATTR = "EmailValue";
    public static final String LOGIN_ATTR = "LoginValue";
    public static final String NAME_ATTR = "NameValue";
    public static final String SUBSCR1_ATTR = "mailing1";
    public static final String SUBSCR2_ATTR = "mailing2";
    public static final String FALSE_LOGIN_ATTR = "FalseLogin";
    public static final String FALSE_PASSWORD_ATTR = "FalsePassword";
    public static final String ALERT_EXPIRE_ATTR = "expireAlert";
    public static final String USER_NAME_ATTR = "Email";

    public static final String CURRENT_USER_ATTR = "user";
    public static final String AVATAR_ATTR = "avatar";

    public static final String PAGE_AFTER_LOGIN_ATTR = "pageAfterLogin";

    //Shopping cart
    public static final String CART = "cart";
    public static final String CART_PRODUCT_ID = "prodId";
    public static final String TOTAL_PRICE = "totalPrice";
    public static final String ALL_ITEMS_QUANTITY = "allItemsQuantity";
    public static final String ITEM_QUANTITY = "ItemQuantity";

    //Order
    public static final String PAY_TYPE = "payType";
    public static final String DELIVERY_ADDRESS = "deliveryAddress";
    public static final String CARD_NUMBER = "cardNumber";
    public static final String TOTAL_ORDER_COST = "totalOrderCost";
    public static final String TOTAL_ORDER_ITEM_QTY = "totalOrderItemQty";
    public static final String STATUS_DESC = "statusDescription";
    public static final String ORDER_STATUS = "status";
    public static final String ORDER_ITEMS = "orderItems";

    public static final String SUPPORTED_LOCALES = "supportLocales";
    public static final String LOCALE = "locale";
    public static final String LANGUAGE = "lang";
    public static final String QUERY_STRING = "queryString";
    public static final String COOKIE_HOLDER = "cookie";
    public static final String SESSION_HOLDER = "session";
}