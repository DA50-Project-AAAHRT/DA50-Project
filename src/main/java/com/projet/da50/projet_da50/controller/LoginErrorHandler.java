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

    public String validateCreateAccountFields(String username, String password, String email) {
        if (!isValidEmail(email)) {
            return "Invalid email format.";
        }
        if (!isValidPassword(password)) {
            return "Password must be at least 6 characters long.";
        }
        if (userController.checkUserExists(username, email)) {
            return "User already exists.";
        }
        return "Valid credentials.";
    }

    public String validateForgotPasswordFields(String email) {
        if (!isValidEmail(email)) {
            return "Invalid email format.";
        }
        return "Valid credentials.";
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}