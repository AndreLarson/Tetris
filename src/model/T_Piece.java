package model;

import java.awt.*;

public class T_Piece extends AbstractPiece {

    private static final String MY_NAME = "T_Piece";

    private static final Point[][] MY_STATES = {
            {new Point(-1, 0), new Point(-2, 1), new Point(-1, 1), new Point(-1, 2)},
            {new Point(-0, 1), new Point(-1, 1), new Point(-2, 1), new Point(-1, 2)},
            {new Point(-1, 0), new Point(-1, 1), new Point(-0, 1), new Point(-1, 2)},
            {new Point(-1, 0), new Point(-0, 1), new Point(-1, 1), new Point(-2, 1)}
    };

    public T_Piece() {
        super(MY_NAME, MY_STATES);
    }

    public T_Piece(AbstractPiece thePiece) {
        super(thePiece);
    }

}
