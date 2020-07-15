package com.liufirst.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class AboutSystemInterFrm extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutSystemInterFrm frame = new AboutSystemInterFrm();
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
	public AboutSystemInterFrm() {
		setClosable(true);
		setIconifiable(true);
		setTitle("\u5236\u4F5C\u5C0F\u7EC4\u4ECB\u7ECD");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5915\u9633\u7EA2\u56E2\u961F");
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 25));
		lblNewLabel.setBounds(436, 37, 152, 85);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 388, 535, 187);
		getContentPane().add(scrollPane);
		
		JTextArea txtrqqmis = new JTextArea();
		scrollPane.setViewportView(txtrqqmis);
		txtrqqmis.setFont(new Font("楷体", Font.BOLD, 25));
		txtrqqmis.setLineWrap(true);        //激活自动换行功能
		txtrqqmis.setText("\u672C\u7CFB\u7EDF\u53EF\u4EE5\u6EE1\u8DB3\u56FE\u4E66\u9986\u7BA1\u7406\u7684\u4E00\u4E9B\u57FA\u672C\u9700\u6C42\uFF0C\u4ECD\u7136\u5B58\u5728\u4E00\u4E9B\u95EE\u9898\uFF0C\u5982\u679C\u5728\u4F7F\u7528\u8FC7\u7A0B\u4E2D\u9047\u5230\u95EE\u9898\uFF0C\u8BF7\u8054\u7CFBQQ\u7FA4\uFF1A715097568  \uFF08MIS\u517C\u6E38\u620F\u7EC4\u961F\u5403\u74DC\u7FA4\uFF09");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(217, 139, 529, 199);
		getContentPane().add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setFont(new Font("楷体", Font.BOLD, 20));
		textArea.setEditable(false);
		textArea.setText("\u4F55\u4E2D\u8C6A\u3001\u5218\u8FEA\u794E\u3001\u4EFB\u5C11\u6770\u3001\u5F90\u536B\u6770\u3001\u6768\u4FCA\u6D69\u3001");
		textArea.setLineWrap(true);

	}
}
