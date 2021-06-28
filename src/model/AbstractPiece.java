package model;

import java.awt.*;

/**
 * This abstract class contains all the non-abstract attributes and behaviors for the tetris pieces.
 *
 */
public abstract class AbstractPiece {

    /** The spawn point of all new tetris pieces */
    public static final Point INITIAL_ORIGIN = new Point(3, 4);

    /** The initial state of all new tetris pieces */
    private static final int INITIAL_STATE = 0;

    /** The origin that the tetris piece moves about */
    private final Point myOrigin;

    /** The current state of the tetris piece */
    private int myState;

    /** An array that holds the local coordinates of the tetris piece */
    private final Point[][] myStates;

    /** The coordinates of the tetris piece in relation to the tetris board */
    private Point[] myBoardCoordinates;

    /** The name of the tetris piece */
    private final String myName;

    /**
     * The parent constructor for all tetris piece child classes.
     *
     * @param theName the name of the tetris piece
     * @param theStates the local coordinates of all the states of the tetris piece
     */
    public AbstractPiece(final String theName,  final Point[][] theStates) {
        myName = theName;
        myStates = theStates;
        myOrigin = new Point(INITIAL_ORIGIN);
        myState = INITIAL_STATE;
        myBoardCoordinates = new Point[4];
        updateCoordinates();
    }

    /**
     * The constructor used to make a copy of a tetris piece
     *
     * @param thePiece the piece to be copied
     */
    public  AbstractPiece(final AbstractPiece thePiece) {
        myName = thePiece.myName;
        myStates = thePiece.myStates;
        myOrigin = thePiece.myOrigin;
        myState = thePiece.myState;
        myBoardCoordinates = thePiece.myBoardCoordinates;
    }

    /**
     * Updates the board coordinates of a tetris piece after the piece has been moved.
     *
     */
    private void updateCoordinates() {
        Point[] result = new Point[myStates[myState].length];
        for (int i = 0; i < myStates[myState].length; i++) {
            Point point = new Point(myStates[myState][i]);
            point.translate(myOrigin.x, myOrigin.y);
            result[i] = point;
        }
        myBoardCoordinates = result;
    }

    /**
     * Moves the piece left.
     *
     */
    public void moveLeft() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(0, -1);
        }
        myOrigin.translate(0,-1);
    }

    /**
     * Moves the piece right.
     *
     */
    public void moveRight() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(0, 1);
        }
        myOrigin.translate(0,1);
    }

    /**
     * Moves the piece down.
     *
     */
    public void moveDown() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(1, 0);
        }
        myOrigin.translate(1,0);
    }

    /**
     * Moves the piece up. This is only used to keep the piece in bounds after a rotation.
     *
     */
    public void moveUp() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(-1, 0);
        }
        myOrigin.translate(-1,0);
    }

    /**
     * Rotates the piece clockwise.
     *
     */
    public void rotateCW() {
        myState++;
        if (myState == 4) {
            myState = 0;
        }
        updateCoordinates();
    }

    /**
     * Rotates the piece counter-clockwise.
     *
     */
    public void rotateCCW() {
        myState--;
        if(myState == -1) {
            myState = 3;
        }
        updateCoordinates();
    }

    /**
     * Returns the board coordinates of the piece.
     *
     * @return myBoardCoordinates
     */
    public Point[] getBoardCoordinates() {
        return myBoardCoordinates;
    }

    /**
     * Checks to see if the piece contains a certain point on the board.
     *
     * @param thePoint the desired point
     * @return true if it contains the point and false otherwise.
     */
    public boolean contains(Point thePoint) {
        boolean result = false;
        for (Point point : myBoardCoordinates) {
            if (point.equals(thePoint)) {
                result = true;
                break;
            }
        }
        return !result;
    }

    /**
     * Returns the origin that the piece moves about.
     *
     * @return myOrigin
     */
    public Point getOrigin() {
        return myOrigin;
    }

    /**
     * Returns the name of the piece
     *
     * @return myName
     */
    public String getName() {
        return myName;
    }

}
