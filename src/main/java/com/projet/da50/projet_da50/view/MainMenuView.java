package com.projet.da50.projet_da50.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenuView extends UI {
    private Stage primaryStage;

    public MainMenuView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        // Add Courses button
        Button btnCourses = new Button("Courses");
        btnCourses.setOnAction(e -> {
            // Handle Courses button action
        });
        grid.add(btnCourses, 0, 0);

        // Add Wallet button
        Button btnWallet = new Button("Wallet");
        btnWallet.setOnAction(e -> {
            // Handle Wallet button action
        });
        grid.add(btnWallet, 0, 1);

        // Add Disconnect button
        Button btnDisconnect = new Button("Disconnect");
        btnDisconnect.setOnAction(e -> {
            // Redirect to AuthentificationFormView
            new AuthenticationFormView(primaryStage).show();
        });
        grid.add(btnDisconnect, 0, 2);

        // Add a My profil button
        Button btnProfil = new Button("My profil");
        btnProfil.setOnAction(e -> {
            // Handle My profil button action
        });
        grid.add(btnProfil, 1, 2);

        // Request focus on another element to prevent the Disconnect button from being selected
        btnCourses.requestFocus();

        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}