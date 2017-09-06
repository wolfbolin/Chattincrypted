package com.test;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * ����TCPЭ���Socketͨ�ţ�ʵ���û�����
 * ��������
 */
public class Server {
	public static void main(String[] args) {
		
		try {
			//1.����һ����������Socket����ServerSocket��ָ���󶨵Ķ˿ڣ����Ҽ����˶˿�
			ServerSocket serverSocket = new ServerSocket(8888);
			
			//2.����serversocket��accept����������ʼ�������ȴ��ͻ��˵�����
			System.out.println("****�����������������ȴ��ͻ��˵�����*****");
			Socket socket1 = serverSocket.accept();
			
			//3.��ȡ��������������ȡ�ͻ��˷��͵���Ϣ
			InputStream is = socket1.getInputStream();//�ֽ�����
			InputStreamReader isr = new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���
			BufferedReader br = new BufferedReader(isr);//Ϊ��������ӻ���
			String info = null;
			while((info = br.readLine())!=null) {
				System.out.println("���Ƿ��������ͻ���˵��"+info);
			}
			socket1.shutdownInput(); //�ر������
			
			//4.��ȡ���������Ӧ�ͻ��˵�����
			OutputStream os = socket1.getOutputStream();
			PrintWriter pw = new PrintWriter(os);//��װΪ��ӡ��
			pw.write("��ӭ����");
			pw.flush();//����flush�����������������
			
			//5.�ر���Դ
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket1.close();
			serverSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
