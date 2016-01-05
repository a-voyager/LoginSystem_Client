package com.voyager.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import com.voyager.utils.UIs;
import com.voyager.utils.Utils;

import java.awt.Font;

public class Login {

	private JFrame frame;
	private JPasswordField et_userPwd;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UIs.setUI();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea et_userName = new JTextArea();
		et_userName.setToolTipText("");
		et_userName.setBounds(10, 25, 202, 48);
		frame.getContentPane().add(et_userName);

		et_userPwd = new JPasswordField();
		et_userPwd.setBounds(10, 116, 202, 59);
		frame.getContentPane().add(et_userPwd);

		JButton btn_login = new JButton("\u767B\u5F55");
		btn_login.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getText(et_userName);
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
	}

	/**
	 * 获取文本框文字
	 * 
	 * @param et_userName
	 */
	private void getText(JTextArea et_userName) {
		String userName = et_userName.getText();
		char[] password = et_userPwd.getPassword();
		String userPwd = new String(password);
		if (!(Utils.isUserNameQualifiedRule(userName) && Utils
				.isUserPwdQualifiedRule(userPwd))) {
			et_userPwd.setText("");
			JOptionPane.showMessageDialog(frame, "您输入的用户名或密码不合法！请重试", "提示",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
