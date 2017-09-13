package application;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow {
	public static Stage windowStage;
	public String userID;
	private static String chatuser;
	SimpleDateFormat dateFormat = new SimpleDateFormat("  yyyy/MM/dd HH:mm:ss\n");

	@FXML
	private ImageView minimize;

	@FXML
	private ImageView sendPic;

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
	private ImageView sendFile;

	@FXML
	private TextField search;

	@FXML
	private ImageView deletefriend;

	@FXML
	private TextArea messageArea;

	@FXML
	private ImageView friendinfo;

	@FXML
	private ScrollPane friendList;

	@FXML
	private ImageView sendVoice;

	@FXML
	private ImageView minimizeShow;

	@FXML
	private ImageView shutdown;

	@FXML
	private ImageView friendShowicon;

	@FXML
	private ImageView messageicon;
	
	@FXML
    private ImageView ban;

	@FXML
	private Label friendname;

	//////////////////////////////////////////////////
	@FXML
	void minimize_onMousePressed(MouseEvent event) {
		System.out.println("minimize");
		MainWindow.windowStage.setIconified(true);
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
		MainWindow.windowStage.close();
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
	//////////////////////////////////////////////////

	@FXML
	void avatar_onMousePressed(MouseEvent event) {
		InfoWindow infoWindow = new InfoWindow();
		infoWindow.userName = Main.user.getUsername();
		infoWindow.nickName = Main.user.getNickname();
		infoWindow.Mail = Main.user.getMail();
		infoWindow.signInfo = Main.user.getSignature();
		infoWindow.avatar_date = Main.user.getAvatar_data();
		infoWindow.editable = true;
		infoWindow.addable = false;
		Stage messageStage = infoWindow.loadWindow();
		messageStage.show();
	}

	@FXML
	void friend_onMousePressed(MouseEvent event) {
		System.out.println("friend show");
		friendicon.setVisible(false);
		messageicon.setVisible(true);
		/////////////////////////////////////////
		getFriendList(false);
		reAddFriend();
	}

	@FXML
	void friendShow_onMousePressed(MouseEvent event) {
		getFriendList(true);
		reAddFriend();
	}

	@FXML
	void message_onMousePressed(MouseEvent event) {
		System.out.println("message show");
		messageicon.setVisible(false);
		friendicon.setVisible(true);
		/////////////////////////////////////////
		reAddRecenet();
	}

	@FXML
	void messageShow_onMousePressed(MouseEvent event) {
		messageicon.setVisible(true);
		/////////////////////////////////////////
		reAddRecenet();
	}

	@FXML
	void backbutton_onMouseClicked(MouseEvent event) {
		title.setVisible(false);
		messageArea.setVisible(false);
		tools.setVisible(false);
		inputArea.setVisible(false);
	}

	@FXML
	void sendFile_onMouseClicked(MouseEvent event) {
		String[] fileInfo = Main.selectFile(false,"","");
		String time = String.valueOf(new Date().getTime());
		addMessage(Main.user.getUsername(), "您将发送文件 \"" + fileInfo[1] + "\"", time, true);
		Main.messageManner.clear();
		Main.messageManner.setAction("send-message");
		Main.messageManner.setType("file");
		Main.messageManner.setReceiver(chatuser);
		Main.messageManner.setTime(time);
		Main.messageManner.setData(fileInfo[0]);
		Main.messageManner.setFilename(fileInfo[1]);
		Main.messageManner.sendMessage();
		time = String.valueOf(new Date().getTime());
		addMessage(Main.user.getUsername(), "您已发送文件 \"" + fileInfo[1] + "\"", time, true);
		if (Main.messageManner.status()) {
			MessageWindow messageWindow = new MessageWindow();
			messageWindow.messageContext = new String("文件发送失败，请重新尝试");
			Stage messageStage = messageWindow.loadWindow();
			messageStage.show();
		}
	}

	@FXML
	void sendPic_onMouedClicked(MouseEvent event) {

	}

	@FXML
	void sendVoice_onMouesClicked(MouseEvent event) {

	}
	
	@FXML
    void ban_onMouseClicked(MouseEvent event) {
		Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "朋友，三思啊！", new ButtonType("取消", ButtonBar.ButtonData.NO),
				new ButtonType("确定", ButtonBar.ButtonData.YES));
		_alert.setTitle("删除");
		_alert.setHeaderText("您确定要永久屏蔽该好友吗？");
		Optional<ButtonType> _buttonType = _alert.showAndWait();
		if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
			Main.messageManner.clear();
			Main.messageManner.setAction("add-blacklist");
			Main.messageManner.setUsername(chatuser);
			Main.messageManner.sendMessage();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (Main.messageManner.status()) {
				MessageWindow messageWindow = new MessageWindow();
				messageWindow.messageContext = new String("屏蔽好友操作成功");
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
			} else {
				MessageWindow messageWindow = new MessageWindow();
				messageWindow.messageContext = Main.messageManner.description();
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
			}
		} else {
			return;
		}
    }

	@FXML
	void friendinfo_onMousePressed(MouseEvent event) {
		for (int i = 0; i != Main.friendlist.size(); i++) {
			System.out.println(Main.friendlist.get(i).getUsername() + "  " + chatuser);
			if (Main.friendlist.get(i).getUsername().equals(chatuser)) {
				InfoWindow infoWindow = new InfoWindow();
				infoWindow.userName = Main.friendlist.get(i).getUsername();
				infoWindow.nickName = Main.friendlist.get(i).getNickname();
				infoWindow.Mail = Main.friendlist.get(i).getMail();
				infoWindow.signInfo = Main.friendlist.get(i).getSignature();
				infoWindow.avatar_date = Main.friendlist.get(i).getAvatar_data();
				infoWindow.editable = false;
				infoWindow.addable = false;

				Stage messageStage = infoWindow.loadWindow();
				messageStage.show();
			}
		}
	}

	@FXML
	void deletefriend_onMousePressed(MouseEvent event) {
		Alert _alert = new Alert(Alert.AlertType.CONFIRMATION, "朋友，三思啊！", new ButtonType("取消", ButtonBar.ButtonData.NO),
				new ButtonType("确定", ButtonBar.ButtonData.YES));
		_alert.setTitle("删除");
		_alert.setHeaderText("您确定要删除该好友吗？");
		Optional<ButtonType> _buttonType = _alert.showAndWait();
		if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
			Main.messageManner.clear();
			Main.messageManner.setAction("del-contact");
			Main.messageManner.setUsername(chatuser);
			Main.messageManner.sendMessage();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (Main.messageManner.status()) {
				MessageWindow messageWindow = new MessageWindow();
				messageWindow.messageContext = new String("删除好友操作成功");
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
			} else {
				MessageWindow messageWindow = new MessageWindow();
				messageWindow.messageContext = Main.messageManner.description();
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
			}
			// 更新好友列表
			getFriendList(true);
			VBox viewList = new VBox();
			for (int i = 0; i < Main.friendlist.size(); i++) {
				FriendInfo friend = Main.friendlist.get(i);
				System.out.println("add friend " + friend.getNickname());
				HBox node = friend.toHBox();
				node.setOnMouseClicked(e -> {
					openWindow(e, friend.getUsername(), friend.getNickname(), friendname, title, messageArea, tools,
							inputArea);
				});
				viewList.getChildren().add(node);
			}
			friendList.setContent(viewList);
		} else {
			return;
		}
	}

	@FXML
	void inputArea_onKeyReleased(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			System.out.println("user send message(on ENTER)");
			long time = new Date().getTime();
			String message = inputArea.getText();
			Main.rcentMessage.addMessage(time, message, Main.user.getUsername(), chatuser);
			addMessage(Main.user.getUsername(), inputArea.getText(), String.valueOf(time), true);
			reAddRecenet();
			messageicon.setVisible(false);
			friendicon.setVisible(true);
			Main.messageManner.clear();
			Main.messageManner.setAction("send-message");
			Main.messageManner.setType("text");
			Main.messageManner.setTime(String.valueOf(time));
			Main.messageManner.setReceiver(chatuser);
			Main.messageManner.setMessage(message);
			System.out.println("message context " + message);
			Main.messageManner.sendMessage();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!Main.messageManner.status()) {
				MessageWindow messageWindow = new MessageWindow();
				if (Main.messageManner.description().equals("You are blocked by user")) {
					messageWindow.messageContext = "消息发送失败，你已被此用户屏蔽";
				} else {
					messageWindow.messageContext = Main.messageManner.description();
				}
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
			}
		}
	}

	@FXML
	void search_onKeyTyped(KeyEvent event) {
		System.out.println("Text=" + search.getText());
		System.out.println("Event=" + event.getCharacter());

		String wordornum = "[A-Za-z0-9]*";
		String searchword = "";
		if (Pattern.matches(wordornum, event.getCharacter())) {
			searchword += search.getText() + event.getCharacter();
		} else {
			searchword += search.getText();
		}

		if (!Pattern.matches(wordornum, searchword)) {
			search.setText("");
		}

		String searchname = searchword;
		if (!searchword.isEmpty()) {
			searchword = wordornum + searchword + wordornum;
		}

		VBox viewList = new VBox();
		for (int i = 0; i < Main.friendlist.size(); i++) {
			FriendInfo friend = Main.friendlist.get(i);
			if (searchword.isEmpty() || Pattern.matches(searchword, friend.getUsername())
					|| Pattern.matches(searchword, friend.getNickname())) {
				System.out.println("add friend " + friend.getNickname());
				HBox node = friend.toHBox();
				node.setOnMouseClicked(e -> {
					openWindow(e, friend.getUsername(), friend.getNickname(), friendname, title, messageArea, tools,
							inputArea);
				});
				viewList.getChildren().add(node);
			}
		}
		if (!searchname.isEmpty()) {
			String searchName = searchname;
			HBox node = addiconHBox();
			node.setOnMouseClicked(e -> {
				Main.messageManner.clear();
				Main.messageManner.setAction("get-user-info");
				Main.messageManner.setUsername(searchName);
				Main.messageManner.sendMessage();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (Main.messageManner.status()) {
					InfoWindow infoWindow = new InfoWindow();
					infoWindow.userName = Main.messageManner.username;
					infoWindow.nickName = Main.messageManner.nickname;
					infoWindow.Mail = Main.messageManner.mail;
					infoWindow.signInfo = Main.messageManner.signature;
					if(Main.messageManner.avatar) {
						infoWindow.avatar_date = DefaultDefine.iconPath+infoWindow.userName+".jpg";
					}else {
						infoWindow.avatar_date = null;
					}
					infoWindow.editable = false;
					infoWindow.addable = true;

					Stage messageStage = infoWindow.loadWindow();
					messageStage.show();
				} else {
					MessageWindow messageWindow = new MessageWindow();
					messageWindow.messageContext = new String("该用户不存在");
					Stage messageStage = messageWindow.loadWindow();
					messageStage.show();
				}
			});
			viewList.getChildren().add(node);
		}

		friendList.setContent(viewList);
	}

	public HBox addiconHBox() {
		HBox hbox = new HBox(10);
		VBox vbox = new VBox(4);
		Text txtName;

		txtName = new Text("添加新的好友");

		avatar = new ImageView(getClass().getResource("../images/addicon.png").toString());
		avatar.setFitHeight(42);
		avatar.setFitWidth(42);

		txtName.setFont(Font.font("Microsoft YaHei", 22));
		txtName.setFill(Color.BLACK);

		vbox.getChildren().addAll(txtName);
		hbox.getChildren().addAll(avatar, vbox);
		hbox.setPadding(new Insets(3, 5, 3, 5));
		vbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}

	public void reAddFriend() {
		VBox viewList = new VBox();
		for (int i = 0; i < Main.friendlist.size(); i++) {
			FriendInfo friend = Main.friendlist.get(i);
			System.out.println("add friend " + friend.getNickname());
			HBox node = friend.toHBox();
			node.setOnMouseClicked(e -> {
				openWindow(e, friend.getUsername(), friend.getNickname(), friendname, title, messageArea, tools,
						inputArea);
			});
			viewList.getChildren().add(node);
		}
		friendList.setContent(viewList);
	}

	public void reAddRecenet() {
		Collections.sort(Main.rcentMessage.msgList);
		VBox viewList = new VBox();
		for (int i = 0; i != Main.friendlist.size(); i++) {
			String username = Main.friendlist.get(i).getUsername();
			String nickname = Main.friendlist.get(i).getNickname();
			HBox node = Main.rcentMessage.getHBox(username, nickname);
			if (node == null) {
				continue;
			}
			node.setOnMouseClicked(e -> {
				openWindow(e, username, nickname, friendname, title, messageArea, tools, inputArea);
			});
			viewList.getChildren().add(node);
		}
		friendList.setContent(viewList);
	}

	private void openWindow(MouseEvent e, String username, String nickname, Label friendname, HBox title,
			TextArea messageArea, HBox tools, TextArea inputArea) {
		if (e.getClickCount() >= 1) {
			chatuser = username;
			friendname.setText(nickname);
			title.setVisible(true);
			messageArea.setVisible(true);
			tools.setVisible(true);
			inputArea.setVisible(true);
			messageArea.setText("");
			inputArea.setText("");
			String sender, receiver, context;
			long time;
			for (int i = 0; i != Main.rcentMessage.msgList.size(); i++) {
				sender = Main.rcentMessage.msgList.get(i).msgSender;
				receiver = Main.rcentMessage.msgList.get(i).msgReceicer;
				context = Main.rcentMessage.msgList.get(i).msgContext;
				time = Main.rcentMessage.msgList.get(i).msgDate;
				if (sender.equals(chatuser) && receiver.equals(Main.user.getUsername())) {
					addMessage(sender, context, String.valueOf(time), false);
				}
				if (sender.equals(Main.user.getUsername()) && receiver.equals(chatuser)) {
					addMessage(Main.user.getUsername(), context, String.valueOf(time), false);
				}
			}
		}
	}

	private void addMessage(String sender, String context, String time, boolean reload) {
		String messageTime = dateFormat.format(new Date(Long.valueOf(time)));
		messageArea.setText(messageArea.getText() + sender + "     " + messageTime + "\n    " + context + "\n\n");
		if (reload) {
			inputArea.setText("");
		}
	}

	private void getFriendList(boolean refresh) {
		long nowTime = new Date().getTime();
		if (refresh || nowTime - Main.friendUserDate > 120000) {
			// 获取好友列表
			Main.messageManner.clear();
			Main.messageManner.setAction("get-my-contacts");
			Main.messageManner.setGet("contacts");
			Main.messageManner.sendMessage();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (Main.messageManner.contacts == null) {
				MessageWindow messageWindow = new MessageWindow();
				messageWindow.messageContext = new String("获取联系人列表失败");
				Stage messageStage = messageWindow.loadWindow();
				messageStage.show();
				return;
			}
			// 重新获取每个人的信息
			// 清空联系人信息数据
			Main.friendlist.clear();
			// 分割数据
			System.out.println(Main.messageManner.contacts);
			if (Main.messageManner.contacts.equals("")) {
				Main.friendUser = new String[0];
			} else {
				Main.friendUser = Main.messageManner.contacts.split(",");
			}
			Main.friendUserDate = nowTime;
			// 获取每个人的信息
			for (int i = 0; i != Main.friendUser.length; i++) {
				Main.messageManner.clear();
				Main.messageManner.setAction("get-user-info");
				System.out.println("get user info " + Main.friendUser[i]);
				Main.messageManner.setUsername(Main.friendUser[i]);
				Main.messageManner.sendMessage();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 新建个人对象 将其加入联系人数据
				FriendInfo newFriend = new FriendInfo();
				if (Main.messageManner.avatar == true) {
					newFriend.setAvatar_data(DefaultDefine.iconPath + Main.messageManner.username + ".jpg");
				} else {
					newFriend.setAvatar_data(DefaultDefine.iconPath + "default.jpg");
				}
				newFriend.setNickname(Main.messageManner.nickname);
				newFriend.setSignature(Main.messageManner.signature);
				newFriend.setUsername(Main.messageManner.username);
				Main.friendlist.add(newFriend);
			}
		}
	}

	/////////////////////////////////////////////////////////////
	public Stage loadWindow() {
		try {
			Parent windowParent = FXMLLoader.load(getClass().getResource("/application/MainLayout.fxml"));

			rebuildPane(windowParent);

			Scene windowScene = new Scene(windowParent);
			windowScene.setFill(null);
			windowStage = new Stage();
			windowStage.setTitle("密聊");
			windowStage.initStyle(StageStyle.TRANSPARENT);
			windowStage.setScene(windowScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return windowStage;
	}

	private void rebuildPane(Parent windowParent) {
		ImageView avatar = (ImageView) windowParent.lookup("#avatar");
		ImageView friendicon = (ImageView) windowParent.lookup("#friendicon");
		HBox title = (HBox) windowParent.lookup("#title");
		TextArea messageArea = (TextArea) windowParent.lookup("#messageArea");
		HBox tools = (HBox) windowParent.lookup("#tools");
		TextArea inputArea = (TextArea) windowParent.lookup("#inputArea");
		ScrollPane friendList = (ScrollPane) windowParent.lookup("#friendList");
		Label friendname = (Label) windowParent.lookup("#friendname");

		getFriendList(false);
		VBox viewList = new VBox();
		for (int i = 0; i < Main.friendlist.size(); i++) {
			FriendInfo friend = Main.friendlist.get(i);
			System.out.println("add friend " + friend.getNickname());
			HBox node = friend.toHBox();
			node.setOnMouseClicked(e -> {
				openWindow(e, friend.getUsername(), friend.getNickname(), friendname, title, messageArea, tools,
						inputArea);
			});
			viewList.getChildren().add(node);
		}
		friendList.setContent(viewList);

		Main.updateUserInfo();

		avatar.setImage(new Image("file:\\" + DefaultDefine.iconPath + Main.user.getUsername() + ".jpg"));

		friendicon.setVisible(false);
		title.setVisible(false);
		messageArea.setVisible(false);
		tools.setVisible(false);
		inputArea.setVisible(false);
	}
}
