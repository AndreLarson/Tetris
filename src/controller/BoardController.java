package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Board;

public class BoardController {

    private static final int BOARD_WIDTH = 350;

    private static final int BOARD_HEIGHT = 600;

    private static final int RECT_WIDTH = BOARD_WIDTH / (Board.BOARD_COLUMNS);

    private static final int RECT_HEIGHT = BOARD_HEIGHT / (Board.BOARD_ROWS - 4);

    private static final int BORDER_WIDTH = 3;

    @FXML
    private Canvas myCanvas;

    private GraphicsContext myGraphics;

    private final Board myBoard;

    private final AnimationTimer myTimer;

    public BoardController() {
        myBoard = new Board();
        myTimer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 500_000_000) {
                    myBoard.step();
                    updateGUI();
                    lastUpdate = now;
                }
            }
        };
    }

    @FXML
    public void initialize() {
        myCanvas.setHeight(BOARD_HEIGHT);
        myCanvas.setWidth(BOARD_WIDTH);
        myGraphics = myCanvas.getGraphicsContext2D();
        for (int i = 0; i < RECT_HEIGHT * (Board.BOARD_ROWS - 4); i += RECT_HEIGHT) {
            for (int j = 0; j < RECT_WIDTH * Board.BOARD_COLUMNS; j += RECT_WIDTH) {
                drawSquare(j, i, false);
            }
        }
    }

    private void drawSquare(int j, int i, boolean theValue) {
        myGraphics.setFill(Color.BLACK);
        myGraphics.fillRect(j, i, RECT_WIDTH, RECT_HEIGHT);
        if (!theValue) {
            myGraphics.setFill(Color.WHITE);
        } else {
            myGraphics.setFill(Color.ORANGE);
        }
        myGraphics.fillRect(j + BORDER_WIDTH, i + BORDER_WIDTH, RECT_WIDTH - (BORDER_WIDTH * 2), RECT_HEIGHT - (BORDER_WIDTH * 2));
    }

    private void updateGUI() {
        for (int i = 0; i < Board.BOARD_ROWS - 4; i++) {
            for (int j = 0; j < Board.BOARD_COLUMNS; j++) {
                drawSquare(j * RECT_WIDTH, i * RECT_HEIGHT, myBoard.getBoard()[i + 4][j]);
            }
        }
    }

    public void setStage(Stage theStage) {
        theStage.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            switch (e.getCode()) {
                case SPACE:
                    //start
                    //if started stop
                    myBoard.start();
                    myTimer.start();
                    break;
                case UP:
                    //fast drop
                    myBoard.fastDrop();
                    updateGUI();
                    break;
                case DOWN:
                    //move down
                    myBoard.down();
                    updateGUI();
                    break;
                case LEFT:
                    //move left
                    myBoard.left();
                    updateGUI();
                    break;
                case RIGHT:
                    //move right
                    myBoard.right();
                    updateGUI();
                    break;
                case Q:
                    //ccw
                    myBoard.CCW();
                    updateGUI();
                    break;
                case E:
                    //cw
                    myBoard.CW();
                    updateGUI();
                    break;
            }
        });
    }

}
