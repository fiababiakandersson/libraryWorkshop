package se.yrgo.libraryapp.dao;

// import static org.assertj.core.api.Assertions.*;

// import java.util.*;

import javax.sql.DataSource;

import org.h2.jdbcx.*;
import org.junit.jupiter.api.*;

import com.radcortez.flyway.test.annotation.*;

// import se.yrgo.libraryapp.entities.*;

@Tag("integration")
@H2
public class UserDaoIntegrationTest {
    private static DataSource ds;

    @BeforeAll
    static void initDataSource() {
        // this way we do not need to create a new datasource every time
        final JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:test");
        UserDaoIntegrationTest.ds = ds;
    }

    // TODO: OUTCOMMENTED FOR NOW FOR TESTING PURPOSES
    // @Test
    // void getUserById() {
    // // this data comes from the test migration files
    // final String username = "test";
    // final UserId userId = UserId.of(1);

    // UserDao userDao = new UserDao(ds);
    // Optional<User> maybeUser = userDao.get(Integer.toString(userId.getId()));

    // assertThat(maybeUser).isPresent();
    // assertThat(maybeUser.get().getName()).isEqualTo(username);
    // assertThat(maybeUser.get().getId()).isEqualTo(userId);
    // }

    // // ex 2
    // @Test
    // void getLoginInfoCorrectName() {
    // final UserId id = UserId.of(1);
    // final String name = "test";

    // UserDao userDao = new UserDao(ds);
    // Optional<LoginInfo> maybeLoginInfo = userDao.getLoginInfo(name);

    // assertThat(maybeLoginInfo).isPresent();
    // assertThat(maybeLoginInfo.get().getUserId()).isEqualTo(id);
    // }

    // @Test
    // void getLoginInfoIncorrectName() {
    // final String name = "iDontExist";

    // UserDao userDao = new UserDao(ds);
    // Optional<LoginInfo> maybeLoginInfo = userDao.getLoginInfo(name);

    // assertThat(maybeLoginInfo).isEmpty();
    // }

    // @Test
    // void registerCorrectInfo() {
    // final String name = "testName";
    // final String realName = "testRealName";
    // final String passwordHash = "testPasswordHash";

    // UserDao userDao = new UserDao(ds);
    // boolean maybeRegistered = userDao.register(name, realName, passwordHash);

    // assertThat(userDao.get(userDao.getLoginInfo(name).get().getUserId().toString())).isPresent();

    // assertThat(maybeRegistered).isTrue();
    // }

    // @Test
    // void registerIncorrectInfo_integrationTest() {
    // UserDao userDao = new UserDao(ds);

    // userDao.register("duplicate", "test", "pw1");
    // boolean result = userDao.register("duplicate", "test", "pw2");

    // assertThat(result).isFalse();
    // }
}

// assertThatThrownBy(() ->
// userDao.getLoginInfo(name)).isInstanceOf(SQLException.class);

// assertThatExceptionOfType(SQLException.class).isThrownBy(() ->
// userDao.getLoginInfo(name));