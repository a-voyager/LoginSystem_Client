package com.voyager.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import com.voyager.dao.UserDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author wuhaojie
 *
 */
public class UserCenter {

	private JFrame frame;
	private static JTextField textField;
	private static String[] argsUIs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		argsUIs = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserCenter window = new UserCenter();
					window.frame.setVisible(true);
					initData();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserCenter() {
		initialize();
	}

	private static void initData() {
		// TODO Auto-generated method stub
		textField.setText("登录成功，" + "\n欢迎回来！" + argsUIs[0]);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u4E2A\u4EBA\u4E2D\u5FC3");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBorder(null);
		textField.setEditable(false);
		textField.setText("\u767B\u5F55\u6210\u529F\uFF01");
		textField.setBounds(10, 10, 414, 160);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btn_logout = new JButton("\u6CE8\u9500");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (UserDao.logout()) {
					frame.dispose();
					Login.main(null);
				}
			}
		});
		btn_logout.setBounds(173, 180, 93, 23);
		frame.getContentPane().add(btn_logout);
	}
}
