package com.example.wd.command.impl;

import com.example.wd.command.Command;
import com.example.wd.service.UserService;
import com.example.wd.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        logger.debug("Login attempt with login: " + login);

        UserService userService = UserServiceImpl.getInstance();
        String page;
        if (userService.authentificate(login, password)) {
            logger.debug("Authentification successful for user: " + login);
            request.setAttribute("user", login);
            page = "pages/main.jsp";
        } else {
            logger.debug("Authentification failed for user: " + login);
            request.setAttribute("login_msg", "Incorrect login or password");
            page = "index.jsp";
        }
        return page;
    }
}