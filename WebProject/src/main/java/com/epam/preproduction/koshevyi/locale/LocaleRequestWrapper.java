package com.epam.preproduction.koshevyi.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;

/**
 * This class provides wrapper for Locale request.
 */
public class LocaleRequestWrapper extends HttpServletRequestWrapper {

    private final Locale locale;

    public LocaleRequestWrapper(HttpServletRequest req, Locale locale) {
        super(req);
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return Collections.enumeration(Collections.singletonList(locale));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocaleRequestWrapper that = (LocaleRequestWrapper) o;
        return Objects.equals(locale, that.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locale);
    }
}