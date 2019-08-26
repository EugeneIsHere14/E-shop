package com.epam.preproduction.koshevyi.task12;

import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.service.UserService;
import com.epam.preproduction.koshevyi.servlet.LoginServlet;
import com.epam.preproduction.koshevyi.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.epam.preproduction.koshevyi.constant.Constants.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginServletTest {

    private Map<String, String> messageMap = new HashMap<>();
    private LoginServlet loginServlet;
    private String applicationPath = "D:\\WebProject (2)\\WebProject\\src\\main\\webapp";
    private User user = new User("ivan@mail.ru", "login3", "User User", "XzZ3ZGZLamhpdWs3", " ", null, 2);

    @Mock
    private UserService userServiceMock;
    @Mock
    private HttpSession httpSessionMock;
    @Mock
    private ServletConfig servletConfigMock;
    @Mock
    private ServletContext servletContextMock;
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private UserValidator userValidatorMock;


    @Before
    public void init() {
        initMocks(this);

        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
        when(requestMock.getServletContext()).thenReturn(servletContextMock);
        when(requestMock.getSession()).thenReturn(httpSessionMock);
        when(requestMock.getSession().getServletContext()).thenReturn(servletContextMock);

        when(servletContextMock.getRealPath("")).thenReturn(applicationPath);

        when(servletContextMock.getAttribute(USER_SERVICE_CONTEXT_ATTR)).thenReturn(userServiceMock);
        when(servletContextMock.getAttribute(USER_VALIDATOR_CONTEXT_ATTR)).thenReturn(userValidatorMock);
        when(userValidatorMock.getErrorMessageMap()).thenReturn(messageMap);

        loginServlet = new LoginServlet();
        loginServlet.init(servletConfigMock);
    }

    @Test
    public void shouldSuccessfullyLoginAndRedirectToMainPage() throws IOException {
        when(requestMock.getParameter("LoginUp")).thenReturn("login3");
        when(requestMock.getParameter("PasswordUp")).thenReturn("_6wdfKjhiuk7");

        when(userServiceMock.findUserByLogin(requestMock.getParameter("LoginUp"))).thenReturn(user);

        loginServlet.doPost(requestMock, responseMock);
        verify(responseMock).sendRedirect(PAGE_MAIN);
    }

    @Test
    public void shouldFailLoginAndRedirectToRegistrationServlet() throws IOException {
        when(requestMock.getParameter("LoginUp")).thenReturn("login3");
        when(requestMock.getParameter("PasswordUp")).thenReturn("vbnJl_9z");

        when(userServiceMock.findUserByLogin(requestMock.getParameter("LoginUp"))).thenReturn(null);

        loginServlet.doPost(requestMock, responseMock);
        verify(responseMock).sendRedirect(REGISTRATION_SERVLET);
    }
}