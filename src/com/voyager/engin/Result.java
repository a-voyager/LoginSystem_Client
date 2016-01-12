package com.voyager.engin;

/**
 * HTTP返回结果封装类
 * 
 * @author wuhaojie
 *
 */
public class Result {
	/**
	 * 返回码 "1"代表成功 否则失败
	 */
	private String responseCode;
	/**
	 * 用户对象 包含用户的个人信息
	 */
	private UserBean user;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Result [responseCode=" + responseCode + ", user=" + user + "]";
	}

}
