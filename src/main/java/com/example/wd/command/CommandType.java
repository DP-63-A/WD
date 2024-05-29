package com.example.wd.command;

import com.example.wd.command.impl.AddUserCommand;
import com.example.wd.command.impl.DefaultCommand;
import com.example.wd.command.impl.LoginCommand;
import com.example.wd.command.impl.LogoutCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
