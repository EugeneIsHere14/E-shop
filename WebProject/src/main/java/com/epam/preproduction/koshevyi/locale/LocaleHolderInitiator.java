package com.epam.preproduction.koshevyi.locale;

import com.epam.preproduction.koshevyi.filter.SecurityFilter;
import com.epam.preproduction.koshevyi.locale.impl.CookieLocaleHolder;
import com.epam.preproduction.koshevyi.locale.impl.SessionLocaleHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.FilterConfig;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Class that chooses and sets up specified implementation of LocaleHolder interface.
 */
public class LocaleHolderInitiator {

    private static final Logger LOGGER = LogManager.getLogger(SecurityFilter.class);

    private LocaleHolder localeHolder;

    public LocaleHolderInitiator(FilterConfig config) {
        initLocaleHolder(config);
    }

    private void initLocaleHolder(FilterConfig config) {
        String localeHolderName = config.getInitParameter(INIT_PARAM_LOCALE_HOLDER_TYPE);
        if (SESSION_HOLDER.equalsIgnoreCase(localeHolderName)) {
            localeHolder = new SessionLocaleHolder();
        } else if (COOKIE_HOLDER.equalsIgnoreCase(localeHolderName)) {
            int cookieLifespan = Integer.parseInt(config.getServletContext().getInitParameter(INIT_PARAM_LOCALISATION_COOKIE_LIFESPAN));
            localeHolder = new CookieLocaleHolder(cookieLifespan);
        } else {
            LOGGER.error("Invalid or empty local holder param");
            throw new IllegalArgumentException("Invalid or empty local holder param");
        }
    }

    public LocaleHolder getLocaleHolder() {
        return localeHolder;
    }
}