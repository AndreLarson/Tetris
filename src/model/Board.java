package model;

import java.awt.*;
import java.util.Random;

//TODO create helper method for all movement methods

public class Board {

    public static final int BOARD_ROWS = 24;

    public static final int BOARD_COLUMNS = 10;

    private static final Random RANDOM = new Random();

    private final boolean[][] myBoard;

    private AbstractPiece myCurrentPiece;

    public Board() {
        myBoard = new boolean[BOARD_ROWS][BOARD_COLUMNS];
        myCurrentPiece = null;
    }

    //TODO delete when finished as this is only used for testing
    public void start() {
        spawnPiece();
    }

    public void step() {
        if (canMove("D")) {
            down();
        } else {
            checkRows();
            spawnPiece();
        }
    }

    private void checkRows() {
        int rowCount = 0;
        int topRow = 0;
        for(Point point : myCurrentPiece.getBoardCoordinates()) {
            if (!containsFalse(myBoard[point.x])) {
                if (topRow == 0 || topRow > point.x) {
                    topRow = point.x;
                }
                for (int i = 0; i < BOARD_COLUMNS; i++) {
                    myBoard[point.x][i] = false;
                }
                rowCount++;
            }
        }
        for (int i = topRow - 1; i > 3; i--) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (myBoard[i][j]) {
                    myBoard[i][j] = false;
                    myBoard[i + rowCount][j] = true;
                }
            }
        }
        //move all true values above firstRow down by rowCount
    }

    private boolean containsFalse(boolean[] theRow) {
        boolean result = false;
        for(boolean value : theRow) {
            if (!value) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void updateBoard() {
        for(Point point : myCurrentPiece.getBoardCoordinates()) {
            myBoard[point.x][point.y] = true;
        }
    }

    public void clearPiece() {
        for(Point point : myCurrentPiece.getBoardCoordinates()) {
            myBoard[point.x][point.y] = false;
        }
    }

    public void left() {
        if (canMove("L")) {
            clearPiece();
            myCurrentPiece.moveLeft();
            updateBoard();
        }
    }

    public void right() {
        if (canMove("R")) {
            clearPiece();
            myCurrentPiece.moveRight();
            updateBoard();
        }
    }

    public void down() {
        if (canMove("D")) {
            clearPiece();
            myCurrentPiece.moveDown();
            updateBoard();
        }
    }

    public void fastDrop() {
        clearPiece();
        while (canMove("D")) {
            myCurrentPiece.moveDown();
        }
        updateBoard();
    }

    private boolean canMove(final String theDirection) {
        boolean result = true;
        switch (theDirection) {
            case "L":
                for (Point point : myCurrentPiece.getBoardCoordinates()) {
                    if (point.y - 1 < 0 || (myBoard[point.x][point.y - 1] && myCurrentPiece.contains(new Point(point.x, point.y - 1)))) {
                        result = false;
                        break;
                    }
                }
                break;
            case "R":
                for (Point point : myCurrentPiece.getBoardCoordinates()) {
                    if (point.y + 1 >= BOARD_COLUMNS || (myBoard[point.x][point.y + 1] && myCurrentPiece.contains(new Point(point.x, point.y + 1)))) {
                        result = false;
                        break;
                    }
                }
                break;
            case "D":
                for (Point point : myCurrentPiece.getBoardCoordinates()) {
                    if (point.x + 1 >= BOARD_ROWS || (myBoard[point.x + 1][point.y] && myCurrentPiece.contains(new Point(point.x + 1, point.y)))) {
                        result = false;
                        break;
                    }
                }
                break;
        }
        return result;
    }

    public void CW() {
        clearPiece();
        myCurrentPiece.rotateCW();
        adjustPiece();
        updateBoard();
    }

    public void CCW() {
        clearPiece();
        myCurrentPiece.rotateCCW();
        adjustPiece();
        updateBoard();
    }

    private void adjustPiece() {
        while (outLeft()) {
            myCurrentPiece.moveRight();
        }
        while (outRight()) {
            myCurrentPiece.moveLeft();
        }
        while (outBelow()) {
            myCurrentPiece.moveUp();
        }
    }

    private boolean outLeft() {
        boolean result = false;
        for (Point point : myCurrentPiece.getBoardCoordinates()) {
            if (point.y < 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean outRight() {
        boolean result = false;
        for (Point point : myCurrentPiece.getBoardCoordinates()) {
            if (point.y >= BOARD_COLUMNS) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean outBelow() {
        boolean result = false;
        for (Point point : myCurrentPiece.getBoardCoordinates()) {
            if (point.x >= BOARD_ROWS) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void setMyCurrentPiece(final AbstractPiece theCurrentPiece) {
        myCurrentPiece = theCurrentPiece;
        updateBoard();
    }

    public void spawnPiece() {
        generateRandomPiece(RANDOM.nextInt(6) + 1);
    }

    public void generateRandomPiece(int thePiece) {
        switch (thePiece) {
            case 1:
                setMyCurrentPiece(new I_Piece());
                break;
            case 2:
                setMyCurrentPiece(new J_Piece());
                break;
            case 3:
                setMyCurrentPiece(new L_Piece());
                break;
            case 4:
                setMyCurrentPiece(new O_Piece());
                break;
            case 5:
                setMyCurrentPiece(new S_Piece());
                break;
            case 6:
                setMyCurrentPiece(new T_Piece());
                break;
            case 7:
                setMyCurrentPiece(new Z_Piece());
                break;

        }
    }

    public boolean[][] getBoard() { return myBoard; }

}
