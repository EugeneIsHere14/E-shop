package com.epam.preproduction.koshevyi.locale.impl;

import com.epam.preproduction.koshevyi.constant.Constants;
import com.epam.preproduction.koshevyi.locale.LocaleHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

public class SessionLocaleHolder implements LocaleHolder {

    @Override
    public Optional<Locale> getLocale(HttpServletRequest request) {
        return Optional.ofNullable((Locale) request.getSession().getAttribute(Constants.LOCALE));
    }

    @Override
    public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        request.getSession().setAttribute(Constants.LOCALE, locale);
    }
}