package jp.dip.oyasirazu.mikutterj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TimelineCell
 */
public class TimelineCell extends ListCell<Message> {

    /**
     * アプリケーション。
     *
     * デフォルトブラウザで画像を開くために使われてます。
     */
    private Application application;

    private static Logger logger = LoggerFactory.getLogger(TimelineCell.class);

    /**
     * デフォルトコンストラクタ。
     *
     * なにもしない。
     **/
    public TimelineCell() {
        logger.debug("start TimelineCell constructor.");
        logger.debug("end TimelineCell constructor.");
    }

    /**
     * コンストラクタ。
     *
     * アプリケーションを受け取ってインスタンスを作る。
     **/
    public TimelineCell(Application application) {
        logger.debug("start TimelineCell constructor with application.");
        this.application = application;
        logger.debug("end TimelineCell constructor with application.");
    }

    /**
     * ListCell 更新。
     */
    @Override
    public void updateItem(Message item, boolean isEmpty) {
        super.updateItem(item, isEmpty);

        logger.debug("start updateItem.");

        // 空なら空 Pane を作る。
        if (isEmpty || item == null) {
            Pane p = new Pane();
            setGraphic(p);
            return;
        }

        // 空でなければ TimelineCell のレイアウトを読み込んで設定する。
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/TimelineCell.fxml"));
            Pane root = loader.load();
            TimelineCellController c = loader.getController();
            c.setApplication(application);
            c.setMessage(item);
            c.setBinding(getListView());
            setGraphic(root);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("layout load erroe!");
        }

        logger.debug("end updateItem.");

        return;
    }
}
