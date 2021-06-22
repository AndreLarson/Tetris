package model;

import java.awt.*;

public abstract class AbstractPiece {

    public static final Point INITIAL_ORIGIN = new Point(3, 4);

    private static final int INITIAL_STATE = 0;

    private final Point myOrigin;

    private int myState;

    private final String myName;

    private final Point[][] myStates;

    private Point[] myBoardCoordinates;

    public AbstractPiece(final String theName, final Point[][] theStates) {
        myName = theName;
        myStates = theStates;
        myOrigin = new Point(INITIAL_ORIGIN);
        myState = INITIAL_STATE;
        myBoardCoordinates = new Point[4];
        updateCoordinates();
    }

    private void updateCoordinates() {
        Point[] result = new Point[myStates[myState].length];
        for (int i = 0; i < myStates[myState].length; i++) {
            Point point = new Point(myStates[myState][i]);
            point.translate(myOrigin.x, myOrigin.y);
            result[i] = point;
        }
        myBoardCoordinates = result;
    }

    public void moveLeft() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(0, -1);
        }
        myOrigin.translate(0,-1);
    }

    public void moveRight() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(0, 1);
        }
        myOrigin.translate(0,1);
    }

    public void moveDown() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(1, 0);
        }
        myOrigin.translate(1,0);
    }

    public void moveUp() {
        for (Point myBoardCoordinate : myBoardCoordinates) {
            myBoardCoordinate.translate(-1, 0);
        }
        myOrigin.translate(-1,0);
    }

    public void rotateCW() {
        myState++;
        if (myState == 4) {
            myState = 0;
        }
        updateCoordinates();
    }

    public void rotateCCW() {
        myState--;
        if(myState == -1) {
            myState = 3;
        }
        updateCoordinates();

    }

    public Point[] getBoardCoordinates() {
        return myBoardCoordinates;
    }

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

    public String getName() { return myName; }

}
