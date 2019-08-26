package com.epam.preproduction.koshevyi.captcha.provider;

import com.epam.preproduction.koshevyi.captcha.strategy.CaptchaStrategy;
import com.epam.preproduction.koshevyi.captcha.strategy.CookieCaptchaStrategy;
import com.epam.preproduction.koshevyi.captcha.strategy.HiddenFieldCaptchaStrategy;
import com.epam.preproduction.koshevyi.captcha.strategy.SessionCaptchaStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides certain implementation of
 * captcha storing strategy.
 */
public class CaptchaStrategyProvider {

    private Map<String, CaptchaStrategy> strategies = new HashMap<>();

    public CaptchaStrategyProvider() {
        strategies.put("session", new SessionCaptchaStrategy());
        strategies.put("field", new HiddenFieldCaptchaStrategy());
        strategies.put("cookie", new CookieCaptchaStrategy());
    }

    /**
     * Returns an instance of CaptchaStrategy.
     *
     * @param strategyName short name of strategy
     * @return an instance of CaptchaStrategy
     */
    public CaptchaStrategy getCaptchaStrategy(String strategyName) {
        return strategies.get(strategyName);
    }
}