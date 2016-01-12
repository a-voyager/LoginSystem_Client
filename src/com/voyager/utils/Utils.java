package com.voyager.utils;

/**
 * 工具类
 * 
 * @author wuhaojie
 *
 */
public class Utils {
	/**
	 * 判断用户名是否合法
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isUserNameQualifiedRule(String name) {
		if (name.trim().isEmpty())
			return false;
		return true;
	}

	/**
	 * 判断密码是否合法
	 * 
	 * @param pwd
	 * @return
	 */
	public static boolean isUserPwdQualifiedRule(String pwd) {
		if (pwd.trim().isEmpty())
			return false;
		return true;
	}
}
