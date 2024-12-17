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

        Label userLabel = new Label("Nom d'utilisateur:");
        grid.add(userLabel, 0, 0);
        TextField userField = new TextField();
        grid.add(userField, 1, 0);

        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 0, 1);
        TextField emailField = new TextField();
        grid.add(emailField, 1, 1);

        Label pwLabel = new Label("Mot de passe:");
        grid.add(pwLabel, 0, 2);
        PasswordField pwField = new PasswordField();
        grid.add(pwField, 1, 2);

        Button btnCreateAccount = new Button("Créer un compte");

        btnCreateAccount.setOnAction(e -> {
            String username = userField.getText();
            String password = pwField.getText();
            String email = emailField.getText();

            String validationMessage = loginErrorHandler.validateCreateAccountFields(username, password, email);
            if ("Valid credentials.".equals(validationMessage)) {
                userController.createUser(username, password, email);
                new AuthenticationFormView(primaryStage).show();
            } else {
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                userField.clear();
                emailField.clear();
                pwField.clear();
            }
        });

        Button btnBack = new Button("Login");
        btnBack.setOnAction(e -> new AuthenticationFormView(primaryStage).show());

        HBox hbButtons = new HBox(10);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtons.getChildren().addAll(btnBack, btnCreateAccount);
        grid.add(hbButtons, 1, 4);

        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Créer un compte");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}