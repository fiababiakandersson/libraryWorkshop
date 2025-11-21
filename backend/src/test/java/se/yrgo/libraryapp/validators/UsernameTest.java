package se.yrgo.libraryapp.validators;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class UsernameTest {
    @ParameterizedTest
    @ValueSource(strings = { "Ladyfunyon", "FizzyFizzen", "Fizzen129" })
    void checkCorrectUsername(String string) {
        assertThat(Username.validate(string)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "Ladyfu nyon", "FizzyF-2 izzen", " Fi  -zzen129 ", "F" })
    void checkIncorrectUsername(String string) {
        assertThat(Username.validate(string)).isFalse();
    }

    @ParameterizedTest
    @EmptySource
    void checkEmpty(String string) {
        assertThat(Username.validate(string)).isFalse();
    }

    @Test 
    void correctUsername() {
        assertThat(Username.validate("bosse")).isTrue();
    }

    @Test
    void incorrectUsername() {
        assertThat(Username.validate("name with space")).isFalse();
    }
}
