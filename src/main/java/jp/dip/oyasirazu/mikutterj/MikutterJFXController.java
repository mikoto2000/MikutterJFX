package jp.dip.oyasirazu.mikutterj;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mikutter 用コントローラークラス。
 */
public class MikutterJFXController implements Initializable {

    private Stage rootStage;
    @FXML ListView<Message> homeTimeline;

    private static Logger logger = LoggerFactory.getLogger(MikutterJFXController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeTimeline.setCellFactory((list) -> {
            return new TimelineCell();
        });
    }

    /**
     * ルートの Stage を設定する。
     */
    public void setRootStage(Stage stage) {
        this.rootStage = stage;
    }

    /**
     * ルート Stage のアイコンを設定する。
     * アイコン設定が終わったら、ウィンドウを表示する。
     */
    public void setIcon(String iconPath) {
        Platform.runLater(() -> {
            rootStage.getIcons().add(new Image("file:" + iconPath));
            rootStage.show();
        });
    }

    /**
     * home timeline にメッセージを追加する。
     */
    public void addMessage(String icon, String screenName, String message) {
        homeTimeline.getItems().add(new Message(icon, screenName, message));
    }
}
