package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InfoWindow {
	public static Stage windowStage;
	public static String userName;
	public static String nickName;
	public static String Mail;
	public static String signInfo;
	public static String avatar_date;
	public static boolean editable;
	public static boolean addable;

	@FXML
	private ImageView minimize;

	@FXML
	private Button add;

	@FXML
	private TextArea sgininfo;

	@FXML
	private Button sure;

	@FXML
	private TextField mail;

	@FXML
	private TextField nickname;

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
	private Label passwordLabel;

	@FXML
	private PasswordField password;

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
		InfoWindow.windowStage.setIconified(true);
	}

	@FXML
	void shutdown_onMousePressed(MouseEvent event) {
		InfoWindow.windowStage.close();
	}
	///////////////////////////////////////////////////////

	@FXML
	void avatar_onMouseClicked(MouseEvent event) {
		String[] fileInfo = Main.selectFile(true,".jpg","JPG文件(*.jpg)");
		System.out.println(fileInfo[0]);
		if(fileInfo[0]!=null) {
			avatar.setImage(new Image("file:\\"+fileInfo[0]));
			avatar_date=fileInfo[0];
		}
	}

	@FXML
	void sure_onMouseClicked(MouseEvent event) {
		if (editable) {
			String passWord = password.getText();
			passWord = MD5.getMD5(passWord);
			Main.messageManner.clear();
			Main.messageManner.setAction("update-personal-info");
			Main.messageManner.setNew_nickname(nickname.getText());
			Main.messageManner.setNew_passwd(passWord);
			Main.messageManner.setNew_signature(sgininfo.getText());
			Main.messageManner.setNew_avatar("true");
			Main.messageManner.setData(avatar_date);
			Main.messageManner.sendMessage();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Main.messageManner.status()) {
				MessageWindow messageWindow = new MessageWindow();
				messageWindow.messageContext = new String("信息修改成功");
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
			} else {
				MessageWindow messageWindow = new MessageWindow();
				messageWindow.messageContext = Main.messageManner.description();
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
			}
			Main.updateUserInfo();
		}
		InfoWindow.windowStage.close();
	}

	@FXML
	void add_onMouseClicked(MouseEvent event) {
		Main.messageManner.clear();
		Main.messageManner.setAction("add-contact");
		Main.messageManner.setUsername(username.getText());
		Main.messageManner.sendMessage();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Main.messageManner.status()) {
			MessageWindow messageWindow = new MessageWindow();
			messageWindow.messageContext = new String("用户添加成功");
			Stage messageStage = messageWindow.loadWindow();
			messageStage.show();
		} else {
			MessageWindow messageWindow = new MessageWindow();
			messageWindow.messageContext = Main.messageManner.description();
			Stage messageStage = messageWindow.loadWindow();
			messageStage.show();
		}
	}

	public Stage loadWindow() {
		try {
			Parent windowParent = FXMLLoader.load(getClass().getResource("/application/InfoLayout.fxml"));

			rebuildPane(windowParent);

			Scene windowScene = new Scene(windowParent);
			windowScene.setFill(null);
			windowStage = new Stage();
			windowStage.setTitle("个人信息");
			windowStage.initStyle(StageStyle.TRANSPARENT);
			windowStage.setScene(windowScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return windowStage;
	}

	private void rebuildPane(Parent windowParent) {
		TextField username = (TextField) windowParent.lookup("#username");
		TextField nickname = (TextField) windowParent.lookup("#nickname");
		TextField mail = (TextField) windowParent.lookup("#mail");
		TextArea sgininfo = (TextArea) windowParent.lookup("#sgininfo");
		PasswordField password = (PasswordField) windowParent.lookup("#password");
		Label passwordLabel = (Label) windowParent.lookup("#passwordLabel");
		ImageView avatar = (ImageView)windowParent.lookup("#avatar");
		Button add = (Button) windowParent.lookup("#add");
		Button sure = (Button) windowParent.lookup("#sure");
		username.setText(userName);
		nickname.setText(nickName);
		mail.setText(Mail);
		sgininfo.setText(signInfo);
		password.setText(Main.user.getPassword());
		if(editable) {
			sure.setText("修改");
		}
		
		if(avatar_date!=null) {
			avatar.setImage(new Image("file:\\"+avatar_date));
		}else {
			avatar.setImage(new Image("file:\\"+DefaultDefine.iconPath+"default.jpg"));
		}
		
		password.setVisible(editable);
		passwordLabel.setVisible(editable);
		username.setDisable(!editable);
		nickname.setDisable(!editable);
		password.setDisable(!editable);
		passwordLabel.setDisable(!editable);
		mail.setDisable(!editable);
		sgininfo.setDisable(!editable);
		add.setVisible(addable);
	}

}
