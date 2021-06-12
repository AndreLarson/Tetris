package model;

import java.awt.*;
import java.util.Random;

//TODO create helper method for all movement methods

public class Board {

    private static final int BOARD_ROWS = 24;

    private static final int BOARD_COLUMNS = 10;

    private static Random RANDOM = new Random();

    private final boolean[][] myBoard;

    private AbstractPiece myCurrentPiece;

    public Board() {
        myBoard = new boolean[BOARD_ROWS][BOARD_COLUMNS];
        myCurrentPiece = null;
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
        clearPiece();
        myCurrentPiece.moveLeft();
        updateBoard();
    }

    public void right() {
        clearPiece();
        myCurrentPiece.moveRight();
        updateBoard();
    }

    public void down() {
        clearPiece();
        myCurrentPiece.moveDown();
        updateBoard();
    }

    public void CW() {
        clearPiece();
        myCurrentPiece.rotateCW();
        updateBoard();
    }

    public void CCW() {
        clearPiece();
        myCurrentPiece.rotateCCW();
        updateBoard();
    }

    public void step() {
        down();
        //piece cant move down
        //piece freezes
        spawnPiece();
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
