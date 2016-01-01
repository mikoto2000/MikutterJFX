package jp.dip.oyasirazu.mikutterj;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.jruby.RubyString;
import org.jruby.RubySymbol;
import org.jruby.runtime.builtin.IRubyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mikutter 用コントローラークラス。
 */
public class MikutterJFXController implements Initializable {

    private Application application;
    private Stage rootStage;
    private IRubyObject service;

    @FXML PostBoxController postboxController;
    @FXML TimelineController homeTimelineController;

    private static Logger logger = LoggerFactory.getLogger(MikutterJFXController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.debug("start MikutterJFXController initialize.");
    }

    /**
     * ルートの Stage を設定する。
     */
    public void setRootStage(Stage stage) {
        logger.debug("start setRootStage.");
        this.rootStage = stage;
        logger.debug("end setRootStage.");
    }

    /**
     * Application を設定する。
     */
    public void setApplication(Application application) {
        logger.debug("start setApplication.");
        this.application = application;
        homeTimelineController.setApplication(application);
        logger.debug("end setApplication.");
    }

    /**
     * ルート Stage のアイコンを設定する。
     * アイコン設定が終わったら、ウィンドウを表示する。
     */
    public void setIcon(String iconPath) {
        logger.debug("start setIcon.");
        Platform.runLater(() -> {
            rootStage.getIcons().add(new Image("file:" + iconPath));
            rootStage.show();
        });
        logger.debug("end setIcon.");
    }

    public void setService(IRubyObject service) {
        logger.debug("start setService.");
        this.service = service;
        postboxController.setService(service);
        logger.debug("end setService.");
    }

    /**
     * home timeline にメッセージを追加する。
     */
    public void addMessage(IRubyObject message, String[] media_urls) {
        logger.debug("start addMessage.");

        homeTimelineController.addMessage(message, media_urls);
        logger.debug("end addMessage.");
    }
}
