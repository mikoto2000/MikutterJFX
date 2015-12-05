package jp.dip.oyasirazu.mikutterj;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import lombok.Data;

/**
 * TimelineCellController
 */
public class TimelineCellController {
    @FXML public ImageView icon;
    @FXML public Text text;

    public void setImage(String image) {
        icon.setImage(new Image(image));
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
