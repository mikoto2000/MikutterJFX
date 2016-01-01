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

    private Application application;

    private static Logger logger = LoggerFactory.getLogger(TimelineCell.class);

    public TimelineCell() {
        logger.debug("start TimelineCell constructor.");
        logger.debug("end TimelineCell constructor.");
    }

    public TimelineCell(Application application) {
        logger.debug("start TimelineCell constructor with application.");
        this.application = application;
        logger.debug("end TimelineCell constructor with application.");
    }

    @Override
    public void updateItem(Message item, boolean isEmpty) {
        super.updateItem(item, isEmpty);

        logger.debug("start updateItem.");
        if (isEmpty || item == null) {
            Pane p = new Pane();
            setGraphic(p);
            return;
        }

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
