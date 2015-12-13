package jp.dip.oyasirazu.mikutterj;

import javafx.fxml.FXML;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Message
 */
@Data
@AllArgsConstructor
public class Message {
    @FXML private String icon;
    @FXML private String name;
    @FXML private String screenName;
    @FXML private String message;
    // TODO: リスト化的なことがしたい
    @FXML private String media1;
    @FXML private String media2;
    @FXML private String media3;
    @FXML private String media4;
}
