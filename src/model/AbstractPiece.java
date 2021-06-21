package model;

import java.awt.*;

public abstract class AbstractPiece {

    public static final Point INITIAL_ORIGIN = new Point(3, 4);

    private static final int INITIAL_STATE = 0;

    private final Point myOrigin = INITIAL_ORIGIN;

    private int myState = INITIAL_STATE;

    private final String myName;

    private final Point[][] myStates;

    private Point[] myBoardCoordinates = new Point[4];

    private boolean isPlaced = false;

    public AbstractPiece(final String theName, final Point[][] theStates) {
        myName = theName;
        myStates = theStates;
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
        for (int i = 0; i < myBoardCoordinates.length; i++) {
            myBoardCoordinates[i].translate(0, -1);
        }
        myOrigin.translate(0,-1);
    }

    public void moveRight() {
        for (int i = 0; i < myBoardCoordinates.length; i++) {
            myBoardCoordinates[i].translate(0, 1);
        }
        myOrigin.translate(0,1);
    }

    public void moveDown() {
        for (int i = 0; i < myBoardCoordinates.length; i++) {
            myBoardCoordinates[i].translate(1, 0);
        }
        myOrigin.translate(1,0);
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
        System.out.println(myStates[myState][0] + " " + myStates[myState][1] + " " + myStates[myState][2] + " " + myStates[myState][3]);
        updateCoordinates();
        System.out.println(myStates[myState][0] + " " + myStates[myState][1] + " " + myStates[myState][2] + " " + myStates[myState][3] + "\n");

    }

    public Point[] getBoardCoordinates() {
        return myBoardCoordinates;
    }

    public Point getOrigin() {
        return myOrigin;
    }

    public String getName() { return myName; }



}
