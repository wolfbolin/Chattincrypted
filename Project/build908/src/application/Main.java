package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	protected static Stage loginWindow;
	protected static Stage mainWindow;
	protected static Stage findWindow;
	protected static Stage messageWindw;
	
	
	private String loginPath = "/application/LoginLayout.fxml";
	private String mainPath = "/application/MainLayout.fxml";
	private String messagePath = "/application/MessageLayout.fxml";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			loginWindow = openWindow(loginWindow,loginPath,"µÇÂ¼");
			mainWindow = openWindow(mainWindow,mainPath,"ÃÜÁÄ");
			//openWindow(loginWindow,loginPath,"µÇÂ¼");
			loginWindow.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Stage openWindow(Stage windowStage,String url,String title) throws IOException {
		Parent windowParent = FXMLLoader.load(getClass().getResource(url));
		Scene windowScene = new Scene(windowParent);
		windowScene.setFill(null);
		windowStage = new Stage();
		windowStage.setTitle(title);
		windowStage.initStyle(StageStyle.TRANSPARENT);
		windowStage.setScene(windowScene);
		return windowStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
