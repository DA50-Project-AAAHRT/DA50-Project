package com.projet.da50.projet_da50.view;

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

public class AuthentificationFormView extends UI{

    private UserController userController;
    private Stage primaryStage;

    public AuthentificationFormView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.userController = new UserController();
    }

    public void show() {
        primaryStage.setTitle("Authentification");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add the label and username field
        Label userLabel = new Label("Nom d'utilisateur:");
        grid.add(userLabel, 0, 0);

        TextField userField = new TextField();
        grid.add(userField, 1, 0);

        // Add the label and password field
        Label pwLabel = new Label("Mot de passe:");
        grid.add(pwLabel, 0, 1);

        PasswordField pwField = new PasswordField();
        grid.add(pwField, 1, 1);

        // Connexion button
        Button btnLogin = new Button("Connexion");
        HBox hbBtnLogin = new HBox(10);
        hbBtnLogin.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnLogin.getChildren().add(btnLogin);
        grid.add(hbBtnLogin, 1, 2);
        btnLogin.setOnAction(e -> {
            String username = userField.getText();
            String password = pwField.getText();

            if (userController.verifyUser(username, password)) {
                System.out.println("Connexion réussie !");
                // Redirect user to the main menu
                new MainMenuView(primaryStage).show();
            } else {
                // TODO : call the error explanation function with "bad credentials error"
                userField.clear();
                pwField.clear();
                System.out.println("Identifiants incorrects.");
            }
        });

        // Button "Mot de passe oublié"
        Button btnForgotPassword = new Button("Mot de passe oublié");
        btnForgotPassword.setOnAction(e -> {
            ForgotPassWordFormView forgotPasswordPage = new ForgotPassWordFormView(primaryStage);
            forgotPasswordPage.show();
        });

        // Button "Créer un compte"
        Button btnCreateAccount = new Button("Créer un compte");
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
        primaryStage.setTitle("Page d'authentification");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}