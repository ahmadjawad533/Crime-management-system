package com.cjs.gui;

import com.cjs.model.Case;
import com.cjs.model.User; // Add this import
import com.cjs.service.CaseService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;

public class CaseForm {
    private final CaseService caseService = new CaseService();
    private final User loggedInUser; // Add this field

    public CaseForm(User user) {
        this.loggedInUser = user; // Store the user
    }

    public void show(Stage stage) {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3a8a, #3b82f6);");

        Label title = new Label("Create New Case");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField firNumberField = new TextField();
        firNumberField.setPromptText("FIR Number");
        firNumberField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        TextField policeOfficerIdField = new TextField();
        policeOfficerIdField.setPromptText("Police Officer ID");
        policeOfficerIdField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        TextField judgeIdField = new TextField();
        judgeIdField.setPromptText("Judge ID (Optional)");
        judgeIdField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        TextField lawyerIdField = new TextField();
        lawyerIdField.setPromptText("Lawyer ID (Optional)");
        lawyerIdField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        ComboBox<String> statusCombo = new ComboBox<>();
        statusCombo.getItems().addAll("Under Investigation", "In Court", "Solved", "Closed");
        statusCombo.setPromptText("Select Status");
        statusCombo.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px;");

        TextField crimeTypeField = new TextField();
        crimeTypeField.setPromptText("Crime Type");
        crimeTypeField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        TextField incidentDateField = new TextField();
        incidentDateField.setPromptText("Incident Date (YYYY-MM-DD)");
        incidentDateField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        TextField locationField = new TextField();
        locationField.setPromptText("Location");
        locationField.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 5px; -fx-padding: 10px;");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #dc2626; -fx-font-weight: bold;");

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #22c55e; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        submitButton.setOnAction(e -> {
            try {
                String firNumber = firNumberField.getText();
                int policeOfficerId = Integer.parseInt(policeOfficerIdField.getText());
                int judgeId = judgeIdField.getText().isEmpty() ? 0 : Integer.parseInt(judgeIdField.getText());
                int lawyerId = lawyerIdField.getText().isEmpty() ? 0 : Integer.parseInt(lawyerIdField.getText());
                String status = statusCombo.getValue();
                String crimeType = crimeTypeField.getText();
                Date incidentDate = Date.valueOf(incidentDateField.getText());
                String location = locationField.getText();

                // Create a Case object and populate it
                Case newCase = new Case();
                newCase.setFirNumber(firNumber);
                newCase.setPoliceOfficerId(policeOfficerId);
                newCase.setJudgeId(judgeId);
                newCase.setLawyerId(lawyerId);
                newCase.setStatus(status);
                newCase.setCrimeType(crimeType);
                newCase.setIncidentDate(incidentDate);
                newCase.setLocation(location);

                // Call createCase with the Case object
                caseService.createCase(newCase);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Case created successfully");
                new PoliceDashboard(loggedInUser).show(stage); // Pass the loggedInUser
            } catch (Exception ex) {
                errorLabel.setText("Error: " + ex.getMessage());
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #6b7280; -fx-text-fill: white; -fx-border-radius: 5px; -fx-padding: 10px 20px;");
        backButton.setOnAction(e -> new PoliceDashboard(loggedInUser).show(stage)); // Pass the loggedInUser

        root.getChildren().addAll(title, firNumberField, policeOfficerIdField, judgeIdField, lawyerIdField,
                statusCombo, crimeTypeField, incidentDateField, locationField, errorLabel, submitButton, backButton);
        Scene scene = new Scene(root, 450, 650);

        // Load stylesheet safely
        try {
            String stylesheet = getClass().getResource("/styles.css").toExternalForm();
            scene.getStylesheets().add(stylesheet);
        } catch (NullPointerException ex) {
            System.out.println("Warning: styles.css not found, proceeding without stylesheet");
        }

        stage.setScene(scene);
        stage.setTitle("Criminal Justice System - Create Case");
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