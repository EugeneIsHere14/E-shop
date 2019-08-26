package com.epam.preproduction.koshevyi.captcha.strategy;

import com.epam.preproduction.koshevyi.captcha.entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.preproduction.koshevyi.constant.Constants.CAPTCHA_ID;

public class HiddenFieldCaptchaStrategy extends CaptchaStrategy {
    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        String captchaId = String.valueOf(request.getServletContext().getAttribute(CAPTCHA_ID));
        return captchas.get(captchaId);
    }

    @Override
    public boolean addCaptcha(HttpServletRequest request, HttpServletResponse response) {
        Captcha captcha = generateNewCaptcha();
        captchas.put(String.valueOf(captcha.getId()), captcha);
        request.getServletContext().setAttribute(CAPTCHA_ID, captcha.getId());
        return true;
    }
}