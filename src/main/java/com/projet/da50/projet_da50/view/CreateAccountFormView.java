package com.projet.da50.projet_da50.view;

import com.projet.da50.projet_da50.controller.LoginErrorHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import com.projet.da50.projet_da50.controller.UserController;

public class CreateAccountFormView extends UI {

    private UserController userController;
    private Stage primaryStage;
    private LoginErrorHandler loginErrorHandler;

    public CreateAccountFormView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.userController = new UserController();
        this.loginErrorHandler = new LoginErrorHandler();
    }

    @Override
    public void show() {
        primaryStage.setTitle("Create Account");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userLabel = new Label("Username:");
        grid.add(userLabel, 0, 0);
        TextField userField = new TextField();
        grid.add(userField, 1, 0);

        Label emailLabel = new Label("Mail:");
        grid.add(emailLabel, 0, 1);
        TextField mailField = new TextField();
        grid.add(mailField, 1, 1);

        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);
        PasswordField pwField = new PasswordField();
        grid.add(pwField, 1, 2);

        Button btnCreateAccount = new Button("Create Account");

        btnCreateAccount.setOnAction(e -> {
            String username = userField.getText();
            String password = pwField.getText();
            String mail = mailField.getText();

            String validationMessage = loginErrorHandler.validateCreateAccountFields(username, password, mail);
            if ("Valid credentials.".equals(validationMessage)) {
                userController.createUser(username, password, mail);
                new AuthenticationFormView(primaryStage).show();
            } else if ("Password should be at least 6 characters long.".equals(validationMessage)) {
                // Handle specific case for password length
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                pwField.clear();
                pwField.requestFocus();
            } else if ("Mail format is invalid.".equals(validationMessage)) {
                // Handle specific case for mail format
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                mailField.clear();
                mailField.requestFocus();
            } else if ("This username is already taken.".equals(validationMessage)) {
                // Handle specific case for username taken
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                userField.requestFocus();
            } else if ("This mail is already used.".equals(validationMessage)) {
                // Handle specific case for mail taken
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                mailField.requestFocus();
            }
        });

        Button btnBack = new Button("Go back");
        btnBack.setOnAction(e -> new AuthenticationFormView(primaryStage).show());

        HBox hbButtons = new HBox(10);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtons.getChildren().addAll(btnBack, btnCreateAccount);
        grid.add(hbButtons, 1, 4);

        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Create Account");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}