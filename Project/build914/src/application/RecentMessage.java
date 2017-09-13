package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RecentMessage {
	class Message implements Comparable<Message> {
		public long msgDate;
		public String msgContext;
		public String msgSender;
		public String msgReceicer;

		Message(long date, String context, String sender, String receiver) {
			msgDate = date;
			msgContext = context;
			msgSender = sender;
			msgReceicer = receiver;
		}

		public int compareTo(Message arg0) {

			if (this.msgDate == arg0.msgDate) {
				return 0;
			} else if (this.msgDate > arg0.msgDate) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public List<Message> msgList = null;

	RecentMessage() {
		msgList = new ArrayList<Message>();
	}

	public void sort() {
		Collections.sort(msgList);
	}

	public void addMessage(long date, String context, String sender, String receiver) {
		Message msg = new Message(date, context, sender, receiver);
		msgList.add(msg);
	}

	public HBox getHBox(String username, String nickname) {
		String msg = new String();
		boolean hasMsg = false;
		for (int i = msgList.size() - 1; i >= 0; i--) {
			if (msgList.get(i).msgSender.equals(username) || msgList.get(i).msgReceicer.equals(username)) {
				msg = msgList.get(i).msgContext;
				hasMsg = true;
				break;
			}
		}
		if (!hasMsg) {
			return null;
		}

		HBox hbox = new HBox(10);
		VBox vbox = new VBox(4);
		HBox namePane = new HBox();
		Text txtName;
		Text txtRemark;
		Text txtSign = new Text();
		ImageView avatar;

		txtName = new Text("(" + username + ")");
		txtRemark = new Text(nickname);
		if (msg.length() > 15) {
			txtSign.setText(msg.substring(0, 18) + "...");
		} else {
			txtSign.setText(msg);
		}

		File avatarIcon = new File(DefaultDefine.iconPath + username + ".jpg");
		avatar = new ImageView();
		if (avatarIcon.exists()) {
			avatar = new ImageView("file:\\" + DefaultDefine.iconPath + username + ".jpg");
		} else {
			avatar = new ImageView("file:\\" + DefaultDefine.iconPath + "default.jpg");
		}
		avatar.setFitHeight(42);
		avatar.setFitWidth(42);

		txtName.setFont(Font.font("Microsoft YaHei", 14));
		txtRemark.setFont(Font.font("Microsoft YaHei", 14));
		txtSign.setFont(Font.font("Microsoft YaHei", 12));
		txtSign.setFill(Color.GRAY);
		txtName.setFill(Color.GRAY);

		namePane.getChildren().addAll(txtRemark, txtName);
		vbox.getChildren().addAll(namePane, txtSign);
		hbox.getChildren().addAll(avatar, vbox);
		hbox.setPadding(new Insets(3, 5, 3, 5));
		vbox.setAlignment(Pos.CENTER_LEFT);
		return hbox;
	}

}
