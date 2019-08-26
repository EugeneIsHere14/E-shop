package com.epam.preproduction.koshevyi.filter;

import com.epam.preproduction.koshevyi.entity.PageAccess;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.util.parser.SecurityParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.epam.preproduction.koshevyi.constant.Constants.*;

/**
 * Web-filter which provides controlled access
 * to different pages of web-application.
 */
public class SecurityFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(SecurityFilter.class);

    private List<PageAccess.Constraint> constraints;

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("SecurityFilter init() method worked");
        String securityFile = (String) filterConfig.getServletContext().getAttribute(SECURITY_FILE);
        try {
            constraints = SecurityParser.parseXML(securityFile);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error("Error occurred during parsing XML-file: " + e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Start SecurityFilter doFilter() method.");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        String URI = req.getRequestURI();
        User user = (User) session.getAttribute(CURRENT_USER_ATTR);

        for (PageAccess.Constraint constraint : constraints) {
            for (String s : constraint.getUrlPatterns()) {
                if (URI.contains(s)) {
                    if (Objects.isNull(user)) {
                        session.setAttribute(PAGE_AFTER_LOGIN_ATTR, URI);
                        req.getRequestDispatcher(LOGIN_SERVLET).forward(req, resp);
                        LOGGER.info("Finish SecurityFilter doFilter() method.");
                        return;
                    } else {
                        if (constraint.getRoles().contains(user.getRoleId())) {
                            chain.doFilter(req, resp);
                            return;
                        } else {
                            resp.sendRedirect(ILLEGAL_ACCESS_SERVLET);
                            LOGGER.info("Finish SecurityFilter doFilter() method.");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("SecurityFilter destroy() method worked");
    }
}