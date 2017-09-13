package application;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

public class MessageManager {
	private SocketThread socketThread;
	private static Socket socket = null;

	public static int messageNum;
	public static String action;// 动作
	public static String mail_address;// 邮件地址
	public static String username;// 用户名（5-20字符）
	public static String nickname;//
	public static String password;// 密码（salt='yR7kF9b4B48z67XPV3'）
	public static String public_key;// OpenPGP public_key
	public static String new_nickname;//
	public static String new_passwd;// 新的密码
	public static String new_signature;// 新的个性签名
	public static String new_avatar;// 新的头像
	public static String data;// 数据
	public static String type;// image/fileoice/text
	public static String message;// 已加密信息
	public static String receiver;// 接收方用户名
	public static String time;// 发送时的时间戳
	public static String filename;// 文件名（如纯文字则为空）
	public static String get;// contacts
	public static String status;// 'success'、'failed'
	public static String description;// 具体说明

	public static boolean avatar;// 是否有头像
	public static String signature;// 个性签名
	public static String mail;// 邮箱地址
	public static String message_id;
	public static String messagetype;
	public static String sender;
	public static String contacts;// 联系人列表

	public static byte[] file_data;

	public void startThread() {
		try {
			socket = new Socket(DefaultDefine.socketIP, DefaultDefine.socketPart);
			socketThread = new SocketThread(socket);
			socketThread.start();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			socket = null;
			System.out.println("连接异常");
		}
	}
	
