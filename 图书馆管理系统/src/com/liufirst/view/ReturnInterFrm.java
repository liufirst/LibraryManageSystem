package com.liufirst.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.liufirst.dao.BorrowDao;
import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.model.Borrow;
import com.liufirst.model.Student;
import com.liufirst.util.DBUtil;
import com.liufirst.util.StringUtil;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReturnInterFrm extends JInternalFrame {
	private JTextField search_b_name;
	private JTextField search_s_id;
	private JTable borrow_table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField borrow_id;
	private JTextField book_name;
	private JTextField book_id;
	private JTextField student_name;
	private JTextField student_id;
	
	private Connection con=null;

	private JRadioButton out_rb;
	private JRadioButton in_rb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnInterFrm frame = new ReturnInterFrm();
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
	public ReturnInterFrm() {
		setIconifiable(true);
		setClosable(true);
		setTitle("\u56FE\u4E66\u5F52\u8FD8");
		setBounds(100, 100, 1200, 700);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 814, 557);
		getContentPane().add(scrollPane);

		borrow_table = new JTable();

		borrow_table.getTableHeader().setReorderingAllowed(false);// 表头不可拖动

		borrow_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				TableMousePressed(e);
			}
		});
		borrow_table.setFont(new Font("楷体", Font.BOLD, 20));
		borrow_table.setRowHeight(40);// 行高
		// borrow_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		borrow_table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u501F\u9605\u7F16\u53F7", "\u56FE\u4E66\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0",
						"\u72B6\u6001", "\u5B66\u751F\u7F16\u53F7", "\u5B66\u751F\u59D3\u540D",
						"\u5B66\u751F\u6027\u522B" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(borrow_table);

		JLabel lblNewLabel_2 = new JLabel("\u501F\u9605\u7F16\u53F7");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_2.setBounds(834, 92, 100, 35);
		getContentPane().add(lblNewLabel_2);

		borrow_id = new JTextField();
		borrow_id.setFont(new Font("宋体", Font.BOLD, 20));
		borrow_id.setBounds(944, 92, 200, 35);
		getContentPane().add(borrow_id);
		borrow_id.setEditable(false);
		borrow_id.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u56FE\u4E66\u7F16\u53F7");
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_3.setBounds(834, 164, 100, 35);
		getContentPane().add(lblNewLabel_3);

		book_id = new JTextField();
		book_id.setFont(new Font("宋体", Font.BOLD, 20));
		book_id.setBounds(944, 166, 200, 35);
		getContentPane().add(book_id);
		book_id.setEditable(false);
		book_id.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("\u5B66\u751F\u7F16\u53F7");
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_5.setBounds(834, 245, 100, 35);
		getContentPane().add(lblNewLabel_5);

		student_id = new JTextField();
		student_id.setFont(new Font("宋体", Font.BOLD, 20));
		student_id.setBounds(944, 247, 200, 35);
		getContentPane().add(student_id);
		student_id.setEditable(false);
		student_id.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		lblNewLabel_4.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_4.setBounds(834, 317, 100, 35);
		getContentPane().add(lblNewLabel_4);

		book_name = new JTextField();
		book_name.setFont(new Font("宋体", Font.BOLD, 20));
		book_name.setBounds(944, 319, 200, 35);
		getContentPane().add(book_name);
		book_name.setEditable(false);
		book_name.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("\u5B66\u751F\u59D3\u540D");
		lblNewLabel_6.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_6.setBounds(834, 394, 100, 35);
		getContentPane().add(lblNewLabel_6);

		student_name = new JTextField();
		student_name.setFont(new Font("宋体", Font.BOLD, 20));
		student_name.setBounds(944, 396, 200, 35);
		getContentPane().add(student_name);
		student_name.setEditable(false);
		student_name.setColumns(10);

		out_rb = new JRadioButton("\u501F\u9605");
		out_rb.setFont(new Font("宋体", Font.BOLD, 20));
		out_rb.setBounds(940, 470, 80, 35);
		getContentPane().add(out_rb);
		buttonGroup.add(out_rb);

		in_rb = new JRadioButton("\u5728\u5E93");
		in_rb.setFont(new Font("宋体", Font.BOLD, 20));
		in_rb.setBounds(1024, 470, 80, 35);
		getContentPane().add(in_rb);
		buttonGroup.add(in_rb);

		JButton btnNewButton_1 = new JButton("\u5F52\u8FD8");
		btnNewButton_1.setIcon(new ImageIcon(ReturnInterFrm.class.getResource("/images/\u786E\u8BA4.png")));
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD, 20));
		btnNewButton_1.setBounds(859, 579, 120, 40);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u91CD\u7F6E");
		btnNewButton_2.setIcon(new ImageIcon(ReturnInterFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		btnNewButton_2.setFont(new Font("楷体", Font.BOLD, 20));
		btnNewButton_2.setBounds(1038, 579, 120, 40);
		getContentPane().add(btnNewButton_2);

		JLabel lblNewLabel_7 = new JLabel("\u56FE\u4E66\u72B6\u6001");
		lblNewLabel_7.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_7.setBounds(834, 470, 100, 35);
		getContentPane().add(lblNewLabel_7);

		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setIcon(new ImageIcon(ReturnInterFrm.class.getResource("/images/\u641C\u7D22.png")));
		btnNewButton.setFont(new Font("楷体", Font.BOLD, 20));
		btnNewButton.setBounds(704, 29, 120, 40);
		getContentPane().add(btnNewButton);

		search_s_id = new JTextField();
		search_s_id.setFont(new Font("宋体", Font.BOLD, 20));
		search_s_id.setBounds(387, 32, 200, 35);
		getContentPane().add(search_s_id);
		search_s_id.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u5B66\u751F\u7F16\u53F7");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(295, 30, 100, 35);
		getContentPane().add(lblNewLabel_1);

		search_b_name = new JTextField();
		search_b_name.setFont(new Font("楷体", Font.BOLD, 20));
		search_b_name.setBounds(120, 32, 150, 35);
		getContentPane().add(search_b_name);
		search_b_name.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 30, 100, 35);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_8.setIcon(new ImageIcon(ReturnInterFrm.class.getResource("/images/\u5185\u90E8\u80CC\u666F4.jpg")));
		lblNewLabel_8.setBounds(0, 0, 1200, 686);
		getContentPane().add(lblNewLabel_8);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 所有借阅图书查询
				SearchActionPerformed(e);
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 重置事件
				reset();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 归还事件
				ReturnActionPerformed(e);
			}
		});

		this.fillTable(new Borrow());

	}

	/**
	 * 图书归还事件 修改图书状态为 在库
	 * 
	 * @param e
	 */
	private void ReturnActionPerformed(ActionEvent e) {
		// 如果没有选择学生或者图书
		if (StringUtil.isEmpty(book_id.getText())) {
			JOptionPane.showMessageDialog(null, "请选择归还的图书");
		}
		int aa = Integer.parseInt(this.book_id.getText());
		String bb = this.student_id.getText();
		int nn=Integer.parseInt(this.borrow_id.getText());
		// 如果为在库状态，无法添加
		if (out_rb.isSelected()) {
			JOptionPane.showMessageDialog(null, "归还，请更改图书状态");
			return;
		}
		try {
//!!!!!!
//!!!!!!
//还没有！！！修改！！！图书！！状态！！！！
			con = DBUtil.createConnection();
			Book book = new Book(aa);
			Borrow bo = new Borrow();
			bo.setBo_id(nn);

			
			int updateNum01 = BorrowDao.Update_0(con, book);// 更改图书状态为 在库
			//更改borrow 表单中borrow——kind为归还
			int updateNum02 =BorrowDao.Update_return(con, bo);
			if (updateNum02 == 1) {
				JOptionPane.showMessageDialog(null, "归还图书成功！");
				reset();
				this.fillTable(new Borrow());
			} else {
				JOptionPane.showMessageDialog(null, "图书借阅失败！");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

	}

	/**
	 * 借阅表单表格行点击事件
	 * 
	 * @param e
	 */
	private void TableMousePressed(MouseEvent e) {
		int row = this.borrow_table.getSelectedRow();
		// 给文本框赋值
		this.borrow_id.setText((String) borrow_table.getValueAt(row, 0));// 记得转换回来int
		this.book_id.setText((String) borrow_table.getValueAt(row, 1));
		this.book_name.setText((String) borrow_table.getValueAt(row, 2));
		// this..setText((String)borrow_table.getValueAt(row, 3)); 图书状态应该是默认借阅的
		this.student_id.setText((String) borrow_table.getValueAt(row, 4));
		this.student_name.setText((String) borrow_table.getValueAt(row, 5));
		String status = (String) borrow_table.getValueAt(row, 3);
		if ("在库".equals(status)) {
			this.in_rb.setSelected(true);
		} else if ("借阅".equals(status)) {
			this.out_rb.setSelected(true);
		}
		//

	}

	/**
	 * 所有借阅图书查询
	 * 
	 * @param e
	 */
	private void SearchActionPerformed(ActionEvent e) {
		String aa = this.search_b_name.getText();
		String bb = this.search_s_id.getText();

		Borrow borr = new Borrow();
		borr.setBo_bookname(aa);
		borr.setBo_studentid(bb);
		// 学生只有一个（String s_id,String s_password）构造方法，再弄（String s_id,String s_name）不能重载
		this.fillTable(borr);

	}

	/**
	 * 重置事件
	 */
	private void reset() {
		this.book_id.setText("");
		this.book_name.setText("");
		this.student_id.setText("");
		this.student_name.setText("");
		this.borrow_id.setText("");
		this.out_rb.isSelected();

	}

	/**
	 * 初始化 借阅 表格
	 * 
	 * @param bt
	 */
	private void fillTable(Borrow bo) {
		DefaultTableModel dtm = (DefaultTableModel) borrow_table.getModel();
		dtm.setRowCount(0);// 设置成0行
		Connection con = null;
		try {
			con = DBUtil.createConnection();
			ResultSet rs = BorrowDao.search(con, bo);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("borrow_id"));
				v.add(rs.getString("book_id"));
				v.add(rs.getString("b_name"));
				v.add(rs.getString("borrow_kind"));
				v.add(rs.getString("student_id"));
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

	/**
	 * 设置列表某一列的宽度
	 * 
	 * @param i
	 * @param width
	 */
	public static void setColumnSize(JTable table, int i, int preferedWidth, int maxWidth, int minWidth) {
		// 表格的列模型
		TableColumnModel cm = table.getColumnModel();
		// 得到第i个列对象
		TableColumn column = cm.getColumn(i);
		column.setPreferredWidth(preferedWidth);// 偏好
		column.setMaxWidth(maxWidth);// 最大
		column.setMinWidth(minWidth);// 最小
	}

}
