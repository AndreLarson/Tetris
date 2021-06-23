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

    /**
     * This method starts the game by spawning the initial piece.
     *
     */
    public void start() {
        spawnPiece();
    }

    /**
     * This method is called by the BoardController's animation timer to update the board at a set time interval.
     *
     * @return true if the game is still in progress, and false if the game is over.
     */
    public boolean step() {
        boolean result = false;
        if(!down()) {
            checkRows();
            if (!gameOver()) {
                result = true;
                spawnPiece();
            }
        } else {
            result = true;
        }
        return result;
    }

    /**
     * This method checks to see if the game is over. The game is considered over if a piece is placed above the
     * play area.
     *
     * @return true if the game is over, and false otherwise.
     */
    private boolean gameOver() {
        return (containsBoolean(myBoard[3], true) && (myCurrentPiece.getOrigin().x != 3));
    }

    /**
     * This method is called when the step method determines that moving down is not possible. If a row contains only
     * true values, the row will be deleted and all true values above the deleted rows will move down accordingly.
     *
     */
    private void checkRows() {
        int rowCount = 0;
        int topRow = 0;
        for(Point point : myCurrentPiece.getBoardCoordinates()) {
            if (!containsBoolean(myBoard[point.x], false)) {
                if (topRow == 0 || topRow > point.x) topRow = point.x;
                for (int i = 0; i < BOARD_COLUMNS; i++) {
                    myBoard[point.x][i] = false;
                }
                rowCount++;
            }
        }
        //move all true values above topRow down by rowCount
        for (int i = topRow - 1; i > 3; i--) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (myBoard[i][j]) {
                    myBoard[i][j] = false;
                    myBoard[i + rowCount][j] = true;
                }
            }
        }
    }

    /**
     * This method is used to check whether a row contains a specified boolean value.
     *
     * @param theRow the row of the board being checked
     * @param theBoolean the specified boolean value to check for
     * @return true if the specified boolean value is found within the row, and false otherwise.
     */
    private boolean containsBoolean(boolean[] theRow, boolean theBoolean) {
        boolean result = false;
        for(boolean value : theRow) {
            if (value == theBoolean) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * This method is used to update the board to match the pieces location.
     *
     * @param theBoolean if false the piece will be removed from the board, if true the piece will be added to the board.
     */
    public void updatePiece(final boolean theBoolean) {
        for(Point point : myCurrentPiece.getBoardCoordinates()) {
            myBoard[point.x][point.y] = theBoolean;
        }
    }

    /**
     * Will move the piece left if it is a valid option.
     *
     */
    public void left() {
        if (canMove('L')) {
            updatePiece(false);
            myCurrentPiece.moveLeft();
            updatePiece(true);
        }
    }

    /**
     * Will move the piece right if it is a valid option.
     *
     */
    public void right() {
        if (canMove('R')) {
            updatePiece(false);
            myCurrentPiece.moveRight();
            updatePiece(true);
        }
    }

    /**
     * Will move the piece down if it is a valid option.
     *
     * @return true if the piece moved down, and false otherwise
     */
    public boolean down() {
        boolean result = false;
        if (canMove('D')) {
            updatePiece(false);
            myCurrentPiece.moveDown();
            updatePiece(true);
            result = true;
        }
        return result;
    }

    /**
     * Will move the piece down until it cannot move down anymore.
     *
     */
    public void fastDrop() {
        updatePiece(false);
        while (canMove('D')) {
            myCurrentPiece.moveDown();
        }
        updatePiece(true);
    }

    /**
     * Checks to see if the current piece can move in a specified direction.
     *
     * @param theDirection 'L' is for left, 'R' is for right, 'D' is for down.
     * @return true if the move is valid, and false otherwise.
     */
    private boolean canMove(final char theDirection) {
        boolean result = true;
        switch (theDirection) {
            case 'L':
                for (Point point : myCurrentPiece.getBoardCoordinates()) {
                    if (point.y - 1 < 0 || (myBoard[point.x][point.y - 1] && myCurrentPiece.contains(new Point(point.x, point.y - 1)))) {
                        result = false;
                        break;
                    }
                }
                break;
            case 'R':
                for (Point point : myCurrentPiece.getBoardCoordinates()) {
                    if (point.y + 1 >= BOARD_COLUMNS || (myBoard[point.x][point.y + 1] && myCurrentPiece.contains(new Point(point.x, point.y + 1)))) {
                        result = false;
                        break;
                    }
                }
                break;
            case 'D':
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

    /**
     * Rotates the piece clockwise.
     *
     */
    public void CW() {
        updatePiece(false);
        myCurrentPiece.rotateCW();
        adjustPiece();
        updatePiece(true);
    }

    /**
     * Rotates the piece counter clockwise.
     *
     */
    public void CCW() {
        updatePiece(false);
        myCurrentPiece.rotateCCW();
        adjustPiece();
        updatePiece(true);
    }

    private AbstractPiece isValid(final AbstractPiece thePiece) {
        //if piece is valid return piece
        //otherwise use recursive backtracking to return a valid location by moving left right or down
        //count number of moves and return the one with the least amount of moves
        return null;
    }

    /**
     * Adjusts the piece to stay within bounds when a rotation moves it out of bounds.
     *
     */
    private void adjustPiece() {
        char theDirection = out();
        while (theDirection != 0) {
            switch (theDirection) {
                case 'L':
                    myCurrentPiece.moveRight();
                    break;
                case 'R':
                    myCurrentPiece.moveLeft();
                    break;
                case 'D':
                    myCurrentPiece.moveUp();
                    break;
            }
            theDirection = out();
        }
    }

    /**
     * Checks to see if the piece is out of bounds and returns the direction via char.
     *
     * @return 'L' if out on left bounds, 'R' if out on right bounds, 'D' if out on bottom bounds.
     */
    private char out() {
        char result = 0;
        for (Point point : myCurrentPiece.getBoardCoordinates()) {
            if (point.y < 0) {
                result = 'L';
                break;
            } else if (point.y >= BOARD_COLUMNS) {
                result = 'R';
                break;
            } else if (point.x >= BOARD_ROWS) {
                result = 'D';
                break;
            }
        }
        return result;
    }

    /**
     * Spawns a random piece.
     *
     */
    public void spawnPiece() {
        generateRandomPiece(RANDOM.nextInt(6) + 1);
    }

    /**
     * Chooses a piece based on a randomly generated number.
     *
     * @param thePiece randomly generated number.
     */
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

    /**
     * Sets myCurrentPiece to theCurrentPiece and updates the board.
     *
     * @param theCurrentPiece the future current piece.
     */
    public void setMyCurrentPiece(final AbstractPiece theCurrentPiece) {
        myCurrentPiece = theCurrentPiece;
        updatePiece(true);
    }

    /**
     * Returns myBoard.
     *
     * @return myBoard
     */
    public boolean[][] getBoard() { return myBoard; }

}
