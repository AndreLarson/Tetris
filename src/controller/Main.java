package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/view/base.fxml"));
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/view/board.fxml"));
        Parent baseRoot = (Parent) baseLoader.load();
        boardLoader.load();
        Scene scene = new Scene(baseRoot, 600, 900);
        BoardController boardController = boardLoader.getController();
        boardController.setStage(primaryStage);
        primaryStage.setTitle("Tetris by Andre Larson");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
