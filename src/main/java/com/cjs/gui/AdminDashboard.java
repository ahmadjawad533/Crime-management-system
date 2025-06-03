package com.cjs.gui;

import com.cjs.util.Logger;
import com.cjs.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard {
    private final User loggedInUser;

    public AdminDashboard(User user) {
        this.loggedInUser = user;
    }

    public void show(Stage stage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3a8a, #3b82f6);");

        Label title = new Label("Admin Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label userDetails = new Label(
                "Welcome, " + loggedInUser.getName() + "\n" +
                        "Username: " + loggedInUser.getUsername() + "\n" +
                        "Role: " + loggedInUser.getRole()
        );
        userDetails.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        logoutButton.setOnAction(e -> {
            new LoginScreen().show(new Stage());
            stage.close();
        });

        root.getChildren().addAll(title, userDetails, logoutButton);

        Scene scene = new Scene(root, 450, 500);

        // Load stylesheet safely
        try {
            String stylesheet = getClass().getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);
        } catch (NullPointerException ex) {
            Logger.info("Warning: styles.css not found in AdminDashboard, proceeding without stylesheet");
        }

        stage.setScene(scene);
        stage.setTitle("Criminal Justice System - Admin Dashboard");
        stage.show();
    }
}