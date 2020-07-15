package com.liufirst.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.liufirst.dao.BookDao;
import com.liufirst.dao.BookTypeDao;
import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.util.DBUtil;
import com.liufirst.util.StringUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

public class AddBookInterFrm extends JInternalFrame {
	private JTextField b_name;
	private JTextField b_publisher;
	private JTextField b_writer;
	private JComboBox comboBox;
	private JTextArea b_description ;
	
	
	private Connection con=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookInterFrm frame = new AddBookInterFrm();
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
	public AddBookInterFrm() {
		setTitle("\u56FE\u4E66\u6DFB\u52A0");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel.setBounds(48, 45, 120, 40);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4F5C\u8005\uFF1A");
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel_1.setBounds(549, 45, 120, 40);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u51FA\u7248\u793E:");
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel_2.setBounds(48, 135, 120, 40);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("\u4ECB\u7ECD\uFF1A");
		lblNewLabel_4.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel_4.setBounds(48, 298, 120, 40);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u7C7B\u522B:");
		lblNewLabel_5.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel_5.setBounds(48, 214, 120, 40);
		getContentPane().add(lblNewLabel_5);
		
		 comboBox = new JComboBox();
		 comboBox.setFont(new Font("宋体", Font.BOLD, 25));
		comboBox.setBounds(166, 214, 250, 40);
		getContentPane().add(comboBox);
		
		b_name = new JTextField();
		b_name.setFont(new Font("楷体", Font.BOLD, 20));
		b_name.setBounds(166, 45, 250, 40);
		getContentPane().add(b_name);
		b_name.setColumns(10);
		
		b_publisher = new JTextField();
		b_publisher.setFont(new Font("楷体", Font.BOLD, 20));
		b_publisher.setBounds(166, 135, 250, 40);
		getContentPane().add(b_publisher);
		b_publisher.setColumns(10);
		
		b_writer = new JTextField();
		b_writer.setFont(new Font("楷体", Font.BOLD, 20));
		b_writer.setBounds(661, 50, 150, 40);
		getContentPane().add(b_writer);
		b_writer.setColumns(10);
		
		JButton ok = new JButton("\u6DFB\u52A0");
		ok.setIcon(new ImageIcon(AddBookInterFrm.class.getResource("/images/\u786E\u8BA4.png")));
		ok.setFont(new Font("楷体", Font.BOLD, 25));
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//添加事件
				addBook(e);
			}
		});
		ok.setBounds(210, 484, 133, 40);
		getContentPane().add(ok);
		
		JButton re = new JButton("\u91CD\u7F6E");
		re.setIcon(new ImageIcon(AddBookInterFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		re.setFont(new Font("楷体", Font.BOLD, 25));
		re.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//重置事件
				reset();
			}
		});
		re.setBounds(661, 484, 133, 40);
		getContentPane().add(re);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 305, 497, 159);
		getContentPane().add(scrollPane);
		 
		  b_description = new JTextArea();
		  scrollPane.setViewportView(b_description);
		  b_description.setFont(new Font("楷体", Font.BOLD, 20));
			b_description.setLineWrap(true);        //激活自动换行功能
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon(AddBookInterFrm.class.getResource("/images/\u5185\u90E8\u7A97\u53E3\u80CC\u666F.jpg")));
			lblNewLabel_3.setBounds(0, 0, 1000, 571);
			getContentPane().add(lblNewLabel_3);
		
		
		this.fillBooktype() ;
		
		

	}
	/**
	 * 确认添加图书方法
	 * 思路：各文本框不能为空---》文本框的取值判断--》实例化书本--》创建数据库连接--》添加图书（bookDao）--》关闭数据库连接
	 * @param e
	 */
				private void addBook(ActionEvent evt) {
					//获取文本框中的数据
					
					String aa=b_name.getText();
					String bb=b_writer.getText();
					String cc=b_publisher.getText();
					String dd=b_description.getText();
					
					
					//判断，文本框不能为空
					if(StringUtil.isEmpty(aa)) {
						JOptionPane.showMessageDialog(null, "图书名称不能为空");
						return;
					}else if(StringUtil.isEmpty(bb)) {
						JOptionPane.showMessageDialog(null, "作者不能为空");
						return;
					}else if(StringUtil.isEmpty(cc)) {
						JOptionPane.showMessageDialog(null, "出版社不能为空");
						return;
					}
					
					BookType bt=(BookType)comboBox.getSelectedItem();
					int bt_id=bt.getBt_id();
					
					//新建图书实例，创建数据库连接
					Book bk=new Book(aa,bb,cc,bt_id,dd);
					con=DBUtil.createConnection();
					
					try {
						int n=BookDao.addBook(con,bk);
						if(n==1) {
							JOptionPane.showMessageDialog(null, "添加成功");
							reset();
							
						}else {
							JOptionPane.showMessageDialog(null, "错误，添加失败");
						}
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "错误，添加失败，可能介绍字数过多");
					}finally {
						DBUtil.closeConnection(con);
						
					}
				}
			
				
				/**
				 * 重置文本框内容方法
				 * @param a
				 */
							public void reset() {
								// TODO Auto-generated method stub
								
								
								b_name.setText("");
								b_writer.setText("");
								b_description.setText("");
								b_publisher.setText("");
								
							}
							
							
							/**
							 * 初始化图书类别		
							 */
						private void fillBooktype() {
							BookType bt=null;
							
							try {
								con=DBUtil.createConnection();
								ResultSet rs=BookTypeDao.list(con, new BookType());
								while(rs.next()) {
									bt=new BookType();
									bt.setBt_id(rs.getInt("bt_id"));
									bt.setBt_name(rs.getString("bt_name"));
									
									this.comboBox.addItem(bt);
								}
								
							}catch(Exception ex) {
								ex.printStackTrace();
							}finally {
								
							}
						}
				
}
