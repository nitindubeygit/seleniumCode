package com.vTiger.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {

		File src = new File("./Configurations/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			try {
				pro.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in config file is" + e.getMessage());

		}

	}

	public String getBaseURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}

	public String getuserName() {
		String uName = pro.getProperty("userName");
		return uName;
	}

	public String getPasswor() {
		String pwd = pro.getProperty("password");
		return pwd;
	}

	public String getChromePath() {
		String ChromePath = pro.getProperty("ChromePath");
		return ChromePath;
	}

	public String getFirefoxPath() {
		String FirefoxPath = pro.getProperty("FirefoxPath");
		return FirefoxPath;
	}

	public String getIEPath() {
		String IEPath = pro.getProperty("IEPath");
		return IEPath;
	}

}
