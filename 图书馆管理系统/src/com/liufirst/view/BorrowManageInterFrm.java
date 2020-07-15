package com.liufirst.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.liufirst.dao.BookDao;
import com.liufirst.dao.BookTypeDao;
import com.liufirst.dao.BorrowDao;
import com.liufirst.dao.StatusDao;
import com.liufirst.dao.StudentDao;
import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.model.Borrow;
import com.liufirst.model.Status;
import com.liufirst.model.Student;
import com.liufirst.util.DBUtil;
import com.liufirst.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.ImageIcon;

public class BorrowManageInterFrm extends JInternalFrame {
	private JTable book_table;
	private JTextField search_b_name;
	private JTextField search_b_writer;
	private JTextField search_s_id;
	private JTextField search_s_name;
	private JTable student_table;
	private JTextField student_id;
	private JTextField student_name;
	private JTextField student_major;
	private JTextField book_id;
	private JTextField book_name;
	private JRadioButton out_rb;

	private JRadioButton in_rb;

	private Connection con = null;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowManageInterFrm frame = new BorrowManageInterFrm();
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
	public BorrowManageInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u501F\u9605");
		setBounds(100, 100, 1200, 700);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 20, 840, 182);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(374, 211, 840, 198);

		book_table = new JTable();
		book_table.setFont(new Font("宋体", Font.BOLD, 20));
		book_table.setRowHeight(40);// 行高
		book_table.getTableHeader().setReorderingAllowed(false);// 表头不可拖动
		book_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 图书表格点击事件
				booTableMousePressed(e);
			}
		});
		book_table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u56FE\u4E66\u7F16\u53F7",
				"\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005", "\u72B6\u6001" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		book_table.getColumnModel().getColumn(0).setPreferredWidth(89);
		book_table.getColumnModel().getColumn(1).setPreferredWidth(96);
		book_table.getColumnModel().getColumn(2).setPreferredWidth(93);
		scrollPane.setViewportView(book_table);
		getContentPane().setLayout(null);
		getContentPane().add(scrollPane);
		getContentPane().add(scrollPane_1);

		student_table = new JTable();
		student_table.setFont(new Font("宋体", Font.BOLD, 20));
		student_table.setRowHeight(40);// 行高
		student_table.getTableHeader().setReorderingAllowed(false);// 表头不可拖动
		student_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 学生信息表格点击
				studentTableMousePressed(e);
			}

		});
		student_table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u5B66\u53F7", "\u59D3\u540D", "\u6027\u522B" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(student_table);

		search_b_name = new JTextField();
		search_b_name.setFont(new Font("楷体", Font.BOLD, 20));
		search_b_name.setBounds(124, 20, 180, 35);
		getContentPane().add(search_b_name);
		search_b_name.setColumns(10);

		search_b_writer = new JTextField();
		search_b_writer.setFont(new Font("楷体", Font.BOLD, 20));
		search_b_writer.setBounds(124, 65, 180, 35);
		getContentPane().add(search_b_writer);
		search_b_writer.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel.setBounds(21, 21, 100, 35);
		getContentPane().add(lblNewLabel);

		JLabel asdadad = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		asdadad.setFont(new Font("楷体", Font.BOLD, 20));
		asdadad.setBounds(21, 66, 100, 35);
		getContentPane().add(asdadad);

		JLabel lblNewLabel_2 = new JLabel("\u53EA\u67E5\u8BE2\u72B6\u6001\u4E3A  \u5728\u5E93\u7684");
		lblNewLabel_2.setBounds(149, 110, 128, 15);
		getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		btnNewButton.setIcon(new ImageIcon(BorrowManageInterFrm.class.getResource("/images/\u641C\u7D22.png")));
		btnNewButton.setFont(new Font("楷体", Font.BOLD, 20));
		btnNewButton.setBounds(91, 148, 172, 40);
		getContentPane().add(btnNewButton);

		search_s_id = new JTextField();
		search_s_id.setFont(new Font("楷体", Font.BOLD, 20));
		search_s_id.setBounds(91, 215, 213, 35);
		getContentPane().add(search_s_id);
		search_s_id.setColumns(10);

		search_s_name = new JTextField();
		search_s_name.setFont(new Font("楷体", Font.BOLD, 20));
		search_s_name.setBounds(89, 276, 215, 35);
		getContentPane().add(search_s_name);
		search_s_name.setColumns(10);

		JButton btnNewButton_1 = new JButton("\u5B66\u751F\u67E5\u8BE2");
		btnNewButton_1.setIcon(new ImageIcon(BorrowManageInterFrm.class.getResource("/images/\u641C\u7D22.png")));
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD, 20));
		btnNewButton_1.setBounds(91, 359, 172, 40);
		getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");
		lblNewLabel_1.setBounds(21, 213, 50, 35);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 20));

		JLabel lblNewLabel_3 = new JLabel("\u59D3\u540D");
		lblNewLabel_3.setBounds(21, 274, 50, 35);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 20));

		JLabel lblNewLabel_4 = new JLabel("\u5B66\u751F\u5B66\u53F7\uFF1A");
		lblNewLabel_4.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_4.setBounds(43, 446, 120, 35);
		getContentPane().add(lblNewLabel_4);

		student_id = new JTextField();
		student_id.setFont(new Font("楷体", Font.BOLD, 20));
		student_id.setBounds(167, 443, 150, 35);
		getContentPane().add(student_id);
		student_id.setEditable(false);
		student_id.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("\u5B66\u751F\u59D3\u540D:");
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_5.setBounds(374, 446, 120, 35);
		getContentPane().add(lblNewLabel_5);

		student_name = new JTextField();
		student_name.setFont(new Font("楷体", Font.BOLD, 20));
		student_name.setBounds(504, 446, 150, 35);
		getContentPane().add(student_name);
		student_name.setEditable(false);
		student_name.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("\u5B66\u751F\u6027\u522B\uFF1A");
		lblNewLabel_6.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_6.setBounds(746, 446, 120, 35);
		getContentPane().add(lblNewLabel_6);

		student_major = new JTextField();
		student_major.setFont(new Font("楷体", Font.BOLD, 20));
		student_major.setBounds(876, 446, 150, 35);
		getContentPane().add(student_major);
		student_major.setEditable(false);
		student_major.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("\u56FE\u4E66\u7F16\u53F7\uFF1A");
		lblNewLabel_7.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_7.setBounds(43, 504, 120, 35);
		getContentPane().add(lblNewLabel_7);

		book_id = new JTextField();
		book_id.setFont(new Font("楷体", Font.BOLD, 20));
		book_id.setBounds(167, 504, 150, 35);
		getContentPane().add(book_id);
		book_id.setEditable(false);
		book_id.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		lblNewLabel_8.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_8.setBounds(374, 504, 120, 35);
		getContentPane().add(lblNewLabel_8);

		book_name = new JTextField();
		book_name.setFont(new Font("楷体", Font.BOLD, 20));
		book_name.setBounds(504, 504, 150, 35);
		getContentPane().add(book_name);
		book_name.setEditable(false);
		book_name.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("\u56FE\u4E66\u72B6\u6001:");
		lblNewLabel_9.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_9.setBounds(746, 504, 120, 35);
		getContentPane().add(lblNewLabel_9);

		in_rb = new JRadioButton("\u5728\u5E93");
		in_rb.setFont(new Font("宋体", Font.BOLD, 20));
		in_rb.setBounds(876, 504, 80, 35);
		getContentPane().add(in_rb);
		buttonGroup.add(in_rb);

		out_rb = new JRadioButton("\u501F\u9605");
		out_rb.setFont(new Font("宋体", Font.BOLD, 20));
		out_rb.setBounds(958, 504, 80, 35);
		getContentPane().add(out_rb);
		buttonGroup.add(out_rb);

		JButton btnNewButton_2 = new JButton("\u786E\u8BA4");
		btnNewButton_2.setIcon(new ImageIcon(BorrowManageInterFrm.class.getResource("/images/\u786E\u8BA4.png")));
		btnNewButton_2.setFont(new Font("楷体", Font.BOLD, 20));
		btnNewButton_2.setBounds(357, 601, 150, 40);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u91CD\u7F6E");
		btnNewButton_3.setIcon(new ImageIcon(BorrowManageInterFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		btnNewButton_3.setFont(new Font("楷体", Font.BOLD, 20));
		btnNewButton_3.setBounds(633, 601, 150, 40);
		getContentPane().add(btnNewButton_3);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(
				new ImageIcon(BorrowManageInterFrm.class.getResource("/images/\u5185\u90E8\u80CC\u666F3.jpg")));
		lblNewLabel_10.setBounds(0, 0, 1200, 671);
		getContentPane().add(lblNewLabel_10);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 借阅事件---①生成借阅对象在借阅表 ② 调整被借阅书籍为 借阅状态
				BorrowActionPerformed(e);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 学生查询
				studentSearchActionPerformed(e);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 图书查询
				bookSearchActionPerformed(e);
			}
		});

		this.fillTable(new Student());
		this.fillTable(new Book());

	}

	/**
	 * 借阅---确认
	 * 
	 * @param e
	 */
	private void BorrowActionPerformed(ActionEvent evt) {

		// 如果没有选择学生或者图书
		if (StringUtil.isEmpty(book_id.getText())) {
			JOptionPane.showMessageDialog(null, "请选择图书");
		}
		if (StringUtil.isEmpty(student_id.getText())) {
			JOptionPane.showMessageDialog(null, "请选择学生");
		}
		int aa = Integer.parseInt(this.book_id.getText());
		String bb = this.student_id.getText();
		// 如果为在库状态，无法添加
		if (in_rb.isSelected()) {
			JOptionPane.showMessageDialog(null, "借阅，请更改图书状态");
			return;
		}
		try {
			// !!!!!!
			// !!!!!!
			// 还没有！！！修改！！！图书！！状态！！！！
			con = DBUtil.createConnection();
			Book book = new Book(aa);
			Borrow bo = new Borrow(aa, bb);

			int updateNum01 = BorrowDao.addBorrow(con, bo);
			int updateNum02 = BorrowDao.Update_1(con, book);// 更改图书状态为 借阅
			if (updateNum01 == 1) {
				JOptionPane.showMessageDialog(null, "图书借阅成功！");
				reset();
				this.fillTable(new Book());
			} else {
				JOptionPane.showMessageDialog(null, "图书借阅失败！");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

	}

	/**
	 * 学生表格点击
	 * 
	 * @param e
	 */
	protected void studentTableMousePressed(MouseEvent e) {
		int row = this.student_table.getSelectedRow();
		// 给文本框赋值
		this.student_id.setText((String) student_table.getValueAt(row, 0));// 记得转换回来int
		this.student_name.setText((String) student_table.getValueAt(row, 1));
		this.student_major.setText((String) student_table.getValueAt(row, 2));

	}

	/**
	 * 图书表格行点击事件
	 * 
	 * @param e
	 */
	private void booTableMousePressed(MouseEvent e) {
		int row = this.book_table.getSelectedRow();
		// 给文本框赋值
		this.book_id.setText((String) book_table.getValueAt(row, 0));// 记得转换回来int
		this.book_name.setText((String) book_table.getValueAt(row, 1));

		String status = (String) book_table.getValueAt(row, 3);
		if ("在库".equals(status)) {
			this.in_rb.setSelected(true);
		} else if ("借阅".equals(status)) {
			this.out_rb.setSelected(true);
		}
		//

	}

	/**
	 * 学生查询
	 * 
	 * @param e
	 */
	private void studentSearchActionPerformed(ActionEvent evt) {
		String aa = this.search_s_id.getText();
		String bb = this.search_s_name.getText();
		Student stu = new Student();
		stu.setS_id(aa);
		stu.setS_name(bb);
		// 学生只有一个（String s_id,String s_password）构造方法，再弄（String s_id,String s_name）不能重载
		this.fillTable(stu);
	}

	/**
	 * 图书查询
	 * 
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent evt) {
		String bb = this.search_b_writer.getText();
		String aa = this.search_b_name.getText();
		Book book = new Book(aa, bb);

		this.fillTable(book);
	}

	/**
	 * 重置
	 */
	private void reset() {
		this.book_id.setText("");
		this.book_name.setText("");
		this.student_id.setText("");
		this.student_name.setText("");

		this.in_rb.isSelected();

	}

	/**
	 * 初始化 图书 表格
	 * 
	 * @param bt
	 */
	private void fillTable(Book b) {
		DefaultTableModel dtm = (DefaultTableModel) book_table.getModel();
		dtm.setRowCount(0);// 设置成0行
		Connection con = null;
		try {
			con = DBUtil.createConnection();
			ResultSet rs = BorrowDao.list(con, b);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("b_id"));
				v.add(rs.getString("b_name"));
				v.add(rs.getString("b_writer"));
				v.add(rs.getString("status_name"));

				dtm.addRow(v);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}

	}

	/**
	 * 初始化 学生 表格
	 * 
	 * @param bt
	 */
	private void fillTable(Student s) {
		DefaultTableModel dtm = (DefaultTableModel) student_table.getModel();
		dtm.setRowCount(0);// 设置成0行
		Connection con = null;
		try {
			con = DBUtil.createConnection();
			ResultSet rs = BorrowDao.list(con, s);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("s_id"));
				v.add(rs.getString("s_name"));
				v.add(rs.getString("s_sex"));

				dtm.addRow(v);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}

	}
}
