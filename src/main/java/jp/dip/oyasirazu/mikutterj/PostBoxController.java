package jp.dip.oyasirazu.mikutterj;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import org.jruby.RubyHash;
import org.jruby.RubyString;
import org.jruby.RubySymbol;
import org.jruby.runtime.builtin.IRubyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PostBoxController
 */
public class PostBoxController implements Initializable {

    @FXML private TextField postbox;
    @FXML private Button send;
    private IRubyObject service;

    private static Logger logger = LoggerFactory.getLogger(PostBoxController.class);

    public void initialize(URL location, ResourceBundle resource) {
        logger.debug("start initialize.");
        send.setOnAction((event) -> {
            post(postbox.getText());
        });
        logger.debug("end initialize.");
    }

    public void post(String message) {
        logger.debug("start post.");
        service.callMethod(
                service.getRuntime().getCurrentContext(),
                "post", RubyString.newString(service.getRuntime(), message));
        logger.debug("end post.");
    }

    public void setService(IRubyObject service) {
        logger.debug("start setService.");
        this.service = service;
        logger.debug("end setService.");
    }
}
