package se.yrgo.libraryapp.controllers;

import javax.inject.*;

import io.jooby.annotations.*;
import se.yrgo.libraryapp.entities.forms.*;
import se.yrgo.libraryapp.services.*;
import se.yrgo.libraryapp.validators.*;

@Path("/register")
public class RegisterUserController {
    private UserService userService;

    @Inject
    RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    public boolean register(RegisterUserData userData) {
        if (Username.validate(userData.getName()) && RealName.validate(userData.getRealName())
                && isRealNameSafe(userData.getRealName())) {
            return userService.handleNameAndPassword(
                    userData.getName(),
                    userData.getRealName(),
                    userData.getPassword());
        }
        return false;
    }

    @GET
    @Path("/available")
    public boolean isNameAvailable(@QueryParam String name) {
        return userService.checkIsNameAvailable(name);
    }

    private boolean isRealNameSafe(String realName) {
        if (realName.contains("<") || realName.contains(">")) {
            System.out.println("ERROR! Real name contains suspicious characters!");
            return false;
        } else
            return true;
    }
}
