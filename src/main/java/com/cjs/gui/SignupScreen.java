package com.cjs.gui;

import com.cjs.service.AuthService;
import com.cjs.util.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SignupScreen {
    private final AuthService authService = new AuthService();
    private final Map<String, String[]> designations = new HashMap<>();

    public SignupScreen() {
        designations.put("Police", new String[]{"SHO", "ASP", "DSP", "SP", "SSP"});
        designations.put("Judge", new String[]{"Civil Judge", "Session Judge", "District Judge", "High Court Judge"});
        designations.put("Lawyer", new String[]{"Advocate", "Senior Advocate", "Public Prosecutor"});
        designations.put("Admin", new String[]{"System Admin"});
        designations.put("Criminal", new String[]{"N/A"});
        designations.put("Victim", new String[]{"N/A"});
        designations.put("Witness", new String[]{"N/A"});
    }

    public void show(Stage stage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3a8a, #3b82f6);");

        Label title = new Label("Criminal Justice System - Signup");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        ComboBox<String> roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll("Admin", "Police", "Judge", "Lawyer", "Criminal", "Victim", "Witness");
        roleCombo.setPromptText("Select Role");
        roleCombo.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px;");

        ComboBox<String> designationCombo = new ComboBox<>();
        designationCombo.setPromptText("Select Designation");
        designationCombo.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px;");

        // Update designations based on role selection
        roleCombo.setOnAction(e -> {
            designationCombo.getItems().clear();
            String selectedRole = roleCombo.getValue();
            if (selectedRole != null) {
                designationCombo.getItems().addAll(designations.get(selectedRole));
            }
        });

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #dc2626; -fx-font-weight: bold;");

        Button signupButton = new Button("Signup");
        signupButton.setStyle("-fx-background-color: #22c55e; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        signupButton.setOnAction(e -> {
            String name = nameField.getText();
            String username = usernameField.getText();
            String role = roleCombo.getValue();
            String designation = designationCombo.getValue();
            String password = passwordField.getText();

            try {
                authService.register(name, username, role, designation, password);
                new LoginScreen().show(stage);
            } catch (Exception ex) {
                errorLabel.setText("Error: " + ex.getMessage());
            }
        });

        Button loginButton = new Button("Back to Login");
        loginButton.setStyle("-fx-background-color: #6b7280; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        loginButton.setOnAction(e -> new LoginScreen().show(stage));

        root.getChildren().addAll(title, nameField, usernameField, roleCombo, designationCombo, passwordField, errorLabel, signupButton, loginButton);
        Scene scene = new Scene(root, 450, 650);
        try {
            String stylesheet = getClass().getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);
        } catch (NullPointerException ex) {
            Logger.info("Warning: styles.css not found in SignupScreen, proceeding without stylesheet");
        }
        stage.setScene(scene);
        stage.setTitle("Criminal Justice System - Signup");
        stage.show();
    }
}