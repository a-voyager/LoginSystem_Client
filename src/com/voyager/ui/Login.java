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
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ��¼����
 * 
 * @author wuhaojie
 *
 */
public class Login {

	private JFrame frame;
	private JPasswordField et_userPwd;
	private String userName;
	private String userPwd;
	private JCheckBox cb_savePwd;
	private JTextField et_userName;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtDesignedByWuhaojie;

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
		frame.setTitle("\u6B22\u8FCE");
		frame.setBounds(100, 100, 450, 242);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		et_userName = new JTextField();
		et_userName.setToolTipText("");
		et_userName.setBounds(89, 42, 202, 32);
		frame.getContentPane().add(et_userName);

		cb_savePwd = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		cb_savePwd.setBounds(321, 89, 103, 23);
		frame.getContentPane().add(cb_savePwd);

		et_userPwd = new JPasswordField();
		et_userPwd.setBounds(89, 126, 202, 32);
		frame.getContentPane().add(et_userPwd);

		JButton btn_login = new JButton("\u767B\u5F55");
		btn_login.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!getText(et_userName))
					return;
				boolean login = UserDao.login(frame, userName, userPwd);
				System.out
						.println("UserDao.login(frame, userName, userPwd) :: "
								+ login);
				ConfigUtils.addConfig("userName", userName);
				if (login) {
					if (cb_savePwd.isSelected())
						ConfigUtils.addConfig("savePwd", "true");
					else
						ConfigUtils.addConfig("savePwd", "false");
					ConfigUtils.addConfig("userPwd", userPwd);
				}
			}

		});
		btn_login.setBounds(320, 42, 93, 23);
		frame.getContentPane().add(btn_login);

		JButton btn_register = new JButton("\u6CE8\u518C");
		btn_register.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
				Register.main(null);
			}
		});
		btn_register.setBounds(320, 130, 93, 23);
		frame.getContentPane().add(btn_register);

		textField = new JTextField();
		textField.setText("\u7528\u6237\u540D");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(new Color(214, 217, 223));
		textField.setBounds(10, 47, 66, 21);
		frame.getContentPane().add(textField);

		textField_1 = new JTextField();
		textField_1.setText("\u5BC6\u7801");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		textField_1.setFocusable(false);
		textField_1.setFocusTraversalKeysEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(214, 217, 223));
		textField_1.setBounds(10, 135, 66, 21);
		frame.getContentPane().add(textField_1);

		String text = "    Copyright (c) 2015 WuHaojie (�����)\n\n    Permission is hereby granted, free of charge, to any person obtaining a copy\n    of this software and associated documentation files (the \"Software\"), to deal\n    in the Software without restriction, including without limitation the rights\n    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n    copies of the Software, and to permit persons to whom the Software is\n    furnished to do so, subject to the following conditions:\n\n    The above copyright notice and this permission notice shall be included in all\n    copies or substantial portions of the Software.";
		txtDesignedByWuhaojie = new JTextField();
		txtDesignedByWuhaojie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frame, text, "License",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		txtDesignedByWuhaojie.setForeground(Color.DARK_GRAY);
		txtDesignedByWuhaojie.setText("Designed by WuHaojie");
		txtDesignedByWuhaojie.setHorizontalAlignment(SwingConstants.CENTER);
		txtDesignedByWuhaojie.setFont(new Font("SentyС����", Font.PLAIN, 16));
		txtDesignedByWuhaojie.setFocusable(false);
		txtDesignedByWuhaojie.setFocusTraversalKeysEnabled(false);
		txtDesignedByWuhaojie.setEditable(false);
		txtDesignedByWuhaojie.setColumns(10);
		txtDesignedByWuhaojie.setBorder(null);
		txtDesignedByWuhaojie.setBackground(new Color(214, 217, 223));
		txtDesignedByWuhaojie.setBounds(303, 183, 131, 21);
		frame.getContentPane().add(txtDesignedByWuhaojie);

		loadConfig();

	}

	protected void closeWindow() {
		// TODO Auto-generated method stub
		frame.dispose();
	}

	/**
	 * ��ȡ�ı�������
	 * 
	 * @param et_userName
	 */
	private boolean getText(JTextField et_userName) {
		userName = et_userName.getText();
		char[] password = et_userPwd.getPassword();
		userPwd = new String(password);
		if (!(Utils.isUserNameQualifiedRule(userName) && Utils
				.isUserPwdQualifiedRule(userPwd))) {
			et_userPwd.setText("");
			JOptionPane.showMessageDialog(frame, "��������û��������벻�Ϸ���������", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;

	}
}
