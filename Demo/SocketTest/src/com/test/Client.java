package com.test;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 客户端
 */
public class Client {
	public static void main(String[] args) {
		try {
			//1.创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost",8888);
			
			//2.获取输出流，用来向服务器端发送服务信息
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("用户名：admin;密码：123"); 
			pw.flush();
			socket.shutdownOutput();
			
			//3.获取输入流，读取服务器端的响应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String info = null;
			while((info = br.readLine())!=null) {
				System.out.println("我是客户端，服务器说："+info);
			}

			
			//4.关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
