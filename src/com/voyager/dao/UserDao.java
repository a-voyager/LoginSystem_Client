package com.voyager.dao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.voyager.constant.Constant;
import com.voyager.engin.Result;
import com.voyager.engin.UserBean;
import com.voyager.net.NetHelper;
import com.voyager.ui.UserCenter;

public class UserDao {

	public static boolean login(JFrame frame, String userName, String userPwd) {
		UserBean user = new UserBean(userName, userPwd);
		Result result = new NetHelper().post(user);
		if (result.getResponseCode().equals(Constant.RESPONSE_OK)) {
			System.out.println("UserDao������¼�ɹ���");
			frame.dispose();
			UserCenter.main(null);
			return true;
		} else {
			System.out.println("UserDao������¼ʧ�ܣ�");
			JOptionPane.showMessageDialog(frame, "�Բ��𣬵�¼ʧ�ܣ�", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

	}

}
