package model;

import java.awt.*;

public class L_Piece extends AbstractPiece {

    private static final String MY_NAME = "L_Piece";

    private static final Point[][] MY_STATES = {
            {new Point(-1, 0), new Point(-1, 1), new Point(-1, 2), new Point(-2, 2)},
            {new Point(-2, 1), new Point(-1, 1), new Point(-0, 1), new Point(-0, 2)},
            {new Point(-0, 0), new Point(-1, 0), new Point(-1, 1), new Point(-1, 2)},
            {new Point(-2, 0), new Point(-2, 1), new Point(-1, 1), new Point(-0, 1)}
    };

    public L_Piece() {
        super(MY_NAME, MY_STATES);
    }

    public L_Piece(AbstractPiece thePiece) {
        super(thePiece);
    }

}
