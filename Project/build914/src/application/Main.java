package application;

import java.io.File;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import application.RecentMessage.Message;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	protected static MessageManager messageManner;
	protected static List<FriendInfo> friendlist;
	protected static String[] friendUser;
	protected static long friendUserDate;
	protected static FriendInfo user;
	protected static RecentMessage rcentMessage;

	@Override
	public void start(Stage primaryStage) {
		friendUserDate = 0;
		friendlist = new LinkedList<FriendInfo>();
		rcentMessage = new RecentMessage();

		/*
		 * rcentMessage.addMessage(123456, "hello", "wolf", "abc");
		 * rcentMessage.addMessage(23456, "hello", "wolf", "abc");
		 * rcentMessage.addMessage(223456, "hello", "wolf", "abc");
		 * rcentMessage.addMessage(23456, "hello", "wolf", "abc");
		 * Collections.sort(rcentMessage.msgList); for(int
		 * i=0;i!=rcentMessage.msgList.size();i++) {
		 * System.out.println(rcentMessage.msgList.get(i).msgDate); }
		 */
		messageManner = new MessageManager();
		messageManner.startThread();

		LoginWindow loginwindow = new LoginWindow();
		Stage loginStage = loginwindow.loadWindow();
		loginStage.show();
	}

	public static void updateUserInfo() {
		messageManner.clear();
		messageManner.setAction("get-user-info");
		System.out.println("get user info " + user.getUsername());
		messageManner.setUsername(user.getUsername());
		messageManner.sendMessage();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (messageManner.avatar) {
			user.setAvatar_data(DefaultDefine.iconPath + user.getUsername() + ".jpg");
		} else {
			user.setAvatar_data(DefaultDefine.iconPath + "default.jpg");
		}
		user.setMail(Main.messageManner.mail);
		user.setNickname(Main.messageManner.nickname);
		user.setPublic_key(Main.messageManner.public_key);
		user.setSignature(Main.messageManner.signature);
		user.setUsername(Main.messageManner.username);
	}

	public static String[] selectFile(boolean limit, String format, String formatDescribe) {
		// 创建文件选择器
		JFileChooser fileChooser = new JFileChooser();
		// 设置当前目录
		fileChooser.setCurrentDirectory(new File("C:\\Users\\wolfbolin\\Desktop"));
		if (limit) {
			fileChooser.setAcceptAllFileFilterUsed(false);
			final String[][] fileENames = { { format, formatDescribe } };
			for (final String[] fileEName : fileENames) {
				fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
					public boolean accept(File file) {
						if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {
							return true;
						}
						return false;
					}

					public String getDescription() {
						return fileEName[1];
					}

				});
			}
		} else {
			fileChooser.addChoosableFileFilter(new FileFilter() {
				public boolean accept(File file) {
					return true;
				}

				public String getDescription() {
					return "所有文件(*.*)";
				}
			});
		}
		int returnVal = fileChooser.showOpenDialog(fileChooser);
		String[] fileInfo = new String[2];
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println(fileChooser.getSelectedFile().getName());
			fileInfo[0] = fileChooser.getSelectedFile().getAbsolutePath(); // 这个就是你选择的文件夹的路径
			fileInfo[1] = fileChooser.getSelectedFile().getName();
		}
		return fileInfo;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
