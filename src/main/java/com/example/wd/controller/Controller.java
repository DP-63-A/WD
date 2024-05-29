package com.example.wd.controller;

import java.io.*;

import com.example.wd.command.Command;
import com.example.wd.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        //String strNum = request.getParameter("num");
        //int resNum = 2 * Integer.parseInt(strNum);
        //request.setAttribute("result", resNum);
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page = command.execute(request);

        request.getRequestDispatcher(page).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
    }
}