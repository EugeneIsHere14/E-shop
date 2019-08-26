package com.epam.preproduction.koshevyi.task11;

import com.epam.preproduction.koshevyi.captcha.entity.Captcha;
import com.epam.preproduction.koshevyi.dto.UserDto;
import com.epam.preproduction.koshevyi.entity.User;
import com.epam.preproduction.koshevyi.service.UserService;
import com.epam.preproduction.koshevyi.validator.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class UserValidatorTest {

    @Mock
    UserService userServiceMock;

    private Captcha captcha;
    private String userInputCaptchaValue;
    private UserValidator userValidator;

    private String email;
    private String name;
    private String login;
    private String password;
    private String confirmPassword;
    private String salesSubscription;
    private String goodsSubscription;
    private User userChecking;

    private static User user1 = new User("ivan@mail.ru", "ivanlog", "Ivan Ivanov", "vbnJl_9z", null, " ", 2);
    private static User user2 = new User("alex@gmail.", "alexlog", "Alex Pushkin", "vbnJl_fdg2", " ", null, 2);

    public UserValidatorTest(String email, String login, String name, String password, String confirmPassword,
                             String salesSubscription, String goodsSubscription, User userChecking) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.salesSubscription = salesSubscription;
        this.goodsSubscription = goodsSubscription;
        this.userChecking = userChecking;
    }

    @Before
    public void init() {
        initMocks(this);

        when(userServiceMock.findUserByLogin(login)).thenReturn(userChecking);

        userInputCaptchaValue = "8765";

        captcha = new Captcha(3871, "8765", System.currentTimeMillis());
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> provideUsersDataForRegistration() {
        return Arrays.asList(new Object[][]{
                {"ivan@mail.ru", "ivanlog", "Ivan Ivanov", "vbnJl_9z", "vbnJl_9z", null, " ", null},
                {"ivan@gmail.com", "ivanlog", "Ivan Sidorov", "vbnJl_9z", "vbnJl_9z", null, null, user1},
                {"alex@gmail.com", "alexlog", "Alex Pushkin", "vbnJl_9z", "vbnJl_fdg2", " ", null, null},
                {"alex@gmail.", "alexlog", "Alex Pushkin", "vbnJl_fdg2", "vbnJl_fdg2", " ", null, user2},
                {"alex@gmail.com", "al", "Alex Pushkin", "vbnJl_fdg2", "vbnJl_fdg2", " ", null, null},
                {"alex@gmail.com", "alexlogfdg", "Alex", "vbnJl_fdg2", "vbnJl_fdg2", " ", null, null},
                {"alex@gmail.com", "alexlogf31", "Alex", "vbnJl_fdg", "vbnJl_fdg", " ", null, null}
        });
    }

    @Test
    public void shouldReturnMessagesBasedOnCurrentUserValidationState() {
        userValidator = new UserValidator(userServiceMock);

        userValidator.validateRegForm(new UserDto(email, login, name, password, confirmPassword, salesSubscription, goodsSubscription), captcha.getValue(), userInputCaptchaValue);
    }
}