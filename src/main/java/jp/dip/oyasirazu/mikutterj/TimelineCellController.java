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
    @FXML public Text name;
    @FXML public Text screenName;
    @FXML public Text message;

    public void setImage(String image) {
        icon.setImage(new Image(image));
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setScreenName(String screenName) {
        this.screenName.setText(screenName);
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}
