package com.voyager.utils;

public class Utils {
	/**
	 * �ж��û����Ƿ�Ϸ�
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
	 * �ж������Ƿ�Ϸ�
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
