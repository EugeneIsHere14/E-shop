package com.epam.preproduction.koshevyi.filter;

import com.epam.preproduction.koshevyi.gzip.GZipServletResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Web-filter which compresses response body of JSPs.
 */
public class GZipFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(GZipFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("GZipFilter init() method worked");
    }

    @Override
    public void destroy() {
        LOGGER.info("GZipFilter destroy() method worked");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("GZipFilter doFilter() method");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (acceptsGZipEncoding(request) && isResponseTypeText(request)) {
            response.addHeader("Content-Encoding", "gzip");
            GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(response);
            filterChain.doFilter(servletRequest, gzipResponse);
            gzipResponse.close();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean acceptsGZipEncoding(HttpServletRequest httpRequest) {
        String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
        return Objects.nonNull(acceptEncoding) && acceptEncoding.contains("gzip");
    }

    private boolean isResponseTypeText(HttpServletRequest httpRequest) {
        String accept = httpRequest.getHeader("Accept");
        return Objects.nonNull(accept) && accept.contains("text");
    }
}