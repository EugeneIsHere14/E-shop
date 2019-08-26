package com.epam.preproduction.koshevyi.captcha.strategy;

import com.epam.preproduction.koshevyi.captcha.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Abstract class which contains methods of getting and adding captcha
 * depending on chosen captcha strategy. Class also has common methods
 * for generating captcha and obtaining captcha map.
 */
public abstract class CaptchaStrategy {

    Map<String, Captcha> captchas = new LinkedHashMap<>();

    /**
     * Returns the map which contains captchas.
     *
     * @return the map which contains captchas
     */
    public Map<String, Captcha> getCaptchas() {
        return captchas;
    }

    /**
     * Generates and returns Captcha instance with random values.
     *
     * @return Captcha instance with random values
     */
    public Captcha generateNewCaptcha() {
        Random random = new Random();
        int id = Math.abs(random.nextInt(10_000));
        String value = String.valueOf(Math.abs(random.nextInt(10_000)));
        long creationTime = System.currentTimeMillis();
        return new Captcha(id, value, creationTime);
    }

    /**
     * Returns an instance of Captcha.
     *
     * @param request current HttpServletRequest
     * @return an instance of Captcha
     */
    public abstract Captcha getCaptcha(HttpServletRequest request);

    /**
     * Creates captcha and put it into captcha map.
     *
     * @param request current HttpServletRequest
     * @param response current HttpServletResponse
     */
    public abstract boolean addCaptcha(HttpServletRequest request, HttpServletResponse response);
}