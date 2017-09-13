package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginWindow {
	public static Stage windowStage;

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
	private Button login;

	@FXML
	void minimize_onMousePressed(MouseEvent event) {
		LoginWindow.windowStage.setIconified(true);
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
		LoginWindow.windowStage.close();
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
	void username_onKeyTyped(KeyEvent event) {

		String format = "[A-Za-z0-9]*";
		String userName = "";
		if (Pattern.matches(format, event.getCharacter())) {
			userName += username.getText() + event.getCharacter();
		} else {
			userName += username.getText();
		}

		if (!Pattern.matches(format, userName)) {
			username.setText("");
			return;
		}
		System.out.println("username=" + userName);

		File avatarFile = new File(DefaultDefine.iconPath + userName + ".jpg");
		if (avatarFile.exists()) {
			System.out.println("username icon exists");
			nickname.setText(userName);
			avatar.setImage(new Image("file:\\" + DefaultDefine.iconPath + userName + ".jpg"));
			avatar.setFitHeight(150);
			avatar.setFitWidth(150);
		} else {
			System.out.println("username icon not exists");
			nickname.setText("");
			avatar.setImage(new Image("file:\\" + DefaultDefine.iconPath + "default.jpg"));
			avatar.setFitHeight(150);
			avatar.setFitWidth(150);
		}
	}

	@FXML
	void login(MouseEvent event) {
		if (Main.messageManner.getNetworkStatus() == false) {
			MessageWindow messageWindow = new MessageWindow();
			messageWindow.messageContext = new String("网络异常，请检查网络连接");
			Stage messageStage = messageWindow.loadWindow();
			messageStage.show();
			return;
		}
		username.setDisable(true);
		password.setDisable(true);
		remember.setDisable(true);
		autologin.setDisable(true);
		login.setDisable(true);
		login.setText("登    录    中");
		if (user_login(remember.isSelected(), autologin.isSelected(), username.getText(), password.getText())) {
			Main.user = new FriendInfo();
			Main.user.setUsername(username.getText());
			Main.user.setPassword(password.getText());
			LoginWindow.windowStage.close();
			MainWindow mainWindow = new MainWindow();
			Stage mainStage = mainWindow.loadWindow();
			mainStage.show();
		} else {
			MessageWindow messageWindow = new MessageWindow();
			messageWindow.messageContext = new String("账户不存在或密码错误");
			Stage messageStage = messageWindow.loadWindow();
			messageStage.show();

			username.setDisable(false);
			password.setDisable(false);
			remember.setDisable(false);
			autologin.setDisable(false);
			login.setDisable(false);
			login.setText("登        录");
		}
	}

	public Stage loadWindow() {
		try {
			Parent windowParent = FXMLLoader.load(getClass().getResource("/application/LoginLayout.fxml"));
			Scene windowScene = new Scene(windowParent);

			rebuildPane(windowParent);

			windowScene.setFill(null);
			windowStage = new Stage();
			windowStage.setTitle("登录");
			windowStage.initStyle(StageStyle.TRANSPARENT);
			windowStage.setScene(windowScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return windowStage;
	}

	private void rebuildPane(Parent windowParent) {
		CheckBox remember = (CheckBox) windowParent.lookup("#remember");
		CheckBox autologin = (CheckBox) windowParent.lookup("#autologin");
		TextField username = (TextField) windowParent.lookup("#username");
		PasswordField password = (PasswordField) windowParent.lookup("#password");
		ImageView avatar = (ImageView) windowParent.lookup("#avatar");
		Label nickname = (Label) windowParent.lookup("#nickname");
		
		File file = new File("D:\\Buffer\\Chattincrypted", "userdata");
		if (!file.exists()) {
			return;
		}
		BufferedReader reader = null;
		String rememberString = null;
		String autologinString = null;
		String userName = null;
		String passWord = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			rememberString = reader.readLine();
			if (rememberString.equals("true")) {
				remember.setSelected(true);
				userName=reader.readLine();
				passWord=reader.readLine();
				username.setText(userName);
				password.setText(passWord);
			} else {
				remember.setSelected(false);
			}
			autologinString = reader.readLine();
			if (autologinString.equals("true")) {
				autologin.setSelected(true);
			} else {
				autologin.setSelected(false);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		File avatarFile = new File(DefaultDefine.iconPath + userName + ".jpg");
		if (avatarFile.exists()) {
			System.out.println("username icon exists");
			nickname.setText(userName);
			avatar.setImage(new Image("file:\\" + DefaultDefine.iconPath + userName + ".jpg"));
			avatar.setFitHeight(150);
			avatar.setFitWidth(150);
		} else {
			System.out.println("username icon not exists");
			nickname.setText("");
			avatar.setImage(new Image("file:\\" + DefaultDefine.iconPath + "default.jpg"));
			avatar.setFitHeight(150);
			avatar.setFitWidth(150);
		}
	}

	private boolean user_login(boolean remember, boolean autologin, String username, String password) {
		//保存复选框数据
		File file = new File("D:\\Buffer\\Chattincrypted", "userdata");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream in = new FileOutputStream(file);
			String data = new String("");
			if (remember) {
				data += "true\n";
				data += username;
				data += "\n";
				data += password;
				data += "\n";
			} else {
				data += "false\n";
			}
			if (autologin) {
				data += "true\n";
			} else {
				data += "false\n";
			}

			try {
				in.write(data.getBytes(), 0, data.getBytes().length);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		password = MD5.getMD5(password+"yR7kF9b4B48z67XPV3");

		Main.messageManner.clear();
		Main.messageManner.setAction("user-login");
		Main.messageManner.setUsername(username);
		Main.messageManner.setPassword(password);
		Main.messageManner.sendMessage();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(Main.messageManner.description);

		return Main.messageManner.status();
	}

}
