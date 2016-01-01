package jp.dip.oyasirazu.mikutterj;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import lombok.Data;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TimelineCellController
 */
public class TimelineCellController {

    /** ユーザーアイコン */
    @FXML public ImageView icon;

    /** ユーザー名 */
    @FXML public Text name;

    /** スクリーンネーム */
    @FXML public Text screenName;

    /** メッセージ本文 */
    @FXML public Text message;

    // TODO: リスト化的なことがしたい
    /** 添付画像その 1 */
    @FXML public ImageView media1;
    /** 添付画像その 2 */
    @FXML public ImageView media2;
    /** 添付画像その 3 */
    @FXML public ImageView media3;
    /** 添付画像その 4 */
    @FXML public ImageView media4;

    /** デフォルトブラウザで開くために使う Application */
    @Setter private Application application;

    // デフォルトブラウザで開くために使う URL
    private String media1Url;
    private String media2Url;
    private String media3Url;
    private String media4Url;

    private static Logger logger = LoggerFactory.getLogger(TimelineCellController.class);

    /**
     * Message を受け取り、各パーツに設定する。
     */
    public void setMessage(Message message) {
        logger.debug("start setMessage");

        icon.setImage(new Image(message.getIcon()));
        name.setText(message.getName());
        screenName.setText(message.getScreenName());
        this.message.setText(message.getMessage());

        if (!message.getMedia1().isEmpty()) {
            media1.setImage(new Image(message.getMedia1(), 100.0, 100.0, true, true, true));
            media1Url = message.getMedia1();
        }

        if (!message.getMedia2().isEmpty()) {
            media2.setImage(new Image(message.getMedia2(), 100.0, 100.0, true, true, true));
            media2Url = message.getMedia2();
        }

        if (!message.getMedia3().isEmpty()) {
            media3.setImage(new Image(message.getMedia3(), 100.0, 100.0, true, true, true));
            media3Url = message.getMedia3();
        }

        if (!message.getMedia4().isEmpty()) {
            media4.setImage(new Image(message.getMedia4(), 100.0, 100.0, true, true, true));
            media4Url = message.getMedia4();
        }

        logger.debug("end setMessage");
    }

    /**
     * メッセージの横幅バインディング設定。
     *
     * TODO: スクロールバーのサイズを取得できないものか...。
     */
    public void setBinding(javafx.scene.control.Control c) {
        logger.debug("start setBinding");
        message.wrappingWidthProperty().bind(
                c.widthProperty().subtract(icon.getImage().getWidth() + 30));
        logger.debug("end setBinding");
    }

    @FXML
    private void onMediaClick1(MouseEvent evt) {
        logger.debug("start onMediaClick1 with application: {}", application);
        if (application != null && !media1Url.isEmpty()) {
            application.getHostServices().showDocument(media1Url);
        }
    }

    @FXML
    private void onMediaClick2(MouseEvent evt) {
        logger.debug("start onMediaClick2 with application: {}", application);
        if (application != null && !media2Url.isEmpty()) {
            application.getHostServices().showDocument(media2Url);
        }
    }

    @FXML
    private void onMediaClick3(MouseEvent evt) {
        logger.debug("start onMediaClick3 with application: {}", application);
        if (application != null && !media3Url.isEmpty()) {
            application.getHostServices().showDocument(media3Url);
        }
    }

    @FXML
    private void onMediaClick4(MouseEvent evt) {
        logger.debug("start onMediaClick4 with application: {}", application);
        if (application != null && !media4Url.isEmpty()) {
            application.getHostServices().showDocument(media4Url);
        }
    }
}
