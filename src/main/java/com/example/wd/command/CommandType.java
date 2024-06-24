package com.example.wd.command;

import com.example.wd.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    CONFIRM(new ConfirmCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    VIEWUSERS(new ViewUsersCommand()),
    MAIN(new MainCommand()),
    DELETEUSER(new DeleteUserCommand()),
    DELETEUSERPAGE(new DeleteUserPageCommand()),
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