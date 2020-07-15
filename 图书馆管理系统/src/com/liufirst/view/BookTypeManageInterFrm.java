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
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel.setBounds(32, 30, 200, 30);
		
		search_textField = new JTextField();
		search_textField.setFont(new Font("����", Font.BOLD, 25));
		search_textField.setBounds(233, 30, 305, 30);
		search_textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u641C\u7D22.png")));
		btnNewButton.setFont(new Font("����", Font.BOLD, 25));
		btnNewButton.setBounds(949, 24, 150, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ͼ������ѯ
				bookTypeSearch(e);
			}
		});
		
		id_textField = new JTextField();
		id_textField.setFont(new Font("����", Font.BOLD, 25));
		id_textField.setBounds(194, 374, 102, 30);
		id_textField.setEditable(false);
		id_textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7C7B\u522B\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setBounds(32, 374, 150, 30);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 25));
		
		update_textField = new JTextField();
		update_textField.setFont(new Font("����", Font.BOLD, 25));
		update_textField.setBounds(480, 379, 200, 30);
		update_textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7C7B\u522B\u540D\u79F0");
		lblNewLabel_2.setBounds(340, 376, 150, 30);
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 25));
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
		btnNewButton_1.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u786E\u8BA4.png")));
		btnNewButton_1.setBounds(768, 516, 150, 50);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������--�޸�
				BookTypeUpdate(e);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 25));
		
		JButton btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u5220\u9664.png")));
		btnNewButton_2.setBounds(992, 516, 150, 50);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������--ɾ��
				BookTypeDelete(e);
			}
		});
		btnNewButton_2.setFont(new Font("����", Font.BOLD, 25));
		
		bt_table = new JTable();
		bt_table.setRowHeight(40);//�и�
		bt_table.getTableHeader().setReorderingAllowed(false);//��ͷ�����϶�
		bt_table.addMouseListener(new MouseAdapter() {
			@Override
			//������
			public void mousePressed(MouseEvent e) {
				BookTypeMousePressed(e);
			}
		});
		bt_table.setFont(new Font("����", Font.BOLD, 22));
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
	
		
		bt_table.setRowHeight(40);//�и�
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
		 lblNewLabel_3.setFont(new Font("����", Font.BOLD, 25));
		 
		 JScrollPane scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(188, 453, 492, 189);
		 getContentPane().add(scrollPane_1);
		 
		  update_textArea = new JTextArea();
		  update_textArea.setLineWrap(true);        //�����Զ����й���
		  scrollPane_1.setViewportView(update_textArea);
		  update_textArea.setFont(new Font("����", Font.BOLD, 20));
		  
		  JLabel lblNewLabel_4 = new JLabel("");
		  lblNewLabel_4.setIcon(new ImageIcon(BookTypeManageInterFrm.class.getResource("/images/\u5185\u90E8\u80CC\u666F3.jpg")));
		  lblNewLabel_4.setBounds(0, 0, 1188, 671);
		  getContentPane().add(lblNewLabel_4);
		
		
		this.fillTable(new BookType());


	}
	/**
	 * ����¼ɾ��
	 * @param e
	 */
	private void BookTypeDelete(ActionEvent e) {
		String aa=id_textField.getText();
		if(StringUtil.isEmpty(aa)) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ļ�¼");
			return;
		}
		int mark=JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ����¼��");
		if(mark==0) {
			con=DBUtil.createConnection();
			try {
				boolean flag=BookDao.existBookByBookTypeId(con, aa);
				if(flag) {
					JOptionPane.showMessageDialog(null, "��ǰͼ���������ͼ�飬����ɾ��");
					return;
				}
				int deletenum=BookTypeDao.delete(con, aa);
				if(deletenum==1) {
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					reset();
					this.fillTable(new BookType());
				}else {
					JOptionPane.showMessageDialog(null, "ʧ�ܣ�ɾ������");
				}
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				DBUtil.closeConnection(con);
			}
		}
		
		
	}

	/**
	 * ����¼�޸�
	 * @param e
	 */
	private  void BookTypeUpdate(ActionEvent evt) {
		String aa=id_textField.getText();
		String bb=update_textField.getText();
		String cc=update_textArea.getText();
		
		if(StringUtil.isEmpty(aa)) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵļ�¼");
			return;
		}
		BookType bt=new BookType(Integer.parseInt(aa),bb,cc);//id�ǵ�ת��Ϊint
		con=DBUtil.createConnection();
		try {
			int n=BookTypeDao.update(con, bt);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				this.reset();
				this.fillTable(new BookType());
				
			}else {
				JOptionPane.showMessageDialog(null, "ʧ�ܣ��޸Ĵ���");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			DBUtil.closeConnection(con);
		}
		
	}

	/**
	 * ����е���¼�����
	 * @param e
	 */
	private void BookTypeMousePressed(MouseEvent evt) {
		int row =bt_table.getSelectedRow();
		//(ת��)
		id_textField.setText((String)bt_table.getValueAt(row, 0));
		update_textField.setText((String)bt_table.getValueAt(row, 1));
		update_textArea.setText((String)bt_table.getValueAt(row, 2));
	}

	/**
	 * ͼ������ѯ����
	 * @param evt
	 */
	private void bookTypeSearch(ActionEvent evt) {
		String aa=this.search_textField.getText();
		BookType bt=new BookType();
		bt.setBt_name(aa);
		this.fillTable(bt);
		
	}

	/**
	 * ��ʼ�����
	 * @param bt
	 */
	private void fillTable(BookType bt) {
		DefaultTableModel dtm=(DefaultTableModel)bt_table.getModel();
		dtm.setRowCount(0);//���ó�0��
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
	 * ���ñ�
	 */
	private void reset() {
		id_textField.setText("");
		update_textField.setText("");
		update_textArea.setText("");
	}
}
