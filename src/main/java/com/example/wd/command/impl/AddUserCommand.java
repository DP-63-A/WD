package com.example.wd.command.impl;

import com.example.wd.command.Command;
import com.example.wd.entity.User;
import com.example.wd.service.UserService;
import com.example.wd.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        logger.debug("Add user attempt with login: " + login);

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);

        UserService userService = UserServiceImpl.getInstance();
        boolean success = userService.addUser(user);

        if (success) {
            logger.debug("User added successfully: " + login);
            request.setAttribute("user", login);
            return "index.jsp";
        } else {
            logger.error("Failed to add user: " + login);
            request.setAttribute("error_msg", "User registration failed");
            return "register.jsp";
        }
    }
}