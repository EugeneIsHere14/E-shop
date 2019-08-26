package com.epam.preproduction.koshevyi.captcha.strategy;

import com.epam.preproduction.koshevyi.captcha.entity.Captcha;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.preproduction.koshevyi.constant.Constants.CAPTCHA_ID;

public class CookieCaptchaStrategy extends CaptchaStrategy {
    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        return getCaptchaFromCookies(cookies);
    }

    @Override
    public boolean addCaptcha(HttpServletRequest request, HttpServletResponse response) {
        Captcha captcha = generateNewCaptcha();
        captchas.put(String.valueOf(captcha.getId()), captcha);
        Cookie cookie = new Cookie(CAPTCHA_ID, String.valueOf(captcha.getId()));
        response.addCookie(cookie);
        return true;
    }

    private Captcha getCaptchaFromCookies(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CAPTCHA_ID)) {
                return captchas.get(cookie.getValue());
            }
        }
        return null;
    }
}