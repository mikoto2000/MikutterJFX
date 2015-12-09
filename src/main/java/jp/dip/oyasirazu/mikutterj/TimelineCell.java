package jp.dip.oyasirazu.mikutterj;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * TimelineCell
 */
public class TimelineCell extends ListCell<Message> {

    @Override
    public void updateItem(Message item, boolean isEmpty) {
        super.updateItem(item, isEmpty);
        if (isEmpty || item == null) {
            Pane p = new Pane();
            setGraphic(p);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/TimelineCell.fxml"));
            Pane root = loader.load();
            TimelineCellController c = loader.getController();
            c.setMessage(item);
            setGraphic(root);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("layout load erroe!");
        }

        return;
    }
}
