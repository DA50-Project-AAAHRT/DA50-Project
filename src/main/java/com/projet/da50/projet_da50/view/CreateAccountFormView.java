package com.projet.da50.projet_da50.view;

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

import java.util.concurrent.atomic.AtomicBoolean;

public class CreateAccountFormView extends UI {

    private UserController userController;
    private Stage primaryStage;

    public CreateAccountFormView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.userController = new UserController();
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
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Label emailLabel = new Label("Email:");
        grid.add(emailLabel, 0, 1);
        TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 1);

        Label pwLabel = new Label("Mot de passe:");
        grid.add(pwLabel, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btnCreateAccount = new Button("Créer un compte");

        AtomicBoolean userexist = new AtomicBoolean(false);
        btnCreateAccount.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwBox.getText();
            String email = emailTextField.getText();

            userexist.set(userController.checkUserExists(username, password, email));
            if(userexist.get()) {
                System.out.println("User created");
                userController.createUser(username, password, email);
                new AuthentificationFormView(primaryStage).show();
            }
            else {
                // TODO : call the error explanation function to display a message in the view
                Label userexistLabel = new Label("User already exists");
                grid.add(userexistLabel, 3, 3);

                System.out.println("User already exists");

                userTextField.clear();
                emailTextField.clear();
                pwBox.clear();
            }
        });

        Button btnBack = new Button("Login");
        btnBack.setOnAction(e -> new AuthentificationFormView(primaryStage).show());

        HBox hbButtons = new HBox(10);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtons.getChildren().addAll(btnBack, btnCreateAccount);
        grid.add(hbButtons, 1, 3);

        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Créer un compte");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
