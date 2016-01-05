package com.voyager.dao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.voyager.constant.Constant;
import com.voyager.engin.Result;
import com.voyager.engin.UserBean;
import com.voyager.net.NetHelper;
import com.voyager.ui.UserCenter;

public class UserDao {

	public static void login(JFrame frame, String userName, String userPwd) {
		UserBean user = new UserBean(userName, userPwd);
		Result result = new NetHelper().post(user);
		if (result.getResponseCode().equals(Constant.RESPONSE_OK)) {
			System.out.println("UserDao£º£ºµÇÂ¼³É¹¦£¡");
			frame.dispose();
			UserCenter.main(null);
		} else {
			System.out.println("UserDao£º£ºµÇÂ¼Ê§°Ü£¡");
			JOptionPane.showMessageDialog(frame, "¶Ô²»Æð£¬µÇÂ¼Ê§°Ü£¡", "ÌáÊ¾",
					JOptionPane.WARNING_MESSAGE);
		}

	}

}
