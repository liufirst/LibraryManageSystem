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
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel.setBounds(167, 126, 90, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel_1.setBounds(167, 219, 90, 30);
		contentPane.add(lblNewLabel_1);

		password = new JPasswordField();
		password.setEchoChar('*');
		password.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		password.setBounds(267, 219, 200, 30);
		contentPane.add(password);

		id = new JTextField();
		id.setFont(new Font("����", Font.BOLD, 25));
		id.setBounds(267, 125, 200, 30);
		contentPane.add(id);
		id.setColumns(10);

		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(e);
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 25));
		btnNewButton.setBounds(190, 340, 110, 35);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 25));
		btnNewButton_1.setBounds(380, 340, 110, 35);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel_2
				.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF.png")));
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 30));
		lblNewLabel_2.setBounds(201, 38, 353, 52);
		contentPane.add(lblNewLabel_2);

		// ��½���������ʾ
		this.setLocationRelativeTo(null);

	}

	/**
	 * ��¼���������� ��ȡ�ı�������--����ȡȨ���������ö����--���ж��Ƿ�Ϊ��--��try-catch���ֲ�Ϊ����Ա��ѧ����
	 * ����Ա��½--��dao��static��¼��login������--��login�ķ���Admin�������Ϊ�����Ǵ���--�����ص�½���棬���������棨���ÿɼ���
	 * ѧ����¼--��
	 * 
	 * @param e
	 */
	protected void login(ActionEvent e) {
		// TODO Auto-generated method stub
		String aa = id.getText();
		String bb = new String(password.getPassword());// �������Ҫ�������ķ��������ַ������飬��������String�Ĺ��췽��
		// UserType selectItem=(UserType)comboBox.getSelectedItem();//����ת��ΪUserType����

		if (StringUtil.isEmpty(aa)) {
			JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
		} else if (StringUtil.isEmpty(bb)) {
			JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
		}
		try {
			con = DBUtil.createConnection();
			Admin admin = AdminDao.login(con, new Admin(aa, bb));

			if (admin == null) {
				JOptionPane.showMessageDialog(null, "�û��������������");
				return;
			}
			JOptionPane.showMessageDialog(null, "��ӭ������Ա����" + admin.getA_name());
			dispose();
			new MainAdminFrm().setVisible(true);
			/**
			 * if("����Ա".equals(selectItem.getName())) { //����Ա��½ Admin
			 * admin=AdminDao.login(con, new Admin(aa,bb));
			 * 
			 * if(admin==null) { JOptionPane.showMessageDialog(null, "�û��������������"); return; }
			 * JOptionPane.showMessageDialog(null, "��ӭ������Ա����"+admin.getA_name()); dispose();
			 * new MainAdminFrm().setVisible(true);
			 * 
			 * }else if("ѧ��".equals(selectItem.getName())) { //ѧ����½,�˹������� Student
			 * student=StudentDao.login(con, new Student(aa, bb));
			 * 
			 * if(student==null) { JOptionPane.showMessageDialog(null, "�û��������������"); return;
			 * } JOptionPane.showMessageDialog(null, "��ӭ��ѧ������"+student.getS_name());
			 * dispose(); //new MainStudentFrm().setVisible(true); }
			 **/
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}

	}

	/**
	 * ���õ�½����
	 * 
	 * @param e
	 */
	protected void reset(ActionEvent e) {
		// TODO Auto-generated method stub
		this.id.setText("");
		this.password.setText("");
	}
}
