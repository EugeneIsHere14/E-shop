package com.epam.preproduction.koshevyi.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Web-filter which turns off caching.
 */
public class NoCacheFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(NoCacheFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("NoCacheFilter init() method worked");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("NoCacheFilter destroy() method worked");
    }
}