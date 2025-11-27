package se.yrgo.libraryapp;

import org.springframework.security.crypto.argon2.*;
import org.springframework.security.crypto.password.*;

import com.google.inject.*;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PasswordEncoder.class).to(Argon2PasswordEncoder.class);
    }
}
