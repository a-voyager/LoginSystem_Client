package com.voyager.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import com.voyager.dao.UserDao;
import com.voyager.utils.ConfigUtils;
import com.voyager.utils.UIs;
import com.voyager.utils.Utils;

import java.awt.Font;
import java.util.Properties;

import javax.swing.JCheckBox;

public class Login {

	private JFrame frame;
	private JPasswordField et_userPwd;
	private String userName;
	private String userPwd;
	private JCheckBox cb_savePwd;
	private JTextArea et_userName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	private void loadConfig() {
		System.out.println("savePwd : " + ConfigUtils.getConfig("savePwd"));
		et_userName.setText(ConfigUtils.getConfig("userName"));
		if ("true".equals(ConfigUtils.getConfig("savePwd"))) {
			cb_savePwd.setSelected(true);
			et_userPwd.setText(ConfigUtils.getConfig("userPwd"));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UIs.setUI();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		et_userName = new JTextArea();
		et_userName.setToolTipText("");
		et_userName.setBounds(10, 25, 202, 48);
		frame.getContentPane().add(et_userName);

		cb_savePwd = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		cb_savePwd.setBounds(268, 91, 103, 23);
		frame.getContentPane().add(cb_savePwd);

		et_userPwd = new JPasswordField();
		et_userPwd.setBounds(10, 116, 202, 59);
		frame.getContentPane().add(et_userPwd);

		JButton btn_login = new JButton("\u767B\u5F55");
		btn_login.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!getText(et_userName))
					return;
				boolean login = UserDao.login(frame, userName, userPwd);
				System.out
						.println("UserDao.login(frame, userName, userPwd) :: "
								+ login);
				ConfigUtils.addConfig("userName", userName);
				if (cb_savePwd.isSelected() && login) {
					ConfigUtils.addConfig("savePwd", "true");
					ConfigUtils.addConfig("userPwd", userPwd);
				}
			}

		});
		btn_login.setBounds(268, 50, 93, 23);
		frame.getContentPane().add(btn_login);

		JButton btn_register = new JButton("\u6CE8\u518C");
		btn_register.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_register.setBounds(268, 134, 93, 23);
		frame.getContentPane().add(btn_register);

		loadConfig();

	}

	/**
	 * 获取文本框文字
	 * 
	 * @param et_userName
	 */
	private boolean getText(JTextArea et_userName) {
		userName = et_userName.getText();
		char[] password = et_userPwd.getPassword();
		userPwd = new String(password);
		if (!(Utils.isUserNameQualifiedRule(userName) && Utils
				.isUserPwdQualifiedRule(userPwd))) {
			et_userPwd.setText("");
			JOptionPane.showMessageDialog(frame, "您输入的用户名或密码不合法！请重试", "提示",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;

	}
}
