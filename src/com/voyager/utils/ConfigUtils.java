package com.voyager.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.voyager.constant.Constant;

public class ConfigUtils {
	private static Properties properties = new Properties();
	private static FileOutputStream outputStream;
	static {
		File file = new File(Constant.CONFIG_FILE_PATH);
		try {
			if (!file.exists())
				file.createNewFile();
			FileInputStream inputStream = new FileInputStream(file);
			properties.load(inputStream);
			outputStream = new FileOutputStream(file);
			properties.store(outputStream, "back up");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getConfig(String key) {
		return properties.getProperty(key);
	}

	public static boolean addConfig(String key, String value) {
		properties.setProperty(key, value);
		try {
			properties.store(outputStream, "user pwd");
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(getConfig("123"));
	}
}
