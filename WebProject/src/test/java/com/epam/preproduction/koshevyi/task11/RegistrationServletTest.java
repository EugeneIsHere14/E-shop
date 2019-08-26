package com.epam.preproduction.koshevyi.task11;

import com.epam.preproduction.koshevyi.captcha.entity.Captcha;
import com.epam.preproduction.koshevyi.captcha.strategy.CaptchaStrategy;
import com.epam.preproduction.koshevyi.dto.UserDto;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.listener.ContextListener;
import com.epam.preproduction.koshevyi.service.UserService;
import com.epam.preproduction.koshevyi.servlet.RegistrationServlet;
import com.epam.preproduction.koshevyi.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static com.epam.preproduction.koshevyi.constant.Constants.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegistrationServletTest {

    private RegistrationServlet registrationServlet;
    private Map<String, String> messageMap = new HashMap<>();
    private Map<String, Captcha> captchaMap = new LinkedHashMap<>();
    private List<User> userList = new ArrayList<>();
    private UserValidator userValidatorSpy;
    private String applicationPath = "D:\\WebProject (2)\\WebProject\\src\\main\\webapp";

    @Mock
    private UserService userServiceMock;
    @Mock
    private CaptchaStrategy captchaStrategyMock;
    @Mock
    private HttpServletRequest requestMock;
    @Mock
    private HttpServletResponse responseMock;
    @Mock
    private ServletConfig servletConfigMock;
    @Mock
    private ServletContext servletContextMock;
    @Mock
    private ServletContextEvent servletContextEventMock;
    @Mock
    private HttpSession httpSessionMock;
    @Mock
    private RequestDispatcher requestDispatcherMock;

    private UserDto userDTO = new UserDto("ivan@mail.ru", "ivanchik", "User User", "vbnJl_9z", "vbnJl_9z", " ", null);

    @Before
    public void init() {
        initMocks(this);

        Captcha captcha = new Captcha(123, "3457", System.currentTimeMillis());

        User user = userDTO.createNewUser();
        userList.add(user);
        captchaMap.put(String.valueOf(captcha.getId()), captcha);

        when(servletConfigMock.getServletContext()).thenReturn(servletContextMock);
        when(requestMock.getServletContext()).thenReturn(servletContextMock);
        when(requestMock.getSession()).thenReturn(httpSessionMock);
        when(servletContextEventMock.getServletContext()).thenReturn(servletContextMock);
        when(servletContextMock.getInitParameter(INIT_PARAM_CAPTCHA_STORING)).thenReturn("session");
        when(servletContextMock.getInitParameter(INIT_PARAM_CAPTCHA_EXPIRE_TIME)).thenReturn("1000000");
        new ContextListener().contextInitialized(servletContextEventMock);

        when(servletContextMock.getAttribute(USER_SERVICE_CONTEXT_ATTR)).thenReturn(userServiceMock);
        userValidatorSpy = spy(new UserValidator(userServiceMock));
        when(servletContextMock.getAttribute(USER_VALIDATOR_CONTEXT_ATTR)).thenReturn(userValidatorSpy);
        when(userValidatorSpy.getErrorMessageMap()).thenReturn(messageMap);
        when(servletContextMock.getAttribute(CAPTCHA_STRATEGY_CONTEXT_ATTR)).thenReturn(captchaStrategyMock);
        when(servletContextMock.getAttribute(CAPTCHA_EXPIRE_TIME)).thenReturn(1000000L);

        when(requestMock.getSession().getServletContext()).thenReturn(servletContextMock);
        when(servletContextMock.getRealPath("")).thenReturn(applicationPath);

        when(userServiceMock.getAllUsers()).thenReturn(userList);
        when(userServiceMock.addNewUser(user)).thenReturn(true);

        when(captchaStrategyMock.addCaptcha(requestMock, responseMock)).thenReturn(true);
        when(captchaStrategyMock.getCaptchas()).thenReturn(captchaMap);
        when(captchaStrategyMock.getCaptcha(requestMock)).thenReturn(new Captcha(123, "3457", System.currentTimeMillis()));

        registrationServlet = new RegistrationServlet();
        registrationServlet.init(servletConfigMock);
    }

    @Test
    public void shouldGetRequestDispatcherWhenDoGet() throws ServletException, IOException {
        when(requestMock.getRequestDispatcher(REGISTRATION_PAGE)).thenReturn(requestDispatcherMock);
        registrationServlet.doGet(requestMock, responseMock);

        verify(requestMock).getRequestDispatcher(REGISTRATION_PAGE);
    }

    @Test
    public void shouldRedirectToRegistrationWhenCaptchaIsIncorrect() throws IOException {
        when(requestMock.getParameter(USER_EMAIL)).thenReturn("fdgdfg@gmail.com");
        when(requestMock.getParameter(USER_LOGIN)).thenReturn("gfhfjgerg");
        when(requestMock.getParameter(USER_NAME)).thenReturn("Ivan Oyvan");
        when(requestMock.getParameter(USER_PASSWORD)).thenReturn("vbnJl_9z");
        when(requestMock.getParameter(USER_CONFIRM_PASSWORD)).thenReturn("vbnJl_9z");
        when(requestMock.getParameter(USER_SUBSCRIPTION1)).thenReturn("");
        when(requestMock.getParameter(USER_SUBSCRIPTION2)).thenReturn(null);

        User user = userDTO.createNewUser();

        when(userServiceMock.findUserByLogin(userDTO.getLogin())).thenReturn(user);
        when(requestMock.getParameter(TAG_CAPTCHA_INPUT_VALUE)).thenReturn("5452");

        registrationServlet.doPost(requestMock, responseMock);
        verify(responseMock).sendRedirect(REGISTRATION_SERVLET);
    }

    @Test
    public void shouldRedirectToRegistrationWhenUserWithTheSameLoginExists() throws IOException {
        when(requestMock.getParameter(USER_EMAIL)).thenReturn(userDTO.getEmail());
        when(requestMock.getParameter(USER_LOGIN)).thenReturn(userDTO.getLogin());
        when(requestMock.getParameter(USER_NAME)).thenReturn(userDTO.getName());
        when(requestMock.getParameter(USER_PASSWORD)).thenReturn(userDTO.getPassword());
        when(requestMock.getParameter(USER_CONFIRM_PASSWORD)).thenReturn(userDTO.getConfirmPassword());
        when(requestMock.getParameter(USER_SUBSCRIPTION1)).thenReturn(userDTO.getSalesSubscription());
        when(requestMock.getParameter(USER_SUBSCRIPTION2)).thenReturn(userDTO.getGoodsSubscription());

        User user = userDTO.createNewUser();

        when(userServiceMock.findUserByLogin(userDTO.getLogin())).thenReturn(user);

        registrationServlet.doPost(requestMock, responseMock);
        verify(responseMock).sendRedirect(REGISTRATION_SERVLET);
    }

    @Test
    public void shouldRedirectToRegistrationWhenDataIsNotValid() throws IOException {
        when(requestMock.getParameter(USER_EMAIL)).thenReturn("sdfgf.");
        when(requestMock.getParameter(USER_LOGIN)).thenReturn("ivanushka");
        when(requestMock.getParameter(USER_NAME)).thenReturn(userDTO.getName());
        when(requestMock.getParameter(USER_PASSWORD)).thenReturn(userDTO.getPassword());
        when(requestMock.getParameter(USER_CONFIRM_PASSWORD)).thenReturn(userDTO.getConfirmPassword());
        when(requestMock.getParameter(USER_SUBSCRIPTION1)).thenReturn(userDTO.getSalesSubscription());
        when(requestMock.getParameter(USER_SUBSCRIPTION2)).thenReturn(userDTO.getGoodsSubscription());

        User user = userDTO.createNewUser();

        when(userServiceMock.findUserByLogin(userDTO.getLogin())).thenReturn(user);
        when(requestMock.getParameter(TAG_CAPTCHA_INPUT_VALUE)).thenReturn("3457");

        registrationServlet.doPost(requestMock, responseMock);
        verify(responseMock).sendRedirect(REGISTRATION_SERVLET);
    }

    @Test
    public void shouldAddNewUserWhenDataIsValid() throws IOException {
        when(requestMock.getParameter(USER_EMAIL)).thenReturn("fdgdfg@gmail.com");
        when(requestMock.getParameter(USER_LOGIN)).thenReturn("gfhfjgerg");
        when(requestMock.getParameter(USER_NAME)).thenReturn("Ivan Oyvan");
        when(requestMock.getParameter(USER_PASSWORD)).thenReturn("vbnJl_9z");
        when(requestMock.getParameter(USER_CONFIRM_PASSWORD)).thenReturn("vbnJl_9z");
        when(requestMock.getParameter(USER_SUBSCRIPTION1)).thenReturn("");
        when(requestMock.getParameter(USER_SUBSCRIPTION2)).thenReturn(null);

        User user = userDTO.createNewUser();

        when(userServiceMock.findUserByLogin(userDTO.getLogin())).thenReturn(user);
        when(requestMock.getParameter(TAG_CAPTCHA_INPUT_VALUE)).thenReturn("3457");

        registrationServlet.doPost(requestMock, responseMock);
    }
}