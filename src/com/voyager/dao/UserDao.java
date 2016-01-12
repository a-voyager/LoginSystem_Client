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
 * 用户操作类 实现登录、注册、注销功能 供界面调用
 * 
 * @author wuhaojie
 *
 */
public class UserDao {

	/**
	 * 登录
	 * 
	 * @param frame
	 *            窗体
	 * @param userName
	 *            用户名
	 * @param userPwd
	 *            用户密码
	 * @return 操作是否成功
	 */
	public static boolean login(JFrame frame, String userName, String userPwd) {
		UserBean user = new UserBean(userName, userPwd);
		Result result = new NetHelper().post(user, "l");
		if (result.getResponseCode().equals(Constant.RESPONSE_OK)) {
			System.out.println("UserDao：：登录成功！");
			frame.dispose();
			UserCenter.main(new String[] { userName, userPwd });
			return true;
		} else {
			System.out.println("UserDao：：登录失败！");
			JOptionPane.showMessageDialog(frame, "对不起，登录失败！", "提示",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

	}

	/**
	 * 注册
	 * 
	 * @param user
	 * @return 操作是否成功
	 */
	public static boolean register(UserBean user) {
		Result result = new NetHelper().post(user, "r");
		if ("1".equals(result.getResponseCode())) {
			return true;
		}
		return false;
	}

	/**
	 * 注销
	 * 
	 * @return 操作是否成功
	 */
	public static boolean logout() {
		// 注销暂未实现判断返回码
		new NetHelper().post(null, "o");
		return true;
	}

}
