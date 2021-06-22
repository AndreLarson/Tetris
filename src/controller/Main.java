package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/view/base.fxml"));
        Parent baseRoot = baseLoader.load();
        BoardController boardController = baseLoader.getController();
        boardController.setStage(primaryStage);
        Scene scene = new Scene(baseRoot, 600, 900);
        primaryStage.setTitle("Tetris Ultimate 3000 Version 1.0 Deluxe Edition");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
