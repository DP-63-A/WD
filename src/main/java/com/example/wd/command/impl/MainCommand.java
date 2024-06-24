package com.example.wd.command.impl;

import com.example.wd.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class MainCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/pages/main.jsp";
    }
}