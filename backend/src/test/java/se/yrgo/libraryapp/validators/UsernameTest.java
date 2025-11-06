package se.yrgo.libraryapp.validators;

// import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;

public class UsernameTest {
    // @ParameterizedTest
    // @ValueSource(strings = { "0-20330-0-02531", "0-20000-0-02598",
    // "0-27100-0-02531" })
    // void testCanParseToString(String idString) {
    // WigosStationIdentifier identifier = WigosStationIdentifier.parse(idString);
    // assertThat(identifier).hasToString(idString);
    // }

    @Test
    void correctUsername() {
        assertThat(Username.validate("bosse")).isTrue();
    }

    @Test
    void incorrectUsername() {
        assertThat(Username.validate("name with space")).isFalse();
    }
}
