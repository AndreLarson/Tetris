package model;

import java.awt.*;

public class O_Piece extends AbstractPiece {

    private static final String MY_NAME = "O_Piece";

    private static final Point[][] MY_STATES = {
            {new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2)}
    }; //Checked

    public O_Piece() {
        super(MY_NAME, MY_STATES);
    }

}
