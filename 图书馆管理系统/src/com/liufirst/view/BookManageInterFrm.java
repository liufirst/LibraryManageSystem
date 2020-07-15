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
import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.util.DBUtil;
import com.liufirst.util.StringUtil;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class BookManageInterFrm extends JInternalFrame {
	private JTable b_table;
	private JTextField search_name;
	private JTextField search_writer;
	private JTextField update_id;
	private JTextField update_writer;
	private JTextField update_publisher;
	private JComboBox search_kind;
	private JComboBox update_kind;
	private JTextArea update_description;

	private Connection con = null;
	private JTextField update_name;
	private JTextField update_status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageInterFrm frame = new BookManageInterFrm();
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
	public BookManageInterFrm() {
		setTitle("\u56FE\u4E66\u67E5\u8BE2\u4E0E\u66F4\u65B0");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1200, 700);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 1168, 221);

		b_table = new JTable();
		b_table.setFont(new Font("楷体", Font.BOLD, 20));
		b_table.setRowHeight(40);// 行高
		b_table.getTableHeader().setReorderingAllowed(false);// 表头不可拖动
		b_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent met) {
				// 表格行点击
				bookTableMousePressed(met);
			}
		});
		getContentPane().setLayout(null);
		b_table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u4F5C\u8005",
						"\u51FA\u7248\u793E", "\u56FE\u4E66\u63CF\u8FF0", "\u56FE\u4E66\u7C7B\u522B",
						"\u56FE\u4E66\u72B6\u6001" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(b_table);
		getContentPane().add(scrollPane);

		JLabel lblNewLabel_3 = new JLabel("\u56FE\u4E66\u7F16\u53F7");
		lblNewLabel_3.setBounds(35, 351, 90, 35);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 20));

		update_id = new JTextField();
		update_id.setBounds(145, 351, 140, 35);
		getContentPane().add(update_id);
		update_id.setFont(new Font("楷体", Font.BOLD, 20));
		update_id.setEditable(false);
		update_id.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("\u56FE\u4E66\u540D\u79F0");
		lblNewLabel_8.setBounds(323, 351, 90, 35);
		getContentPane().add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("楷体", Font.BOLD, 20));

		update_name = new JTextField();
		update_name.setBounds(423, 351, 187, 35);
		getContentPane().add(update_name);
		update_name.setFont(new Font("楷体", Font.BOLD, 20));
		update_name.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("\u51FA\u7248\u793E");
		lblNewLabel_5.setBounds(620, 351, 90, 35);
		getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 20));

		update_publisher = new JTextField();
		update_publisher.setBounds(760, 351, 300, 35);
		getContentPane().add(update_publisher);
		update_publisher.setFont(new Font("楷体", Font.BOLD, 20));
		update_publisher.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("\u56FE\u4E66\u7C7B\u522B");
		lblNewLabel_6.setBounds(35, 415, 90, 35);
		getContentPane().add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("楷体", Font.BOLD, 20));

		update_kind = new JComboBox();
		update_kind.setBounds(145, 415, 140, 35);
		getContentPane().add(update_kind);
		update_kind.setFont(new Font("楷体", Font.BOLD, 20));

		JLabel lblNewLabel_4 = new JLabel("\u56FE\u4E66\u4F5C\u8005");
		lblNewLabel_4.setBounds(323, 415, 90, 35);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("楷体", Font.BOLD, 20));

		update_writer = new JTextField();
		update_writer.setBounds(423, 415, 187, 35);
		getContentPane().add(update_writer);
		update_writer.setFont(new Font("楷体", Font.BOLD, 20));
		update_writer.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("\u56FE\u4E66\u72B6\u6001");
		lblNewLabel_9.setBounds(620, 415, 90, 35);
		getContentPane().add(lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("楷体", Font.BOLD, 20));

		update_status = new JTextField();
		update_status.setBounds(755, 415, 140, 35);
		getContentPane().add(update_status);
		update_status.setFont(new Font("楷体", Font.BOLD, 20));
		update_status.setEditable(false);
		update_status.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("\u56FE\u4E66\u63CF\u8FF0");
		lblNewLabel_7.setBounds(35, 475, 90, 35);
		getContentPane().add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("楷体", Font.BOLD, 20));

		JButton update = new JButton("\u4FEE\u6539");
		update.setBounds(745, 542, 150, 50);
		getContentPane().add(update);
		update.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/\u786E\u8BA4.png")));
		update.setFont(new Font("楷体", Font.BOLD, 25));

		JButton delete = new JButton("\u5220\u9664");
		delete.setBounds(969, 542, 150, 50);
		getContentPane().add(delete);
		delete.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/\u5220\u9664.png")));
		delete.setFont(new Font("楷体", Font.BOLD, 25));

		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		lblNewLabel.setBounds(25, 27, 100, 35);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 20));

		search_name = new JTextField();
		search_name.setBounds(135, 27, 180, 35);
		getContentPane().add(search_name);
		search_name.setFont(new Font("楷体", Font.BOLD, 20));
		search_name.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u56FE\u4E66\u4F5C\u8005:");
		lblNewLabel_1.setBounds(335, 27, 100, 35);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 20));

		search_writer = new JTextField();
		search_writer.setBounds(437, 27, 180, 35);
		getContentPane().add(search_writer);
		search_writer.setFont(new Font("楷体", Font.BOLD, 20));
		search_writer.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u56FE\u4E66\u7C7B\u522B:");
		lblNewLabel_2.setBounds(638, 27, 100, 35);
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 20));

		search_kind = new JComboBox();
		search_kind.setBounds(729, 27, 180, 35);
		getContentPane().add(search_kind);
		search_kind.setFont(new Font("楷体", Font.BOLD, 20));

		JButton search = new JButton("\u641C\u7D22");
		search.setBounds(969, 22, 150, 40);
		getContentPane().add(search);
		search.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/\u641C\u7D22.png")));
		search.setFont(new Font("楷体", Font.BOLD, 25));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(145, 492, 465, 122);
		getContentPane().add(scrollPane_1);
		
				update_description = new JTextArea();
				update_description.setLineWrap(true);        //激活自动换行功能
				scrollPane_1.setViewportView(update_description);
				update_description.setFont(new Font("楷体", Font.BOLD, 20));
				
				JLabel lblNewLabel_10 = new JLabel("");
				lblNewLabel_10.setIcon(new ImageIcon(BookManageInterFrm.class.getResource("/images/\u5185\u90E8\u80CC\u666F3.jpg")));
				lblNewLabel_10.setBounds(0, 0, 1200, 683);
				getContentPane().add(lblNewLabel_10);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);

			}
		});
		delete.addActionListener(new ActionListener() {
			// 图书删除事件
			public void actionPerformed(ActionEvent e) {
				bookDeleteActionPerformed(e);
			}
		});

		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 图书修改事件
				bookUpdateActionPerformed(e);
			}
		});

		this.fillBookType("search");
		this.fillBookType("modify");
		this.fillTable(new Book());

	}

	/**
	 * 图书删除
	 * 
	 * @param e
	 */
	private void bookDeleteActionPerformed(ActionEvent e) {
		String id = update_id.getText();
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if (n == 0) {

			try {
				con = DBUtil.createConnection();
				int deleteNum = BookDao.delete(con, Integer.parseInt(id));
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					this.reset();
					this.fillTable(new Book());
				} else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			} finally {
				try {
					DBUtil.closeConnection(con);
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}

	}

	/**
	 * 图书修改事件
	 * 
	 * @param e
	 */
	private void bookUpdateActionPerformed(ActionEvent e) {
		String id = this.update_id.getText();// 记得转换成int
		if (StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}

		String b_name = this.update_name.getText();
		String b_writer = this.update_writer.getText();
		String b_publisher = this.update_publisher.getText();
		String b_description = this.update_description.getText();

		if (StringUtil.isEmpty(b_name)) {
			JOptionPane.showMessageDialog(null, "图书名称不能为空！");
			return;
		}

		if (StringUtil.isEmpty(b_writer)) {
			JOptionPane.showMessageDialog(null, "图书作者不能为空！");
			return;
		}

		BookType bt = (BookType) update_kind.getSelectedItem();
		int bt_id = bt.getBt_id();

		Book book = new Book(Integer.parseInt(id), b_name, b_writer, b_publisher, bt_id, b_description);

		try {
			con = DBUtil.createConnection();
			int updateNum = BookDao.update(con, book);
			if (updateNum == 1) {
				JOptionPane.showMessageDialog(null, "图书修改成功！");
				reset();
				this.fillTable(new Book());
			} else {
				JOptionPane.showMessageDialog(null, "图书修改失败！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书修改失败！");
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

	}

	/**
	 * 表格行点击事件
	 * 
	 * @param met
	 */
	private void bookTableMousePressed(MouseEvent met) {
		int row = this.b_table.getSelectedRow();
		// 给文本框赋值
		this.update_id.setText((String) b_table.getValueAt(row, 0));// 记得转换回来int
		this.update_name.setText((String) b_table.getValueAt(row, 1));
		this.update_writer.setText((String) b_table.getValueAt(row, 2));
		this.update_publisher.setText((String) b_table.getValueAt(row, 3));
		this.update_description.setText((String) b_table.getValueAt(row, 4));
		this.update_status.setText((String) b_table.getValueAt(row, 6));
		//
		String update_btName = (String) this.b_table.getValueAt(row, 5); // ①
		int n = this.update_kind.getItemCount();// 获取下拉框的行数
		for (int i = 0; i < n; i++) {
			BookType bt = (BookType) this.update_kind.getItemAt(i);
			if (bt.getBt_name().equals(update_btName)) {// 这里解决了下拉框不显示的bug ① update_btName!!!
				this.update_kind.setSelectedIndex(i);
			}
		}

	}

	/**
	 * 图书查询事件
	 * 
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent e) {
		String aa = this.search_name.getText();
		String bb = this.search_writer.getText();
		BookType bt = (BookType) search_kind.getSelectedItem();
		int n = bt.getBt_id();

		Book book = new Book(aa, bb, n);

		this.fillTable(book);

	}

	/**
	 * 初始化下拉框
	 * 
	 * @param type 下拉框类型
	 */
	private void fillBookType(String type) {

		BookType bt = null;
		try {
			con = DBUtil.createConnection();
			ResultSet rs = BookTypeDao.list(con, new BookType());
			if ("search".equals(type)) {
				bt = new BookType();
				bt.setBt_name("请选择...");
				bt.setBt_id(-1);
				this.search_kind.addItem(bt);
			}

			while (rs.next()) {
				bt = new BookType();
				bt.setBt_name(rs.getString("bt_name"));
				bt.setBt_id(rs.getInt("bt_id"));

				if ("search".equals(type)) {
					this.search_kind.addItem(bt);
				} else if ("modify".equals(type)) {
					this.update_kind.addItem(bt);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化表格
	 * 
	 * @param bt
	 */
	private void fillTable(Book b) {
		DefaultTableModel dtm = (DefaultTableModel) b_table.getModel();
		dtm.setRowCount(0);// 设置成0行
		Connection con = null;
		try {
			con = DBUtil.createConnection();
			ResultSet rs = BookDao.list(con, b);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("b_id"));
				v.add(rs.getString("b_name"));
				v.add(rs.getString("b_writer"));
				v.add(rs.getString("b_publisher"));
				v.add(rs.getString("b_description"));
				v.add(rs.getString("bt_name"));
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
	 * 重置表单
	 */
	private void reset() {
		update_id.setText("");
		update_writer.setText("");
		update_name.setText("");
		update_publisher.setText("");
		update_description.setText("");
	}
}
