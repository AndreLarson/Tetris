package model;

import java.awt.*;

public class I_Piece extends AbstractPiece {

    private static final String MY_NAME = "I_Piece";

    private static final Point[][] MY_STATES = {
            {new Point(-2, 0), new Point(-2, 1), new Point(-2, 2), new Point(-2, 3)},
            {new Point(-0, 2), new Point(-1, 2), new Point(-2, 2), new Point(-3, 2)},
            {new Point(-1, 0), new Point(-1, 1), new Point(-1, 2), new Point(-1, 3)},
            {new Point(-0, 1), new Point(-1, 1), new Point(-2, 1), new Point(-3, 1)}
    };

    public I_Piece() {
        super(MY_NAME, MY_STATES);
    }

    public I_Piece(AbstractPiece thePiece) {
        super(thePiece);
    }

}
