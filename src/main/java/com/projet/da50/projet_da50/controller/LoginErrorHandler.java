package com.projet.da50.projet_da50.controller;

import com.projet.da50.projet_da50.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class LoginErrorHandler {

    private UserController userController;

    public LoginErrorHandler(){
        this.userController = new UserController();
    }

    public String validateAuthenticationFields(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return "Please fill in all fields.";
        }

        User user = userController.findUserByUsername(username);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return "Valid credentials.";
        } else {
            return "Invalid username or password.";
        }
    }

    public String validateCreateAccountFields(String username, String password, String mail) {
        if (username.isEmpty() || password.isEmpty() || mail.isEmpty()) {
            return "Please fill in all fields.";
        }
        if (password.length() < 6) {
            return "Password should be at least 6 characters long.";
        }
        if (!mail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return "Mail format is invalid.";
        }
        if (userController.findUserByUsername(username)!=null) {
            return "This username is already taken.";
        }
        if (userController.findUserByMail(mail)!=null) {
            return "This mail is already used.";
        }
        return "Valid credentials.";
    }

    public String validateForgotPasswordFields(String mail) {
        if (mail.isEmpty()) {
            return "Please fill in all fields.";
        }
        if (!mail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            return "Mail format is invalid.";
        }
        if (userController.findUserByMail(mail) == null) {
            return "This mail does not exist";
        }
        //If everything is ok:
        return "Valid credentials.";
    }
}