package application;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MessageWindow {
	public static Stage windowStage;
    public String messageContext;

    @FXML
    private ImageView minimize;

    @FXML
    private Button surebutton;

    @FXML
    private ImageView shutdownShow;

    @FXML
    private ImageView minimizeShow;

    @FXML
    private ImageView shutdown;
    
    @FXML
    private Text messageText;
////////////////////////////////////////////////////////
    @FXML
    void minimize_onMouseEntered(MouseEvent event) {
    	minimize.setVisible(false);
    }

    @FXML
    void minimize_onMouseExited(MouseEvent event) {
    	minimize.setVisible(true);
    }

    @FXML
    void shutdown_onMouseEntered(MouseEvent event) {
    	shutdown.setVisible(false);
    }
    
    @FXML
    void shutdown_onMouseExited(MouseEvent event) {
    	shutdown.setVisible(true);
    }
    
    @FXML
    void minimize_onMousePressed(MouseEvent event) {
    	MessageWindow.windowStage.setIconified(true);
    }
    
    @FXML
    void shutdown_onMousePressed(MouseEvent event) {
    	MessageWindow.windowStage.close();
    }
///////////////////////////////////////////////////////
    @FXML
    void sure_onMouseClicked(MouseEvent event) {
    	MessageWindow.windowStage.close();
    }
    
    public Stage loadWindow() {
		try {
			Parent windowParent = FXMLLoader.load(getClass().getResource("/application/MessageLayout.fxml"));
			
			Text messageText = (Text)windowParent.lookup("#messageText");
			if(messageContext==null) {
				messageText.setText("null");
			}else {
				messageText.setText(messageContext);
			}
			
			Scene windowScene = new Scene(windowParent);
			windowScene.setFill(null);
			windowStage = new Stage();
			windowStage.setTitle("系统提示");
			windowStage.initStyle(StageStyle.TRANSPARENT);
			windowStage.setScene(windowScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return windowStage;
    }
    
}
