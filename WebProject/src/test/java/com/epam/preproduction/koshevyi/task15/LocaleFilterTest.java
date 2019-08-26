package com.epam.preproduction.koshevyi.task15;

import com.epam.preproduction.koshevyi.filter.LocaleFilter;
import com.epam.preproduction.koshevyi.locale.LocaleRequestWrapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import static com.epam.preproduction.koshevyi.constant.Constants.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class LocaleFilterTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext servletContext;

    private String lang;
    private Locale expectedLocale;
    public LocaleFilterTest(String lang, Locale expectedLocale) {
        this.lang = lang;
        this.expectedLocale = expectedLocale;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> provideDataForLocaleTest() {
        return Arrays.asList(new Object[][]{
                {null, new Locale("en")},
                {"ru", new Locale("ru")},
                {null, new Locale("ru")},
                {"en", new Locale("en")},
        });
    }


    @Test
    public void shouldSetLocalesBasedOnProvidedData() throws IOException, ServletException {
        when(filterConfig.getInitParameter(INIT_PARAM_DEFAULT_LOCALE)).thenReturn("en");
        when(filterConfig.getInitParameter(INIT_PARAM_SUPPORTED_LOCALES)).thenReturn("ru,en");
        when(filterConfig.getInitParameter(INIT_PARAM_LOCALE_HOLDER_TYPE)).thenReturn(SESSION_HOLDER);
        when(filterConfig.getServletContext()).thenReturn(servletContext);
        when(request.getParameter(LANGUAGE)).thenReturn(lang);
        when(request.getLocales()).thenReturn(Collections.enumeration(Collections.singletonList(expectedLocale)));
        when(request.getSession()).thenReturn(session);

        LocaleFilter localeFilter = new LocaleFilter();
        localeFilter.init(filterConfig);
        localeFilter.doFilter(request, response, chain);
        HttpServletRequestWrapper wrapper = new LocaleRequestWrapper(request, expectedLocale);
        verify(chain).doFilter(wrapper, response);
    }
}