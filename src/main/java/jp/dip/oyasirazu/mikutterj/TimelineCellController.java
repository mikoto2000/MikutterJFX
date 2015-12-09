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

    public void setMessage(Message message) {
        icon.setImage(new Image(message.getIcon()));
        name.setText(message.getName());
        screenName.setText(message.getScreenName());
        this.message.setText(message.getMessage());
    }
}
