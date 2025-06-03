package com.cjs.gui;

import com.cjs.service.CaseService;
import com.cjs.util.Logger;
import com.cjs.model.User; // Add this import
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WitnessDashboard {
    private final CaseService caseService = new CaseService();
    private final User loggedInUser; // Add this field

    public WitnessDashboard(User user) {
        this.loggedInUser = user; // Store the user
    }

    public void show(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f3f4f6;");

        // Top: Header
        Label header = new Label("Witness Dashboard - Welcome, " + loggedInUser.getName());
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #1e3a8a; -fx-padding: 20px;");
        root.setTop(header);
        BorderPane.setAlignment(header, Pos.CENTER);

        // Center: Case Status
        VBox center = new VBox(10);
        center.setPadding(new Insets(20));
        center.setAlignment(Pos.CENTER);

        Label title = new Label("View Case Status");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button viewCasesButton = new Button("View Related Cases");
        viewCasesButton.setStyle("-fx-background-color: #3b82f6; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        viewCasesButton.setOnAction(e -> {
            try {
                caseService.getAllCases().forEach(caseObj ->
                        showAlert(Alert.AlertType.INFORMATION, "Case", "FIR: " + caseObj.getFirNumber() + ", Status: " + caseObj.getStatus()));
                Logger.info("View Related Cases button clicked, displayed cases");
            } catch (Exception ex) {
                Logger.error("Failed to view cases: " + ex.getMessage());
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to view cases: " + ex.getMessage());
            }
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        logoutButton.setOnAction(e -> {
            try {
                new LoginScreen().show(new Stage());
                stage.close();
                Logger.info("Logout button clicked, returned to LoginScreen");
            } catch (Exception ex) {
                Logger.error("Failed to logout: " + ex.getMessage());
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to logout: " + ex.getMessage());
            }
        });

        center.getChildren().addAll(title, viewCasesButton, logoutButton);
        root.setCenter(center);

        Scene scene = new Scene(root, 800, 600);

        // Load stylesheet safely
        try {
            String stylesheet = getClass().getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);
        } catch (NullPointerException ex) {
            Logger.info("Warning: styles.css not found in WitnessDashboard, proceeding without stylesheet");
        }

        stage.setScene(scene);
        stage.setTitle("Criminal Justice System - Witness Dashboard");
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}