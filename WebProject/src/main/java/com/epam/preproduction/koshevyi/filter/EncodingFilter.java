package com.epam.preproduction.koshevyi.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.util.Objects;

import static com.epam.preproduction.koshevyi.constant.Constants.INIT_PARAM_ENCODING;

/**
 * Web-filter which sets up encoding for the application.
 */
public class EncodingFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter(INIT_PARAM_ENCODING);

        LOGGER.info("SecurityFilter init() method worked");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (Objects.isNull(request.getCharacterEncoding())) {
            LOGGER.info("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("SecurityFilter destroy() method worked");
    }
}