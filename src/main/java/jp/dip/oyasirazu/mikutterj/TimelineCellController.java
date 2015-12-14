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
    // TODO: リスト化的なことがしたい
    @FXML public ImageView media1;
    @FXML public ImageView media2;
    @FXML public ImageView media3;
    @FXML public ImageView media4;

    public void setMessage(Message message) {
        icon.setImage(new Image(message.getIcon()));
        name.setText(message.getName());
        screenName.setText(message.getScreenName());
        this.message.setText(message.getMessage());

        if (!message.getMedia1().isEmpty()) {
            media1.setImage(new Image(message.getMedia1(), 100.0, 100.0, true, true, true));
        }

        if (!message.getMedia2().isEmpty()) {
            media2.setImage(new Image(message.getMedia2(), 100.0, 100.0, true, true, true));
        }

        if (!message.getMedia3().isEmpty()) {
            media3.setImage(new Image(message.getMedia3(), 100.0, 100.0, true, true, true));
        }

        if (!message.getMedia4().isEmpty()) {
            media4.setImage(new Image(message.getMedia4(), 100.0, 100.0, true, true, true));
        }
    }

    /**
     * メッセージの横幅バインディング設定。
     *
     * TODO: スクロールバーのサイズを取得できないものか...。
     */
    public void setBinding(javafx.scene.control.Control c) {
        message.wrappingWidthProperty().bind(
                c.widthProperty().subtract(icon.getImage().getWidth() + 30));
    }
}
