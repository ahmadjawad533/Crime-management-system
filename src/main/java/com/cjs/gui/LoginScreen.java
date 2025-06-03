package com.cjs.gui;

import com.cjs.service.AuthService;
import com.cjs.util.Logger;
import com.cjs.model.User; // Add this import
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {
    private final AuthService authService = new AuthService();

    public void show(Stage stage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3a8a, #3b82f6);");

        Label title = new Label("Criminal Justice System - Login");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #dc2626; -fx-font-weight: bold;");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #22c55e; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText(); // Changed from password_hash to password for clarity

            // Validate input
            if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                errorLabel.setText("Error: Username and password cannot be empty");
                Logger.info("Login failed: Empty username or password");
                return;
            }

            try {
                // First, authenticate the user and get their role
                String role = authService.login(username, password);
                if (role == null) {
                    errorLabel.setText("Error: Invalid username or password");
                    Logger.info("Login failed for username: " + username + " - Invalid credentials");
                    return;
                }

                // Fetch the full User object after successful authentication
                User loggedInUser = authService.getUserByUsername(username); // Add this method to AuthService
                if (loggedInUser == null) {
                    errorLabel.setText("Error: User not found after login");
                    Logger.info("Login failed for username: " + username + " - User not found after login");
                    return;
                }

                Logger.info("Login successful for username: " + username + ", role: " + role);
                switch (role) {
                    case "Admin":
                        new AdminDashboard(loggedInUser).show(stage);
                        break;
                    case "Police":
                        new PoliceDashboard(loggedInUser).show(stage);
                        break;
                    case "Judge":
                        new JudgeDashboard(loggedInUser).show(stage);
                        break;
                    case "Lawyer":
                        new LawyerDashboard(loggedInUser).show(stage);
                        break;
                    case "Criminal":
                        new CriminalDashboard(loggedInUser).show(stage);
                        break;
                    case "Victim":
                        new VictimDashboard(loggedInUser).show(stage);
                        break;
                    case "Witness":
                        new WitnessDashboard(loggedInUser).show(stage);
                        break;
                    default:
                        errorLabel.setText("Error: Unsupported role: " + role);
                        Logger.info("Login failed for username: " + username + " - Unsupported role: " + role);
                }
            } catch (Exception ex) {
                errorLabel.setText("Error: " + ex.getMessage());
                Logger.info("Login failed for username: " + username + " - Exception: " + ex.getMessage());
            }
        });

        Button signupButton = new Button("Go to Signup");
        signupButton.setStyle("-fx-background-color: #6b7280; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        signupButton.setOnAction(e -> {
            System.out.println("Go to Signup button clicked at " + java.time.LocalDateTime.now());
            try {
                new SignupScreen().show(stage);
            } catch (Exception ex) {
                System.err.println("Error navigating to SignupScreen: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(title, usernameField, passwordField, errorLabel, loginButton, signupButton);
        Scene scene = new Scene(root, 450, 500);

        // Load stylesheet safely
        try {
            String stylesheet = getClass().getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);
        } catch (NullPointerException ex) {
            Logger.info("Warning: styles.css not found, proceeding without stylesheet");
        }

        stage.setScene(scene);
        stage.setTitle("Criminal Justice System - Login");
        stage.show();
    }
}