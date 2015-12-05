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
    @FXML private String screenName;
    @FXML private String message;
}
