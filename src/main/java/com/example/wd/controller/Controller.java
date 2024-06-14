package com.example.wd.controller;

import com.example.wd.command.Command;
import com.example.wd.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    public void init() {
        logger.info("Controller initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        logger.debug("Received command: {}", commandStr);
        Command command = CommandType.define(commandStr);
        String page = command.execute(request);

        logger.debug("Forwarding to page: {}", page);
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {
        logger.info("Controller destroyed");
    }
}