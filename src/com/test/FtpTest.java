package com.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpTest {
	public static void main(String[] args) {
		FTPClient client = new FTPClient();
		try {
			client.connect("112.175.85.159", 21);
			if (!client.login(getId(), getPassword())) {          // id와 password을 문자열로 작성, 예: client.login("test", "1234")
				System.out.println("login fail.");          
				return;        
			}else {
				System.out.println("login success");
			}
			
			client.enterLocalPassiveMode();
			
			// 파일업로드
			File newFile = new File("C:/test/newFile.txt");
			newFile.createNewFile();
			FileInputStream fis = new FileInputStream(newFile);
			client.storeFile("./" + newFile.getName(), fis);
			
			// 파일다운로드
			File downFile = new File("C:/test/index.html");
			FileOutputStream fos = new FileOutputStream(downFile);
			client.retrieveFile("./" + downFile.getName(), fos);
			
			// 파일목록출력
			for (FTPFile file : client.listFiles()) {
				System.out.println(file.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.logout();
				if (client.isConnected()) {
					client.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	
	
	
	
	
	private static String getPassword() {
		return "Mnwise4210!@";
	}

	private static String getId() {
		return "mnwiseces";
	}
}
