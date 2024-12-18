package com.projet.da50.projet_da50.controller;

import java.util.regex.Pattern;

public class LoginErrorHandler {

    private UserController userController;

    public LoginErrorHandler() {
        this.userController = new UserController();
    }

    public String validateAuthenticationFields(String username, String password) {
        if(!userController.verifyUserCredentials(username, password)){
            return "Wrong credentials.";
        }
        return "Valid credentials.";
    }

    public String validateCreateAccountFields(String username, String password, String mail) {
        if (!isValidMail(mail)) {
            return "Mail format is invalid.";
        }
        if (!isValidPassword(password)) {
            return "Password must be at least 6 characters long.";
        }
        if (userController.checkUserExists(username, mail).equals("This username is already taken.")) {
            return "This username is already taken.";
        }
        if (userController.checkUserExists(username, mail).equals("This mail is already used.")) {
            return "This mail is already used.";
        }
        return "Valid credentials.";
    }

    public String validateForgotPasswordFields(String mail) {
        if (!isValidMail(mail)) {
            return "Invalid mail format.";
        }
        if (userController.checkUserExists("", mail) != "This mail is already used.") {
            return "No account is associated with this mail.";
        }
        return "Valid credentials.";
    }

    public static boolean isValidMail(String mail) {
        String mailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(mailRegex);
        return pattern.matcher(mail).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}