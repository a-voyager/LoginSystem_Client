package com.voyager.engin;

/**
 * HTTP���ؽ����װ��
 * 
 * @author wuhaojie
 *
 */
public class Result {
	/**
	 * ������ "1"����ɹ� ����ʧ��
	 */
	private String responseCode;
	/**
	 * �û����� �����û��ĸ�����Ϣ
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
