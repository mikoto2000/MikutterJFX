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

import org.jruby.RubySymbol;
import org.jruby.runtime.builtin.IRubyObject;
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
    public void addMessage(IRubyObject message, String[] media_urls) {
        IRubyObject user = callMethod(message, "user");

        homeTimeline.getItems().add(
                new Message(getValue(user, "profile_image_url").toString(),
                    getValue(user, "idname").toString(),
                    getValue(user, "name").toString(),
                    callMethod(message, "to_show").toString(),
                    media_urls[0],
                    media_urls[1],
                    media_urls[2],
                    media_urls[3]));
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
}
