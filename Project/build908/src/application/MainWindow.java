package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class MainWindow {

	@FXML
    private ImageView minimize;

    @FXML
    private ImageView plusfriendicon;

    @FXML
    private TextArea inputArea;

    @FXML
    private ImageView friendicon;

    @FXML
    private ImageView backbutton;

    @FXML
    private ImageView messageShowicon;

    @FXML
    private ImageView shutdownShow;

    @FXML
    private ImageView avatar;

    @FXML
    private HBox title;
    
    @FXML
    private HBox tools;

    @FXML
    private ImageView plusfriendShowicon;

    @FXML
    private TextArea messageArea;

    @FXML
    private ImageView minimizeShow;

    @FXML
    private ImageView shutdown;

    @FXML
    private ImageView friendShowicon;

    @FXML
    private ImageView messageicon;

    @FXML
    private Label friendname;

    @FXML
    void onSwipeLeft(MouseEvent event) {

    }

    @FXML
    void minimize_onMousePressed(MouseEvent event) {
    	System.out.println("minimize");
    	Main.mainWindow.setIconified(true);
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
    	Main.mainWindow.close();
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
    void friend_onMousePressed(MouseEvent event) {
    	System.out.println("friendshow");
    	friendicon.setVisible(false);
    }
    
    @FXML
    void friendShow_onMousePressed(MouseEvent event) {
    	System.out.println("friendhide");
    	friendicon.setVisible(true);
    }
    
    @FXML
    void message_onMousePressed(MouseEvent event) {
    	System.out.println("messageshow");
    	messageicon.setVisible(false);
    }
    
    @FXML
    void messageShow_onMousePressed(MouseEvent event) {
    	System.out.println("messagehide");
    	messageicon.setVisible(true);
    }
    
    @FXML
    void plusfriend_onMouseExited(MouseEvent event) {
    	plusfriendicon.setVisible(true);
    }

    @FXML
    void plusfriend_omMouseEntered(MouseEvent event) {
    	plusfriendicon.setVisible(false);
    }
    
    @FXML
    void backbutton_onMouseClicked(MouseEvent event) {
    	title.setVisible(false);
    	messageArea.setVisible(false);
    	tools.setVisible(false);
    	inputArea.setVisible(false);
    }
    
}
