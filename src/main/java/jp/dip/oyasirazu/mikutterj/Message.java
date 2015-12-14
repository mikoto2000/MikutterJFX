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
    private String icon;
    private String name;
    private String screenName;
    private String message;
    // TODO: リスト化的なことがしたい
    private String media1;
    private String media2;
    private String media3;
    private String media4;
}
