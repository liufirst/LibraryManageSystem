package com.liufirst.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.liufirst.dao.AdminDao;
import com.liufirst.dao.StudentDao;
import com.liufirst.model.Admin;
import com.liufirst.model.Student;
import com.liufirst.model.UserType;
import com.liufirst.util.DBUtil;
import com.liufirst.util.StringUtil;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginFrm extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;
	private JTextField id;
	private Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm frame = new LoginFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrm() {

		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDFV1.0");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(LoginFrm.class.getResource("/images/\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\uFF1A");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setFont(new Font("黑体", Font.BOLD, 25));
		lblNewLabel.setBounds(167, 126, 90, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("黑体", Font.BOLD, 25));
		lblNewLabel_1.setBounds(167, 219, 90, 30);
		contentPane.add(lblNewLabel_1);

		password = new JPasswordField();
		password.setEchoChar('*');
		password.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		password.setBounds(267, 219, 200, 30);
		contentPane.add(password);

		id = new JTextField();
		id.setFont(new Font("楷体", Font.BOLD, 25));
		id.setBounds(267, 125, 200, 30);
		contentPane.add(id);
		id.setColumns(10);

		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(e);
			}
		});
		btnNewButton.setFont(new Font("楷体", Font.BOLD, 25));
		btnNewButton.setBounds(190, 340, 110, 35);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD, 25));
		btnNewButton_1.setBounds(380, 340, 110, 35);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel_2
				.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF.png")));
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 30));
		lblNewLabel_2.setBounds(201, 38, 353, 52);
		contentPane.add(lblNewLabel_2);

		// 登陆界面居中显示
		this.setLocationRelativeTo(null);

	}

	/**
	 * 登录进入主界面 获取文本框内容--》获取权限下拉框的枚举类--》判断是否为空--》try-catch（分叉为管理员和学生）
	 * 管理员登陆--》dao的static登录（login）方法--》login的返回Admin对象如果为空则是错误--》隐藏登陆界面，生成主界面（设置可见）
	 * 学生登录--》
	 * 
	 * @param e
	 */
	protected void login(ActionEvent e) {
		// TODO Auto-generated method stub
		String aa = id.getText();
		String bb = new String(password.getPassword());// 这里很重要，密码框的方法传回字符串数组，这是利用String的构造方法
		// UserType selectItem=(UserType)comboBox.getSelectedItem();//向上转型为UserType类型

		if (StringUtil.isEmpty(aa)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空");
		} else if (StringUtil.isEmpty(bb)) {
			JOptionPane.showMessageDialog(null, "密码不能为空");
		}
		try {
			con = DBUtil.createConnection();
			Admin admin = AdminDao.login(con, new Admin(aa, bb));

			if (admin == null) {
				JOptionPane.showMessageDialog(null, "用户名或者密码错误");
				return;
			}
			JOptionPane.showMessageDialog(null, "欢迎【管理员】：" + admin.getA_name());
			dispose();
			new MainAdminFrm().setVisible(true);
			/**
			 * if("管理员".equals(selectItem.getName())) { //管理员登陆 Admin
			 * admin=AdminDao.login(con, new Admin(aa,bb));
			 * 
			 * if(admin==null) { JOptionPane.showMessageDialog(null, "用户名或者密码错误"); return; }
			 * JOptionPane.showMessageDialog(null, "欢迎【管理员】："+admin.getA_name()); dispose();
			 * new MainAdminFrm().setVisible(true);
			 * 
			 * }else if("学生".equals(selectItem.getName())) { //学生登陆,此功能作废 Student
			 * student=StudentDao.login(con, new Student(aa, bb));
			 * 
			 * if(student==null) { JOptionPane.showMessageDialog(null, "用户名或者密码错误"); return;
			 * } JOptionPane.showMessageDialog(null, "欢迎【学生】："+student.getS_name());
			 * dispose(); //new MainStudentFrm().setVisible(true); }
			 **/
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}

	}

	/**
	 * 重置登陆界面
	 * 
	 * @param e
	 */
	protected void reset(ActionEvent e) {
		// TODO Auto-generated method stub
		this.id.setText("");
		this.password.setText("");
	}
}
