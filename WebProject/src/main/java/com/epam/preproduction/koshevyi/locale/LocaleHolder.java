package com.epam.preproduction.koshevyi.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

/**
 * This interface contains methods for retrieving from request and storing locales.
 */
public interface LocaleHolder {

    /**
     * Extract and returns specified Locale instance.
     *
     * @param request HttpServletRequest instance
     * @return specified Locale instance
     */
    Optional<Locale> getLocale(HttpServletRequest request);

    /**
     * Saves Locale instance.
     *
     * @param request HttpServletRequest instance
     * @param response HttpServletResponse instance
     * @param locale Locale instance
     */
    void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale);
}