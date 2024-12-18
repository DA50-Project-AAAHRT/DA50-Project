package com.projet.da50.projet_da50.view;

import com.projet.da50.projet_da50.controller.LoginErrorHandler;
import com.projet.da50.projet_da50.controller.UserController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AuthenticationFormView extends UI{

    private UserController userController;
    private Stage primaryStage;
    private LoginErrorHandler loginErrorHandler;

    public AuthenticationFormView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.userController = new UserController();
        this.loginErrorHandler = new LoginErrorHandler();
    }

    public void show() {

        primaryStage.setTitle("Authentication");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add the label and username field
        Label userLabel = new Label("Username:");
        grid.add(userLabel, 0, 0);

        TextField userField = new TextField();
        grid.add(userField, 1, 0);

        // Add the label and password field
        Label pwLabel = new Label("Password");
        grid.add(pwLabel, 0, 1);

        PasswordField pwField = new PasswordField();
        grid.add(pwField, 1, 1);

        // Connection button
        Button btnLogin = new Button("Login");
        HBox hbBtnLogin = new HBox(10);
        hbBtnLogin.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnLogin.getChildren().add(btnLogin);
        grid.add(hbBtnLogin, 1, 2);
        btnLogin.setOnAction(e -> {
            String username = userField.getText();
            String password = pwField.getText();

            String validationMessage = loginErrorHandler.validateAuthenticationFields(username, password);
            if ("Valid credentials.".equals(validationMessage)) {
                new MainMenuView(primaryStage).show();
            } else {
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                userField.clear();
                pwField.clear();
            }
        });

        // Button "Forgotten password?"
        Button btnForgotPassword = new Button("Forgotten password?");
        btnForgotPassword.setOnAction(e -> {
            ForgotPassWordFormView forgotPasswordPage = new ForgotPassWordFormView(primaryStage);
            forgotPasswordPage.show();
        });

        // Button "Create Account"
        Button btnCreateAccount = new Button("Create Account");
        btnCreateAccount.setOnAction(e -> {
            CreateAccountFormView createAccountPage = new CreateAccountFormView(primaryStage);
            createAccountPage.show();
        });
        // Add the buttons to the bottom of the grid
        HBox hbBottomButtons = new HBox(10);
        hbBottomButtons.setAlignment(Pos.BOTTOM_CENTER);
        hbBottomButtons.getChildren().addAll(btnForgotPassword, btnCreateAccount);
        grid.add(hbBottomButtons, 1, 3);

        // Create the scene and add the grid pane
        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}