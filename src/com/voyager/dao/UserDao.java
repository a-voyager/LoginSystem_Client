package com.voyager.dao;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.voyager.constant.Constant;
import com.voyager.engin.Result;
import com.voyager.engin.UserBean;
import com.voyager.net.NetHelper;
import com.voyager.ui.UserCenter;

/**
 * �û������� ʵ�ֵ�¼��ע�ᡢע������ ���������
 * 
 * @author wuhaojie
 *
 */
public class UserDao {

	/**
	 * ��¼
	 * 
	 * @param frame
	 *            ����
	 * @param userName
	 *            �û���
	 * @param userPwd
	 *            �û�����
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean login(JFrame frame, String userName, String userPwd) {
		UserBean user = new UserBean(userName, userPwd);
		Result result = new NetHelper().post(user, "l");
		if (result.getResponseCode().equals(Constant.RESPONSE_OK)) {
			System.out.println("UserDao������¼�ɹ���");
			frame.dispose();
			UserCenter.main(new String[] { userName, userPwd });
			return true;
		} else {
			System.out.println("UserDao������¼ʧ�ܣ�");
			JOptionPane.showMessageDialog(frame, "�Բ��𣬵�¼ʧ�ܣ�", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

	}

	/**
	 * ע��
	 * 
	 * @param user
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean register(UserBean user) {
		Result result = new NetHelper().post(user, "r");
		if ("1".equals(result.getResponseCode())) {
			return true;
		}
		return false;
	}

	/**
	 * ע��
	 * 
	 * @return �����Ƿ�ɹ�
	 */
	public static boolean logout() {
		// ע����δʵ���жϷ�����
		new NetHelper().post(null, "o");
		return true;
	}

}
