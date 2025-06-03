package com.cjs;

import com.cjs.gui.LoginScreen;
import com.cjs.util.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Logger.info("Application started");
        new LoginScreen().show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}