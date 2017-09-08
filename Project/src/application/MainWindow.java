package application;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainWindow {

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView shutdownShow;

    @FXML
    private ImageView avatar;

    @FXML
    private ImageView minimizeShow;

    @FXML
    private ImageView shutdown;

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

}
