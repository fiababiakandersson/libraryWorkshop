package se.yrgo.libraryapp.services;

import java.util.*;

import javax.inject.*;

import org.springframework.security.crypto.password.*;

import se.yrgo.libraryapp.dao.*;
import se.yrgo.libraryapp.entities.*;

public class UserService {
    private UserDao userDao;
    private PasswordEncoder encoder;

    @Inject
    public UserService(UserDao userDao, PasswordEncoder encoder2) {
        this.userDao = userDao;
        this.encoder = encoder2;
    }

    public Optional<UserId> validate(String username, String password) {
        Optional<LoginInfo> maybeLoginInfo = userDao.getLoginInfo(username);
        if (maybeLoginInfo.isEmpty()) {
            return Optional.empty();
        }

        LoginInfo loginInfo = maybeLoginInfo.get();

        if (!this.encoder.matches(password, loginInfo.getPasswordHash())) {
            return Optional.empty();
        }

        return Optional.of(loginInfo.getUserId());
    }

    public boolean handleNameAndPassword(String name, String realname, String password) {
        String passwordHash = encoder.encode(password);

        return userDao.register(name, realname, passwordHash);
    }

    // ex 1 isNameAvailable
    public boolean checkIsNameAvailable(String name) {
        if (name == null || name.trim().length() < 3)
            return false;

        if (userDao.isNameAvailable(name) == false) {
            return false;
        } else
            return true;
    }
}
