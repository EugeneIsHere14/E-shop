package com.epam.preproduction.koshevyi.filter;

import com.epam.preproduction.koshevyi.locale.LocaleHolder;
import com.epam.preproduction.koshevyi.locale.LocaleHolderInitiator;
import com.epam.preproduction.koshevyi.locale.LocaleRequestWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Web-filter which provides localization of the application.
 */
public class LocaleFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(LocaleFilter.class);

    private Locale defaultLocale;
    private List<String> supportedLocales;
    private LocaleHolder localeHolder;

    @Override
    public void init(FilterConfig config) {
        LOGGER.info("LocaleFilter init method start");
        defaultLocale = new Locale(config.getInitParameter(INIT_PARAM_DEFAULT_LOCALE));
        initSupportedLocales(config);
        localeHolder = new LocaleHolderInitiator(config).getLocaleHolder();
        config.getServletContext().setAttribute(SUPPORTED_LOCALES, supportedLocales);
        LOGGER.info("LocaleFilter init method end");
    }

    private void initSupportedLocales(FilterConfig config) {
        supportedLocales = new ArrayList<>();
        String locales = config.getInitParameter(INIT_PARAM_SUPPORTED_LOCALES);
        supportedLocales.addAll(Arrays.asList(locales.split(",")));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        LOGGER.debug("Original request locale: " + request.getLocale());
        LOGGER.debug("Session locale: " + request.getSession().getAttribute(LOCALE));
        Locale locale = getLocaleFromRequest(request);
        localeHolder.saveLocale(request, response, locale);
        HttpServletRequestWrapper wrapper = new LocaleRequestWrapper(request, locale);

        removeLocaleFromRequest(request);
        filterChain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("LocaleFilter destroy method");
    }

    private Locale getLocaleFromRequest(HttpServletRequest request) {
        String locale = request.getParameter(LANGUAGE);
        if (supportedLocales.contains(locale)) {
            return new Locale(locale);
        }
        return localeHolder.getLocale(request).orElse(getAcceptedLocale(request).orElse(defaultLocale));
    }

    private Optional<Locale> getAcceptedLocale(HttpServletRequest request) {
        return Collections.list(request.getLocales()).stream().map(Locale::getLanguage)
                .filter(supportedLocales::contains)
                .findFirst().map(Locale::new);
    }

    private void removeLocaleFromRequest(HttpServletRequest request) {
        if (Objects.nonNull(request.getQueryString())) {
            request.setAttribute(QUERY_STRING, request.getQueryString().replaceFirst("&?lang=\\w+(?=&|\\s*)", ""));
        } else {
            request.setAttribute(QUERY_STRING, request.getQueryString());
        }
    }
}