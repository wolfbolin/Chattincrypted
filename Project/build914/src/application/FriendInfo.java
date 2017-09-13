package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FriendInfo {
	private String username;
	private String nickname;
	private String public_key;
	private String mail;
	private ImageView avatar;
	private String signature;
	private String avatar_data;
	private String password;

	public HBox toHBox() {
		HBox hbox = new HBox(10);
		VBox vbox = new VBox(4);
		HBox namePane = new HBox();
		Text txtName;
		Text txtRemark;
		Text txtSign = new Text();

		txtName = new Text("(" + username + ")");
		txtRemark = new Text(nickname);
		if (signature.length() > 15) {
			txtSign.setText(signature.substring(0, 15) + "...");
		} else {
			txtSign.setText(signature);
		}

		avatar = new ImageView("file:\\" + avatar_data);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAvatar_data() {
		return avatar_data;
	}

	public void setAvatar_data(String avatar_data) {
		this.avatar_data = avatar_data;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
