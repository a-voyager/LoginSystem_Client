package com.voyager.engin;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户封装对象
 * 
 * @author wuhaojie
 *
 */
public class UserBean {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String userPwd;

	public UserBean(String userName, String userPwd) {
		super();
		this.userName = userName;
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/**
	 * 获取用户所有个人信息
	 * 
	 * @return Map<key, value>
	 */
	public Map<String, String> getInfoList() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user", userName);
		map.put("pwd", userPwd);
		return map;

	}

}