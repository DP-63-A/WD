package com.example.wd.command.impl;

import com.example.wd.command.Command;
import com.example.wd.entity.User;
import com.example.wd.service.UserService;
import com.example.wd.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ViewUsersCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        List<User> users = userService.findAllUsers();
        request.setAttribute("users", users);
        return "/pages/users.jsp";
    }
}