package com.liufirst.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class MainAdminFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { MainAdminFrm frame = new
	 * MainAdminFrm(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 **/
	/**
	 * Create the frame.
	 */
	public MainAdminFrm() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MainAdminFrm.class.getResource("/images/\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF.png")));
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDFV1.0\uFF08\u7BA1\u7406\u5458\uFF09");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 750);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("\u56FE\u4E66\u7C7B\u522B");
		mnNewMenu.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u56FE\u4E66\u7C7B\u522B.png")));
		mnNewMenu.setFont(new Font("楷体", Font.BOLD, 20));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("\u56FE\u4E66\u7C7B\u522B\u6DFB\u52A0");
		mntmNewMenuItem.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u6B21\u7EA7\u6DFB\u52A0.png")));
		mntmNewMenuItem.setFont(new Font("楷体", Font.BOLD, 15));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookTypeInterFrm abtif = new AddBookTypeInterFrm();
				abtif.setVisible(true);
				desktopPane.add(abtif);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u7C7B\u522B\u67E5\u8BE2\u4E0E\u66F4\u65B0");
		mntmNewMenuItem_3
				.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u6B21\u7EA7\u7BA1\u7406.png")));
		mntmNewMenuItem_3.setFont(new Font("楷体", Font.BOLD, 15));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 图书类别查询与更新
				BookTypeManageInterFrm btmif = new BookTypeManageInterFrm();
				btmif.setVisible(true);
				desktopPane.add(btmif);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_2 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		mnNewMenu_2.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u56FE\u4E66\u7BA1\u7406.png")));
		mnNewMenu_2.setFont(new Font("楷体", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u6DFB\u52A0\u56FE\u4E66");
		mntmNewMenuItem_4
				.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u6B21\u7EA7\u6DFB\u52A0.png")));
		mntmNewMenuItem_4.setFont(new Font("楷体", Font.BOLD, 15));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 图书管理-添加图书
				AddBookInterFrm abif = new AddBookInterFrm();
				abif.setVisible(true);
				desktopPane.add(abif);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u56FE\u4E66\u67E5\u8BE2\u548C\u66F4\u65B0");
		mntmNewMenuItem_5
				.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u6B21\u7EA7\u7BA1\u7406.png")));
		mntmNewMenuItem_5.setFont(new Font("楷体", Font.BOLD, 15));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 图书管理-图书查询与更新
				BookManageInterFrm bmif = new BookManageInterFrm();
				bmif.setVisible(true);
				desktopPane.add(bmif);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);

		JMenu mnNewMenu_3 = new JMenu("\u501F\u9605\u7BA1\u7406");
		mnNewMenu_3.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u501F\u9605\u7BA1\u7406.png")));
		mnNewMenu_3.setFont(new Font("楷体", Font.BOLD, 20));
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("\u56FE\u4E66\u501F\u9605");
		mntmNewMenuItem_6.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u501F\u9605.png")));
		mntmNewMenuItem_6.setFont(new Font("楷体", Font.BOLD, 15));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 图书借阅
				BorrowManageInterFrm bmif = new BorrowManageInterFrm();
				bmif.setVisible(true);
				desktopPane.add(bmif);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("\u56FE\u4E66\u5F52\u8FD8");
		mntmNewMenuItem_7.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u5F52\u8FD8.png")));
		mntmNewMenuItem_7.setFont(new Font("楷体", Font.BOLD, 15));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 借阅管理--归还图书
				ReturnInterFrm rif = new ReturnInterFrm();
				rif.setVisible(true);
				desktopPane.add(rif);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);

		JMenu mnNewMenu_4 = new JMenu("\u5173\u4E8E\u7CFB\u7EDF");
		mnNewMenu_4.setIcon(new ImageIcon(MainAdminFrm.class.getResource("/images/\u5173\u4E8E\u7CFB\u7EDF.png")));
		mnNewMenu_4.setFont(new Font("楷体", Font.BOLD, 20));
		menuBar.add(mnNewMenu_4);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("\u5236\u4F5C\u5C0F\u7EC4\u4ECB\u7ECD");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//制作小组介绍
				AboutSystemInterFrm asif = new AboutSystemInterFrm();
				asif.setVisible(true);
				desktopPane.add(asif);
			}
		});
		mntmNewMenuItem_8.setFont(new Font("楷体", Font.BOLD, 15));
		mnNewMenu_4.add(mntmNewMenuItem_8);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);// 主面板开启默认最大化
	}

}
