package application;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginWindow {

    @FXML
    private ImageView minimize;

    @FXML
    private CheckBox remember;

    @FXML
    private CheckBox autologin;

    @FXML
    private PasswordField password;

    @FXML
    private Label nickname;

    @FXML
    private ImageView shutdownShow;

    @FXML
    private ImageView avatar;

    @FXML
    private ImageView minimizeShow;

    @FXML
    private ImageView shutdown;

    @FXML
    private TextField username;

    @FXML
    void minimize_onMousePressed(MouseEvent event) {
    	System.out.println("minimize");
    	Main.loginWindow.setIconified(true);
    }
    
    @FXML
    void minimize_onMouseEntered(MouseEvent event) {
    	minimize.setVisible(false);
    }

    @FXML
    void minimize_onMouseExited(MouseEvent event) {
    	minimize.setVisible(true);
    }

    @FXML
    void shutdown_onMousePressed(MouseEvent event) {
    	System.out.println("shutdown");
    	Main.loginWindow.close();
        System.exit(0);
    }

    @FXML
    void shutdown_onMouseExited(MouseEvent event) {
    	shutdown.setVisible(true);
    }

    @FXML
    void shutdown_onMouseEntered(MouseEvent event) {
    	shutdown.setVisible(false);
    }

    @FXML
    void login(MouseEvent event) {
    	System.out.println(remember.isSelected());
    	System.out.println(autologin.isSelected());
    	Main.loginWindow.close();
    	Main.mainWindow.show();
    }

}
