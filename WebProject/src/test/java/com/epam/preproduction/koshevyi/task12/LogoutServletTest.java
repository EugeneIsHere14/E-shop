package com.epam.preproduction.koshevyi.task12;

import com.epam.preproduction.koshevyi.servlet.LogoutServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.epam.preproduction.koshevyi.constant.Constants.PAGE_MAIN;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LogoutServletTest {

    private LogoutServlet logoutServlet;
    @Mock
    private HttpSession httpSessionMock;
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;

    @Before
    public void init() {
        initMocks(this);

        logoutServlet = new LogoutServlet();
    }

    @Test
    public void shouldLogoutAndRedirectToHomePage() throws IOException {
        when(requestMock.getSession(false)).thenReturn(httpSessionMock);

        logoutServlet.doPost(requestMock, responseMock);
        verify(responseMock).sendRedirect(PAGE_MAIN);
    }
}