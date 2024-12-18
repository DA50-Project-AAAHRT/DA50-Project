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
        primaryStage.setTitle("Reset Password");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label emailLabel = new Label("Mail:");
        grid.add(emailLabel, 0, 0);
        TextField mailField = new TextField();
        grid.add(mailField, 1, 0);

        Button btnResetPassword = new Button("Reset Password");
        btnResetPassword.setOnAction(e -> {
            String mail = mailField.getText();
            String validationMessage = loginErrorHandler.validateForgotPasswordFields(mail);

            if ("Valid credentials.".equals(validationMessage)) {
                Label successLabel = new Label("A reset link has been sent to " + mail);
                Stage successStage = new Stage();
                GridPane successGrid = new GridPane();
                successGrid.setAlignment(Pos.CENTER);
                successGrid.setPadding(new Insets(10));
                successGrid.add(successLabel, 0, 0);

                Button closeButton = new Button("Close");
                closeButton.setOnAction(event -> successStage.close());
                successGrid.add(closeButton, 0, 1);

                Scene successScene = new Scene(successGrid, 300, 100);
                successStage.setScene(successScene);
                successStage.showAndWait();
                new AuthenticationFormView(primaryStage).show();
            } else {
                Label errorLabel = new Label(validationMessage);
                grid.add(errorLabel, 1, 3);
                mailField.clear();
            }
        });

        Button btnBack = new Button("Go back");
        btnBack.setOnAction(e -> new AuthenticationFormView(primaryStage).show());

        HBox hbButtons = new HBox(10);
        hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
        hbButtons.getChildren().addAll(btnBack, btnResetPassword);
        grid.add(hbButtons, 1, 1);

        Scene scene = new Scene(grid, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}