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

public class Register {

	private JFrame frame;
	private JPasswordField et_userPwd_first;
	private JPasswordField et_userPwd_second;
	private String userName;
	private String userPwd;
	private String userPwd_first;
	private String userPwd_second;
	private JEditorPane et_userName;

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
		frame.setBounds(100, 100, 450, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		et_userPwd_first = new JPasswordField();
		et_userPwd_first.setBounds(73, 138, 122, 21);
		frame.getContentPane().add(et_userPwd_first);

		et_userName = new JEditorPane();
		et_userName.setBounds(73, 85, 122, 21);
		frame.getContentPane().add(et_userName);

		et_userPwd_second = new JPasswordField();
		et_userPwd_second.setBounds(73, 189, 122, 21);
		frame.getContentPane().add(et_userPwd_second);

		JButton button = new JButton("\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!getText())
					return;
				System.out.println("ע��" + userName + userPwd);
				UserBean user = new UserBean(userName, userPwd);
				boolean register = UserDao.register(user);
				if (register) {
					JOptionPane.showMessageDialog(frame, "ע��ɹ�", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "�������ע��ʧ��", "��ʾ",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		button.setBounds(277, 137, 93, 23);
		frame.getContentPane().add(button);
	}

	/**
	 * ��ȡ�ı�������
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
			JOptionPane.showMessageDialog(frame, "�����������벻һ�£�", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		userPwd = userPwd_first;

		if (!(Utils.isUserNameQualifiedRule(userName) && Utils
				.isUserPwdQualifiedRule(userPwd))) {
			et_userPwd_first.setText("");
			et_userPwd_second.setText("");
			JOptionPane.showMessageDialog(frame, "��������û��������벻�Ϸ���������", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;

	}
}
