package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import model.Board;

import static javafx.scene.layout.Priority.SOMETIMES;

public class BoardController {

    @FXML
    private GridPane myGridPane;

    private Board myBoard;

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
                pane.setStyle("-fx-border-color: #000000;" + "-fx-border-width: 2.5");
                myGridPane.add(pane, j, i);
            }
        }
    }

}