	public static boolean getNetworkStatus() {
		if(socket == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void stopThread() {
		socketThread.exit = false;
	}

	public void clear() {
		action = null;
		mail_address = null;
		username = null;
		nickname = null;
		password = null;
		public_key = null;
		new_nickname = null;
		new_passwd = null;
		new_signature = null;
		new_avatar = null;
		data = null;
		type = null;
		message = null;
		receiver = null;
		time = null;
		filename = null;
		get = null;
		status = null;
		description = null;

		messageNum = 0;
	}

	public boolean status() {
		if (status == null || status.equals("failed")) {
			return false;
		} else {
			return true;
		}
	}
	
	public String description() {
		return description;
	}

	public void setAction(String msgAction) {
		action = msgAction;
		messageNum++;
	}

	public void setMail_address(String msgMail_address) {
		mail_address = msgMail_address;
		messageNum++;
	}

	public void setUsername(String msgUsername) {
		username = msgUsername;
		messageNum++;
	}

	public void setNickname(String msgNickname) {
		nickname = msgNickname;
		messageNum++;
	}

	public void setPassword(String msgPassword) {
		password = msgPassword;
		messageNum++;
	}

	public void setFingerprint(String msgFingerprint) {
		public_key = msgFingerprint;
		messageNum++;
	}

	public void setNew_nickname(String msgNew_nickname) {
		new_nickname = msgNew_nickname;
		messageNum++;
	}

	public void setNew_passwd(String msgNew_passwd) {
		new_passwd = msgNew_passwd;
		messageNum++;
	}

	public void setNew_signature(String msgNew_signature) {
		new_signature = msgNew_signature;
		messageNum++;
	}

	public void setNew_avatar(String msgNew_avatar) {
		new_avatar = msgNew_avatar;
		messageNum++;
	}

	public void setData(String msgData) {
		data = msgData;
		messageNum++;
	}

	public void setType(String msgType) {
		type = msgType;
		messageNum++;
	}

	public void setMessage(String msgMessage) {
		message = msgMessage;
		messageNum++;
	}

	public void setReceiver(String msgReceiver) {
		receiver = msgReceiver;
		messageNum++;
	}

	public void setTime(String msgTime) {
		time = msgTime;
		messageNum++;
	}

	public void setFilename(String msgFilename) {
		filename = msgFilename;
		messageNum++;
	}

	public void setGet(String msgGet) {
		get = msgGet;
		messageNum++;
	}

	public void setStatus(String msgStatus) {
		status = msgStatus;
		messageNum++;
	}

	public void setDescription(String msgDescription) {
		description = msgDescription;
		messageNum++;
	}

	public void sendMessage() {
		System.out.println("-------message begin-------");
		MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
		try {
			packer.packMapHeader(messageNum);
			if (action != null) {
				System.out.println("Client send action "+action);
				packer.packString("action");
				packer.packString(action);
			}
			if (mail_address != null) {
				packer.packString("mail-address");
				packer.packString(mail_address);
			}
			if (username != null) {
				packer.packString("username");
				packer.packString(username);
			}
			if (nickname != null) {
				packer.packString("nickname");
				packer.packString(nickname);
			}
			if (password != null) {
				packer.packString("password");
				packer.packString(password);
			}
			if (public_key != null) {
				packer.packString("public_key");
				packer.packString(public_key);
			}
			if (new_nickname != null) {
				packer.packString("new-nickname");
				packer.packString(new_nickname);
			}
			if (new_passwd != null) {
				packer.packString("new-passwd");
				packer.packString(new_passwd);
			}
			if (new_signature != null) {
				packer.packString("new-signature");
				packer.packString(new_signature);
			}
			if (new_avatar != null) {
				packer.packString("new-avatar");
				packer.packString(new_avatar);
			}
			if (data != null) {
				packer.packString("data");
				byte [] fileDate = getFileBytes(data);
				packer.packBinaryHeader(fileDate.length);
				packer.writePayload(fileDate);
			}
			if (type != null) {
				packer.packString("type");
				packer.packString(type);
			}
			if (message != null) {
				packer.packString("message");
				packer.packString(message);
			}
			if (receiver != null) {
				packer.packString("receiver");
				packer.packString(receiver);
			}
			if (time != null) {
				packer.packString("time");
				packer.packString(time);
			}
			if (filename != null) {
				packer.packString("filename");
				packer.packString(filename);
			}
			if (get != null) {
				packer.packString("get");
				packer.packString(get);
			}
			if (status != null) {
				packer.packString("status");
				packer.packString(status);
			}
			if (description != null) {
				packer.packString("description");
				packer.packString(description);
			}

			packer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] context = packer.toByteArray();
		byte[] length = getBytes(context.length);
		byte[] msg = unitByteArray(length, context);
		
		sendMessage(socket, msg);
		System.out.println("-------message end-------");
	}

	public void receiveMessage(byte[] msg) {
		byte[] len = new byte[4];
		System.arraycopy(msg, 0, len, 0, 4);
		int contextLenth = getInt(len);

		byte[] context = new byte[contextLenth];
		System.arraycopy(msg, 4, context, 0, contextLenth);

		MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(context);
		int pairNumber = 0;
		String bufferString;
		try {
			pairNumber = unpacker.unpackMapHeader();
			bufferString = unpacker.unpackString();
			System.out.println("receive message type " + bufferString);
			if (bufferString.equals("status")) {
				status = unpacker.unpackString();
				bufferString = unpacker.unpackString();
				description = unpacker.unpackString();
				System.out.println("receive description " + description);
			} else if (bufferString.equals("action")) {
				bufferString = unpacker.unpackString();
				System.out.println("receive action " + bufferString);
				if (bufferString.equals("get-user-info")) {
					status = "success";
					bufferString = unpacker.unpackString();
					username = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					nickname = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					public_key = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					signature = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					mail = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					avatar = unpacker.unpackBoolean();
					if (avatar == true) {
						bufferString = unpacker.unpackString();
						System.out.println("have avatar");
						file_data = unpacker.readPayload(unpacker.unpackBinaryHeader());
						toFile(file_data, DefaultDefine.iconPath, username + ".jpg");
					}

				} else if (bufferString.equals("get-my-contacts")) {
					bufferString = unpacker.unpackString();
					contacts = unpacker.unpackString();
				} else if (bufferString.equals("receive-message")) {
					System.out.println("new message");
					bufferString = unpacker.unpackString();
					message_id = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					type = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					sender = unpacker.unpackString();
					bufferString = unpacker.unpackString();
					time = unpacker.unpackString();
					if(type.equals("text")) {
						bufferString = unpacker.unpackString();
						message = unpacker.unpackString();
						Main.rcentMessage.addMessage(Long.valueOf(time), message, sender, Main.user.getUsername());
					}else if(type.equals("file")) {
						bufferString = unpacker.unpackString();
						filename = unpacker.unpackString();
						bufferString = unpacker.unpackString();
						file_data = unpacker.readPayload(unpacker.unpackBinaryHeader());
						toFile(file_data, DefaultDefine.FilePath, filename);
						
						Main.rcentMessage.addMessage(Long.valueOf(time), "您已收到文件\""+filename+"\"并缓存于："+DefaultDefine.FilePath, sender, Main.user.getUsername());
					}
					MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();
					packer.packMapHeader(2);
					packer.packString("action");
					packer.packString("message-received");
					packer.packString("message_id");
					packer.packString(message_id);
					packer.close();
					byte[] reply = packer.toByteArray();
					byte[] length = getBytes(reply.length);
					byte[] rmsg = unitByteArray(length, reply);
					sendMessage(socket, rmsg);
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static int getInt(byte[] bytes) {
		return (0xff & bytes[3]) | (0xff00 & (bytes[2] << 8)) | (0xff0000 & (bytes[1] << 16))
				| (0xff000000 & (bytes[0] << 24));
	}
	
	private static byte[] getFileBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);
            int length = (int) file.length();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(length);  
            byte[] b = new byte[length];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }


	public static byte[] getBytes(int data) {
		byte[] bytes = new byte[4];
		bytes[3] = (byte) (data & 0xff);
		bytes[2] = (byte) ((data & 0xff00) >> 8);
		bytes[1] = (byte) ((data & 0xff0000) >> 16);
		bytes[0] = (byte) ((data & 0xff000000) >> 24);
		return bytes;
	}

	public static byte[] unitByteArray(byte[] byte1, byte[] byte2) {
		byte[] unitByte = new byte[byte1.length + byte2.length];
		System.arraycopy(byte1, 0, unitByte, 0, byte1.length);
		System.arraycopy(byte2, 0, unitByte, byte1.length, byte2.length);
		return unitByte;
	}

	public static void toFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void sendMessage(Socket link, byte[] message) {
		OutputStream outputStream;
		try {
			outputStream = link.getOutputStream();
			outputStream.write(message);
			System.out.println("Client send message complete.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Client send message Error.");
			e.printStackTrace();
		}

	}

	class SocketThread extends Thread {
		boolean exit = true;
		Socket link;

		SocketThread(Socket socket) {
			link = socket;
		}

		@Override
		public void run() {
			InputStream inputStream;
			try {
				inputStream = link.getInputStream();
				while (exit) {
					byte[] msg = new byte[83886080];
					inputStream.read(msg);
					System.out.println("-------receive begin-------");
					receiveMessage(msg);
					System.out.println("-------receive end-------");
				}
				link.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
