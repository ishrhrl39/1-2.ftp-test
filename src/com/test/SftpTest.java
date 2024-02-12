package com.test;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SftpTest {
	public static void main(String[] args) {
		JSch jsch = new JSch();
        Session session = null;
		try {
			session = jsch.getSession(getId(), "INSERT IP", 22);
			session.setPassword(getPassword());
	        java.util.Properties config = new java.util.Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
	        session.connect();
	        System.out.println("login success");
	        
	        Channel channel = session.openChannel("sftp");
            channel.connect();
	        ChannelSftp channelSftp = (ChannelSftp) channel;
	        
	        // 파일 업로드
	        channelSftp.put("C:/test/newFile.txt", "www/test.txt");
	        
	        // 파일 다운로드
	        byte[] buffer = new byte[1024];
	        BufferedInputStream bis = new BufferedInputStream(channelSftp.get("www/index.html"));
	        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("C:/test/test.html")));
			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			bis.close();
	        
	        session.disconnect();
		} catch (Exception e) {
			e.printStackTrace();	
			System.out.println("login fail");
		}
	}

	private static String getPassword() {
		return "";
	}

	private static String getId() {
		return "";
	}
}
