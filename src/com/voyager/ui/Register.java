package com.voyager.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.JButton;

import com.voyager.dao.UserDao;
import com.voyager.engin.UserBean;
import com.voyager.utils.UIs;
import com.voyager.utils.Utils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.SystemColor;

public class Register {

	private static final String COLOR = "#D6D9DF";
	private JFrame frame;
	private JPasswordField et_userPwd_first;
	private JPasswordField et_userPwd_second;
	private String userName;
	private String userPwd;
	private String userPwd_first;
	private String userPwd_second;
	private JEditorPane et_userName;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UIs.setUI();
		frame = new JFrame();
		frame.setTitle("\u6CE8\u518C");
		frame.setBounds(100, 100, 311, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		et_userPwd_first = new JPasswordField();
		et_userPwd_first.setBounds(109, 130, 122, 24);
		frame.getContentPane().add(et_userPwd_first);

		et_userName = new JEditorPane();
		et_userName.setBounds(109, 80, 122, 21);
		frame.getContentPane().add(et_userName);

		et_userPwd_second = new JPasswordField();
		et_userPwd_second.setBounds(109, 181, 122, 24);
		frame.getContentPane().add(et_userPwd_second);

		JButton button = new JButton("\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!getText())
					return;
				System.out.println("注册" + userName + userPwd);
				UserBean user = new UserBean(userName, userPwd);
				boolean register = UserDao.register(user);
				if (register) {
					JOptionPane.showMessageDialog(frame, "注册成功", "提示",
							JOptionPane.WARNING_MESSAGE);
					frame.dispose();
					Login.main(null);
				} else {
					JOptionPane.showMessageDialog(frame, "网络错误！注册失败", "提示",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button.setBounds(109, 247, 93, 23);
		frame.getContentPane().add(button);

		JTextField textField = new JTextField();
		textField.setBackground(Color.decode(COLOR));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField.setBorder(null);
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setEditable(false);
		textField.setText("\u7528\u6237\u540D");
		textField.setBounds(30, 80, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("\u5BC6\u7801");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_1.setFocusable(false);
		textField_1.setFocusTraversalKeysEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(214, 217, 223));
		textField_1.setBounds(30, 133, 66, 21);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("\u786E\u8BA4\u5BC6\u7801");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_2.setFocusable(false);
		textField_2.setFocusTraversalKeysEnabled(false);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBackground(new Color(214, 217, 223));
		textField_2.setBounds(30, 184, 66, 21);
		frame.getContentPane().add(textField_2);
	}

	/**
	 * 获取文本框文字
	 * 
	 * @param et_userName
	 */
	private boolean getText() {
		userName = et_userName.getText();
		char[] password_first = et_userPwd_first.getPassword();
		userPwd_first = new String(password_first);
		char[] password_second = et_userPwd_second.getPassword();
		userPwd_second = new String(password_second);

		if (!userPwd_first.equals(userPwd_second)) {
			JOptionPane.showMessageDialog(frame, "两次密码输入不一致！", "提示",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		userPwd = userPwd_first;

		if (!(Utils.isUserNameQualifiedRule(userName) && Utils
				.isUserPwdQualifiedRule(userPwd))) {
			et_userPwd_first.setText("");
			et_userPwd_second.setText("");
			JOptionPane.showMessageDialog(frame, "您输入的用户名或密码不合法！请重试", "提示",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;

	}
}
