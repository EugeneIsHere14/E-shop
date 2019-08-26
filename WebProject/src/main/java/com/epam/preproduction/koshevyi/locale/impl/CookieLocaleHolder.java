package com.epam.preproduction.koshevyi.locale.impl;

import com.epam.preproduction.koshevyi.constant.Constants;
import com.epam.preproduction.koshevyi.locale.LocaleHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public class CookieLocaleHolder implements LocaleHolder {

    private int cookieLifespan;

    public CookieLocaleHolder(int cookieLifespan) {
        this.cookieLifespan = cookieLifespan;
    }

    @Override
    public Optional<Locale> getLocale(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equalsIgnoreCase(Constants.LOCALE))
                .peek(cookie -> cookie.setMaxAge(cookieLifespan))
                .findFirst()
                .map(cookie -> new Locale(cookie.getValue()));
    }

    @Override
    public void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = new Cookie(Constants.LOCALE, locale.getLanguage());
        cookie.setMaxAge(cookieLifespan);
        response.addCookie(cookie);
    }
}