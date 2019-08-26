package com.epam.preproduction.koshevyi.captcha.remover;

import com.epam.preproduction.koshevyi.captcha.strategy.CaptchaStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

/**
 * This class is used for removing expired captchas from map.
 */
public class CaptchaRemover extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(CaptchaRemover.class);

    private static final String CAPTCHA_MAP_SIZE_MSG = "Captcha Map Size: {}";
    private static final String REMOVED_CAPTCHA_MSG = "Removed captcha (id = {})";
    private static final String CAPTCHA_LIFETIME_MSG = "Life time of captcha (id: {}) is {}";


    private long expireTime;
    private CaptchaStrategy captchaStrategy;

    public CaptchaRemover(Long expireTime, CaptchaStrategy captchaStrategy) {
        this.expireTime = expireTime;
        this.captchaStrategy = captchaStrategy;
    }

    @Override
    public void run() {
        Iterator<String> iterator = captchaStrategy.getCaptchas().keySet().iterator();

        while(iterator.hasNext()) {
            String captchaKey = iterator.next();

            long timeAlive = System.currentTimeMillis() - captchaStrategy.getCaptchas().get(captchaKey).getCreationTime();

            int captchaId = captchaStrategy.getCaptchas().get(captchaKey).getId();

            LOGGER.info(CAPTCHA_LIFETIME_MSG, captchaId, timeAlive);

            if(timeAlive > expireTime) {
                LOGGER.info(REMOVED_CAPTCHA_MSG, captchaId);
                iterator.remove();
            } else {
                break;
            }
        }
        LOGGER.info(CAPTCHA_MAP_SIZE_MSG, captchaStrategy.getCaptchas().size());
    }
}