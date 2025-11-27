package se.yrgo.libraryapp.services;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.mockito.quality.*;
import org.springframework.security.crypto.password.*;

import se.yrgo.libraryapp.dao.*;
import se.yrgo.libraryapp.entities.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    // @Mock
    // private UserService userService;

    @InjectMocks
    private UserService userService;

    @Test
    @SuppressWarnings("deprecation")
    void correctLogin() {
        final String userId = "1";
        final UserId id = UserId.of(userId);
        final String username = "testuser";
        final String password = "password";
        final String passwordHash = "password";
        final LoginInfo info = new LoginInfo(id, passwordHash);
        final PasswordEncoder encoder = org.springframework.security.crypto.password.NoOpPasswordEncoder
                .getInstance();

        when(userDao.getLoginInfo(username)).thenReturn(Optional.of(info));

        UserService userService = new UserService(userDao, encoder);
        assertThat(userService.validate(username, password)).isEqualTo(Optional.of(id));
    }

    @Test
    void testPasswordHashBeforeRegister() {
        String password = "secret";

        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);

        when(userDao.register(anyString(), anyString(), anyString())).thenReturn(false);

        userService.handleNameAndPassword("name", "realName", password);

        verify(userDao).register(anyString(), anyString(), passwordCaptor.capture());

        String hashedPassword = passwordCaptor.getValue();

        assertThat(hashedPassword).isNotEqualTo(password);

        assertThat(hashedPassword).startsWith("$argon2");
    }

    // ex 1
    @Test
    void checkAvailableNameReturnTrue() {

        when(userDao.isNameAvailable("valid")).thenReturn(true);

        boolean result = userService.checkIsNameAvailable("valid");

        assertThat(result).isTrue();
        verify(userDao).isNameAvailable("valid");
    }
}
