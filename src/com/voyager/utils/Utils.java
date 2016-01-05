package com.voyager.utils;

public class Utils {
	/**
	 * 判断用户名是否合法
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isUserNameQualifiedRule(String name) {
		if (name.isEmpty())
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
		if (pwd.isEmpty())
			return false;
		return true;
	}
}
