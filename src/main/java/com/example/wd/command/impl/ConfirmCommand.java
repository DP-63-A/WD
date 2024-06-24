package com.example.wd.command.impl;

import com.example.wd.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfirmCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ConfirmCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String confirmationCode = request.getParameter("confirmationCode");
        Integer expectedCode = (Integer) request.getSession().getAttribute("confirmationCode");

        logger.debug("Received confirmation code: " + confirmationCode);
        logger.debug("Expected confirmation code: " + expectedCode);

        if (expectedCode != null && expectedCode.toString().equals(confirmationCode)) {
            logger.debug("Confirmation code matched. Registration successful.");
            request.getSession().removeAttribute("confirmationCode");
            return "/pages/success.jsp";
        } else {
            logger.debug("Confirmation code didn't match. Returning to confirmation page.");
            request.setAttribute("error_msg", "Incorrect confirmation code. Please try again.");
            return "/pages/confirm.jsp";
        }
    }
}