package com.projet.da50.projet_da50.view;

import com.projet.da50.projet_da50.controller.LoginErrorHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ForgotPassWordFormView extends UI {

    private Stage primaryStage;
    private LoginErrorHandler loginErrorHandler;

    public ForgotPassWordFormView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.loginErrorHandler = new LoginErrorHandler();
    }

    public void show() {
        primaryStage.setTitle("Forgot Password");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 0, 0);
        TextField emailField = new TextField();
        grid.add(emailField, 1, 0);

        Button btnResetPassword = new Button("Réinitialiser le mot de passe");
        btnResetPassword.setOnAction(e -> {
            String email = emailField.getText();
            String validationMessage = loginErrorHandler.validateForgotPasswordFields(email);
            if ("Valid credentials.".equals(validationMessage)) {
                System.out.println("Lien de réinitialisation envoyé à " + email);
                new AuthenticationFormView(primaryStage).show();
            } else {
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                emailField.clear();
            }
        });

        Button btnBack = new Button("Retour");
        btnBack.setOnAction(e -> new AuthenticationFormView(primaryStage).show());

        HBox hbButtons = new HBox(10);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtons.getChildren().addAll(btnBack, btnResetPassword);
        grid.add(hbButtons, 1, 1);

        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Mot de passe oublié");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}