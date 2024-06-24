package com.example.wd.command.impl;

import com.example.wd.command.Command;
import com.example.wd.entity.User;
import com.example.wd.service.UserService;
import com.example.wd.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class AddUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        logger.debug("Add user attempt with login: " + login);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);

        UserService userService = UserServiceImpl.getInstance();
        boolean success;

        try {
            success = userService.addUser(user);
        } catch (Exception e) {
            logger.error("Error adding user: " + login, e);
            request.setAttribute("error_msg", "User registration failed due to internal error");
            return "/pages/register.jsp";
        }

        if (success) {
            logger.debug("User added successfully: " + login);
            request.setAttribute("user", login);

            int confirmationCode = new Random().nextInt(9000) + 1000;
            request.getSession().setAttribute("confirmationCode", confirmationCode);
            request.setAttribute("generatedCode", confirmationCode);

            return "/pages/confirm.jsp";
        } else {
            logger.error("Failed to add user: " + login);
            request.setAttribute("error_msg", "User registration failed");
            return "/pages/register.jsp";
        }
    }
}