package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.awt.*;

public class ControlsController {

    @FXML
    private HBox myControls;

    private final String[] myControlList = new String[] {"up.png", "down.png", "left.png", "right.png", "enter.png",
            "a.png", "d.png"};

    private final String[] myControlDescription = new String[] {"Move Up", "Move Down", "Move Left", "Move right",
            "Start/Stop Game", "Rotate CCW", "Rotate CW"};

    @FXML
    public void initialize() {
        for (int i = 0; i < myControlList.length; i++) {
            ImageView img = new ImageView();
            ColorAdjust whiteOut = new ColorAdjust();
            whiteOut.setBrightness(1.0);
            img.setEffect(whiteOut);
            img.setImage(new Image(myControlList[i]));
            img.setFitWidth(40);
            img.setFitHeight(40);
            Label label = new Label();
            label.setText(myControlDescription[i]);
            label.setMaxHeight(40);
            label.setTextFill(Color.WHITE);
            myControls.getChildren().addAll(img, label);
        }
    }

}
