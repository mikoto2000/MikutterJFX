package jp.dip.oyasirazu.mikutterj;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


import org.jruby.RubySymbol;
import org.jruby.runtime.builtin.IRubyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Timeline 用コントローラークラス。
 */
public class TimelineController implements Initializable {

    /**
     * アプリケーション。
     *
     * 画像をデフォルトブラウザで開くために使われてます。
     */
    private Application application;

    /**
     * タイムライン。
     */
    @FXML ListView<Message> timeline;

    /**
     * タイムラインのタイトル。
     */
    @FXML private Label title;

    private static Logger logger = LoggerFactory.getLogger(MikutterJFXController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.debug("start initialize");
        timeline.setCellFactory((list) -> {
            return new TimelineCell();
        });
        logger.debug("end initialize");
    }

    /**
     * Application を設定する。
     */
    public void setApplication(Application application) {
        logger.debug("start setApplication.");
        this.application = application;
        timeline.setCellFactory((list) -> {
            return new TimelineCell(application);
        });
        logger.debug("end setApplication.");
    }

    /**
     * home timeline にメッセージを追加する。
     */
    public void addMessage(IRubyObject message, String[] media_urls) {
        logger.debug("start addMessage");

        IRubyObject user = callMethod(message, "user");

        timeline.getItems().add(
                new Message(getValue(user, "profile_image_url").toString(),
                    getValue(user, "idname").toString(),
                    getValue(user, "name").toString(),
                    callMethod(message, "to_show").toString(),
                    media_urls[0],
                    media_urls[1],
                    media_urls[2],
                    media_urls[3]));

        logger.debug("end addMessage");
    }

    /**
     * 引数なしの ruby メソッドを呼び出す。
     */
    private IRubyObject callMethod(IRubyObject object, String methodName) {
        return object.callMethod(object.getRuntime().getCurrentContext(), methodName);
    }

    /**
     * get メソッドを呼び出す。
     *
     * ruby でいう object[:key] に対応する処理らしい。
     */
    private IRubyObject getValue(IRubyObject object, String key) {
        return object.callMethod(object.getRuntime().getCurrentContext(), "get", RubySymbol.newSymbol(object.getRuntime(), key));
    }

    /**
     * タイムラインのタイトルを設定する。
     */
    public void setTitle(String titleStr) {
        title.setText(titleStr);
    }

    /**
     * タイムラインのタイトルを取得する。
     */
    public String setTitle() {
        return title.getText();
    }
}

