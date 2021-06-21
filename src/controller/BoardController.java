package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Board;

import static javafx.scene.layout.Priority.SOMETIMES;

public class BoardController {

    @FXML
    private GridPane myGridPane;

    private Board myBoard;

    private AnimationTimer myTimer;

    private Stage myStage;

    public BoardController() {
        myBoard = new Board();
        myTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // want one sec delay
                myBoard.step();
            }
        };
    }

    public void setStage(Stage theStage) {
        myStage = theStage;
        myStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
                case SPACE:
                    //start
                    //if started stop
                    myBoard.start();
                    updateGUI();
                    System.out.println("works");
                    break;
                case UP:
                    //fast drop
                    break;
                case DOWN:
                    //move down
                    myBoard.down();
                    updateGUI();
                    System.out.println("works");
                    break;
                case LEFT:
                    //move left
                    break;
                case RIGHT:
                    //move right
                    break;
                case Q:
                    //ccw
                    break;
                case E:
                    //cw
                    break;
            }
        });
    }

    @FXML
    public void initialize() {
        for (int i = 0, j = 0; i < Board.BOARD_ROWS - 4; i++) {
            if (j < Board.BOARD_COLUMNS) {
                ColumnConstraints column = new ColumnConstraints();
                column.setHgrow(SOMETIMES);
                myGridPane.getColumnConstraints().add(column);
            }
            RowConstraints row = new RowConstraints();
            row.setVgrow(SOMETIMES);
            myGridPane.getRowConstraints().add(row);
        }
        for (int i = 0; i < Board.BOARD_ROWS - 4; i++) {
            for (int j = 0; j < Board.BOARD_COLUMNS; j++) {
                Pane pane = new Pane();
                if (myBoard.getBoard()[i + 4][j] == true) {
                    pane.setStyle("-fx-border-color: #000000;" + "-fx-border-width: 2.5;" + "-fx-background-color: #d30000");
                    System.out.println("happened");
                } else {
                    pane.setStyle("-fx-border-color: #000000;" + "-fx-border-width: 2.5;" + "-fx-background-color: #ffffff");
                }
                myGridPane.add(pane, j, i);
            }
        }
    }

    private void updateGUI() {
        for (int i = 0; i < Board.BOARD_ROWS - 4; i++) {
            for (int j = 0; j < Board.BOARD_COLUMNS; j++) {
                Pane pane = new Pane();
                if (myBoard.getBoard()[i + 4][j] == true) {
                    pane.setStyle("-fx-border-color: #000000;" + "-fx-border-width: 2.5;" + "-fx-background-color: #d30000");
                    System.out.println("happened");
                } else {
                    pane.setStyle("-fx-border-color: #000000;" + "-fx-border-width: 2.5;" + "-fx-background-color: #ffffff");
                }
                myGridPane.getChildren().add(i, pane);
            }
        }
    }
}
