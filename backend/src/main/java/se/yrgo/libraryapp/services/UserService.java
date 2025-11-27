package se.yrgo.libraryapp.services;

import java.util.*;

import javax.inject.*;

import org.springframework.security.crypto.argon2.*;
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
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        String passwordHash = encoder.encode(password);

        String fixedRealName = realname.replace("'", "\\'");

        if (userDao.register(name, fixedRealName, passwordHash) == true) {
            return false;
        } else {
            return true;
        }
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
