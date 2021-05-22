package model;

import java.awt.*;

public class S_Piece extends AbstractPiece {

    private static final String MY_NAME = "S_Piece";

    private static final Point[][] MY_STATES = {
            {new Point(0, 1), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
            {new Point(1, 2), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(0, 2), new Point(0, 1), new Point(1, 1), new Point(1, 0)}
    };

    public S_Piece() {
        super(MY_NAME, MY_STATES);
    }

}
