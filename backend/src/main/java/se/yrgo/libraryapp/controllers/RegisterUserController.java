package se.yrgo.libraryapp.controllers;

import javax.inject.*;

import io.jooby.annotations.*;
import se.yrgo.libraryapp.dao.*;
import se.yrgo.libraryapp.entities.forms.*;
import se.yrgo.libraryapp.services.*;
import se.yrgo.libraryapp.validators.*;

@Path("/register")
public class RegisterUserController {
    private UserDao userDao;
    private UserService userService;

    @Inject
    RegisterUserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @POST
    public boolean register(RegisterUserData userData) {
        if (Username.validate(userData.getName()) && RealName.validate(userData.getRealName())) {
            return userDao.register(userData.getName(), userData.getRealName(), userData.getPassword());
        }

        return false;
    }

    @GET
    @Path("/available")
    public boolean isNameAvailable(@QueryParam String name) {
        return userService.checkIsNameAvailable(name);
    }
}
