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

    /**
     * アプリケーション。
     *
     * タイムラインに渡される。<br />
     * デフォルトブラウザで開くときに使われてます。
     */
    private Application application;

    /**
     * ルートステージ。
     *
     * アイコン設定に使われる。
     */
    private Stage rootStage;

    /**
     * サービス。
     *
     * mikutter の機能を呼び出すために使われてます。
     */
    private IRubyObject service;

    /** PostBox */
    @FXML private PostBoxController postboxController;

    /** ホームタイムライン */
    @FXML private TimelineController homeTimelineController;

    /** 通知タイムライン */
    @FXML private TimelineController mentionTimelineController;

    private static Logger logger = LoggerFactory.getLogger(MikutterJFXController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.debug("start MikutterJFXController initialize.");
        homeTimelineController.setTitle("home timeline.");
        mentionTimelineController.setTitle("mention timeline.");
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

    /**
     * サービスを設定する。
     *
     * 投稿機能を使用するために、PostBox に渡されます。
     */
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

    /**
     * mention timeline にメッセージを追加する。
     */
    public void addMention(IRubyObject message, String[] media_urls) {
        logger.debug("start addMention.");

        mentionTimelineController.addMessage(message, media_urls);
        logger.debug("end addMention.");
    }
}
