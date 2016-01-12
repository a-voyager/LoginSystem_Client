package com.voyager.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.voyager.constant.Constant;

/**
 * 配置文件读取操作类
 * @author wuhaojie
 *
 */
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

	/**
	 * 获取配置信息
	 * @param key
	 * @return value
	 */
	public static String getConfig(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 添加配置信息
	 * @param key
	 * @param value
	 * @return 操作是否成功
	 */
	public static boolean addConfig(String key, String value) {
		properties.setProperty(key, value);
		try {
			properties.store(outputStream, "user pwd");
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	
}
