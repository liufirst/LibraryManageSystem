package com.liufirst.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.liufirst.dao.BookTypeDao;
import com.liufirst.model.BookType;
import com.liufirst.util.DBUtil;
import com.liufirst.util.StringUtil;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class AddBookTypeInterFrm extends JInternalFrame {
	private JTextField booktypename;
	private JTextArea booktypedescription;
	private Connection con=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookTypeInterFrm frame = new AddBookTypeInterFrm();
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
	public AddBookTypeInterFrm() {
		setTitle("\u6DFB\u52A0\u56FE\u4E66\u7C7B\u522B");
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7C7B\u522B\u540D\u79F0:");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel.setBounds(71, 72, 130, 40);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel_1.setBounds(71, 176, 130, 40);
		getContentPane().add(lblNewLabel_1);
		
		booktypename = new JTextField();
		booktypename.setFont(new Font("楷体", Font.BOLD, 20));
		booktypename.setBounds(211, 72, 437, 40);
		getContentPane().add(booktypename);
		booktypename.setColumns(10);
		
		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.setIcon(new ImageIcon(AddBookTypeInterFrm.class.getResource("/images/\u786E\u8BA4.png")));
		btnNewButton.setFont(new Font("楷体", Font.BOLD, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//确认-添加图书类别
				addBookType(e);
			}
		});
		btnNewButton.setBounds(144, 439, 150, 50);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setIcon(new ImageIcon(AddBookTypeInterFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD, 25));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重置
				reset();
			}
		});
		btnNewButton_1.setBounds(569, 439, 150, 50);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 194, 426, 194);
		
		getContentPane().add(scrollPane);
		
		booktypedescription = new JTextArea();
		scrollPane.setViewportView(booktypedescription);
		booktypedescription.setFont(new Font("楷体", Font.BOLD, 20));
		booktypedescription.setLineWrap(true);        //激活自动换行功能
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AddBookTypeInterFrm.class.getResource("/images/\u5185\u90E8\u80CC\u666F2.jpg")));
		lblNewLabel_2.setBounds(0, 0, 1000, 571);
		getContentPane().add(lblNewLabel_2);

	}
	/**
	 * 添加图书类别
	 * @param e
	 * @throws SQLException 
	 */
protected void addBookType(ActionEvent evt)  {
		// TODO Auto-generated method stub
	String aa=booktypename.getText();
	String bb=booktypedescription.getText();
	
	if(StringUtil.isEmpty(aa)) {
		JOptionPane.showMessageDialog(null, "类别名称不能为空");
		return;
	}
	con=DBUtil.createConnection();
	BookType bt=new BookType(aa,bb);
	try {
		int n=BookTypeDao.addBookType(con, bt);
		if(n==1) {
			JOptionPane.showMessageDialog(null, "添加成功");
			reset();
		}else {
			JOptionPane.showMessageDialog(null, "失败，添加错误");
		}
	}catch(Exception ex) {
		ex.printStackTrace();
		JOptionPane.showMessageDialog(null, "失败，添加错误,可能介绍字数过多");
	}finally {
		DBUtil.closeConnection(con);
	}
		
	}

/**
 * 重置
 * @param e
 */
	protected void reset() {
		// TODO Auto-generated method stub
		this.booktypename.setText("");
		this.booktypedescription.setText("");
		
	}
}
