package com.example.wd.command.impl;

import com.example.wd.command.Command;
import com.example.wd.service.UserService;
import com.example.wd.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");

        if (login == null || login.trim().isEmpty()) {
            request.setAttribute("errorMsg", "Login cannot be empty");
            return "/pages/deleteUser.jsp";
        }

        boolean isDeleted = userService.deleteUserByLogin(login);

        if (isDeleted) {
            request.setAttribute("successMsg", "User deleted successfully");
        } else {
            request.setAttribute("errorMsg", "User not found or could not be deleted");
        }

        return "/pages/deleteUser.jsp";
    }
}