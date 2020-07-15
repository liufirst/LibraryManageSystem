package com.liufirst.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.liufirst.dao.BookDao;
import com.liufirst.dao.BookTypeDao;
import com.liufirst.model.BookType;
import com.liufirst.util.DBUtil;
import com.liufirst.util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class BookTypeManageInterFrm extends JInternalFrame {
	private JTable bt_table;
	private JTextField search_textField;
	private JTextField id_textField;
	private JTextField update_textField;
	private JTextArea update_textArea;
	
	
	private Connection con=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageInterFrm frame = new BookTypeManageInterFrm();
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
	public BookTypeManageInterFrm() {
		setIconifiable(true);
		setTitle("\u56FE\u4E66\u7C7B\u522B\u67E5\u8BE2\u4E0E\u66F4\u65B0");
		setResizable(true);
		setClosable(true);
		setBounds(100, 100,1200, 700);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 84, 1129, 283);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u7C7B\u522B\u540D\u79F0\uFF1A");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel.setBounds(32, 30, 200, 30);
		
		search_textField = new JTextField();
		search_textField.setFont(new Font("楷体", Font.BOLD, 25));
		search_textField.setBounds(233, 30, 305, 30);
		search_textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u641C\u7D22.png")));
		btnNewButton.setFont(new Font("楷体", Font.BOLD, 25));
		btnNewButton.setBounds(949, 24, 150, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//图书类别查询
				bookTypeSearch(e);
			}
		});
		
		id_textField = new JTextField();
		id_textField.setFont(new Font("楷体", Font.BOLD, 25));
		id_textField.setBounds(194, 374, 102, 30);
		id_textField.setEditable(false);
		id_textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7C7B\u522B\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setBounds(32, 374, 150, 30);
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 25));
		
		update_textField = new JTextField();
		update_textField.setFont(new Font("楷体", Font.BOLD, 25));
		update_textField.setBounds(480, 379, 200, 30);
		update_textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7C7B\u522B\u540D\u79F0");
		lblNewLabel_2.setBounds(340, 376, 150, 30);
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 25));
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
		btnNewButton_1.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u786E\u8BA4.png")));
		btnNewButton_1.setBounds(768, 516, 150, 50);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表单操作--修改
				BookTypeUpdate(e);
			}
		});
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD, 25));
		
		JButton btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u5220\u9664.png")));
		btnNewButton_2.setBounds(992, 516, 150, 50);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//表单操作--删除
				BookTypeDelete(e);
			}
		});
		btnNewButton_2.setFont(new Font("楷体", Font.BOLD, 25));
		
		bt_table = new JTable();
		bt_table.setRowHeight(40);//行高
		bt_table.getTableHeader().setReorderingAllowed(false);//表头不可拖动
		bt_table.addMouseListener(new MouseAdapter() {
			@Override
			//点击表格
			public void mousePressed(MouseEvent e) {
				BookTypeMousePressed(e);
			}
		});
		bt_table.setFont(new Font("楷体", Font.BOLD, 22));
		bt_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7C7B\u522B\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u7C7B\u522B\u4ECB\u7ECD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		bt_table.getColumnModel().getColumn(0).setMinWidth(20);
	
		
		bt_table.setRowHeight(40);//行高
		scrollPane.setViewportView(bt_table);
		getContentPane().setLayout(null);
		getContentPane().add(id_textField);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(update_textField);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(btnNewButton_1);
		getContentPane().add(btnNewButton_2);
		getContentPane().add(lblNewLabel);
		getContentPane().add(search_textField);
		getContentPane().add(btnNewButton);
		getContentPane().add(scrollPane);
		 
		 JLabel lblNewLabel_3 = new JLabel("\u7C7B\u522B\u63CF\u8FF0\uFF1A");
		 lblNewLabel_3.setBounds(32, 451, 150, 30);
		 getContentPane().add(lblNewLabel_3);
		 lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 25));
		 
		 JScrollPane scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(188, 453, 492, 189);
		 getContentPane().add(scrollPane_1);
		 
		  update_textArea = new JTextArea();
		  update_textArea.setLineWrap(true);        //激活自动换行功能
		  scrollPane_1.setViewportView(update_textArea);
		  update_textArea.setFont(new Font("楷体", Font.BOLD, 20));
		  
		  JLabel lblNewLabel_4 = new JLabel("");
		  lblNewLabel_4.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u5185\u90E8\u80CC\u666F3.jpg")));
		  lblNewLabel_4.setBounds(0, 0, 1188, 671);
		  getContentPane().add(lblNewLabel_4);
		
		
		this.fillTable(new BookType());


	}
	/**
	 * 表单记录删除
	 * @param e
	 */
	private void BookTypeDelete(ActionEvent e) {
		String aa=id_textField.getText();
		if(StringUtil.isEmpty(aa)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int mark=JOptionPane.showConfirmDialog(null, "确认要删除记录吗");
		if(mark==0) {
			con=DBUtil.createConnection();
			try {
				boolean flag=BookDao.existBookByBookTypeId(con, aa);
				if(flag) {
					JOptionPane.showMessageDialog(null, "当前图书类别下有图书，不能删除");
					return;
				}
				int deletenum=BookTypeDao.delete(con, aa);
				if(deletenum==1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					reset();
					this.fillTable(new BookType());
				}else {
					JOptionPane.showMessageDialog(null, "失败，删除错误");
				}
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				DBUtil.closeConnection(con);
			}
		}
		
		
	}

	/**
	 * 表单记录修改
	 * @param e
	 */
	private  void BookTypeUpdate(ActionEvent evt) {
		String aa=id_textField.getText();
		String bb=update_textField.getText();
		String cc=update_textArea.getText();
		
		if(StringUtil.isEmpty(aa)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		BookType bt=new BookType(Integer.parseInt(aa),bb,cc);//id记得转型为int
		con=DBUtil.createConnection();
		try {
			int n=BookTypeDao.update(con, bt);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				this.reset();
				this.fillTable(new BookType());
				
			}else {
				JOptionPane.showMessageDialog(null, "失败，修改错误");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			DBUtil.closeConnection(con);
		}
		
	}

	/**
	 * 表格行点击事件处理
	 * @param e
	 */
	private void BookTypeMousePressed(MouseEvent evt) {
		int row =bt_table.getSelectedRow();
		//(转型)
		id_textField.setText((String)bt_table.getValueAt(row, 0));
		update_textField.setText((String)bt_table.getValueAt(row, 1));
		update_textArea.setText((String)bt_table.getValueAt(row, 2));
	}

	/**
	 * 图书类别查询处理
	 * @param evt
	 */
	private void bookTypeSearch(ActionEvent evt) {
		String aa=this.search_textField.getText();
		BookType bt=new BookType();
		bt.setBt_name(aa);
		this.fillTable(bt);
		
	}

	/**
	 * 初始化表格
	 * @param bt
	 */
	private void fillTable(BookType bt) {
		DefaultTableModel dtm=(DefaultTableModel)bt_table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection con=null;
		try {
			con=DBUtil.createConnection();
			ResultSet rs=BookTypeDao.list(con,bt);
			while(rs.next()) {
				Vector v=new Vector();
				v.add(rs.getString("bt_id"));
				v.add(rs.getString("bt_name"));
				v.add(rs.getString("bt_description"));
				
				dtm.addRow(v);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			DBUtil.closeConnection(con);
		}
		
	}
	/**
	 * 重置表单
	 */
	private void reset() {
		id_textField.setText("");
		update_textField.setText("");
		update_textArea.setText("");
	}
}
