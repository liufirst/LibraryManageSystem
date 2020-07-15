package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.util.StringUtil;

/**
 * ͼ��ʵ�������ݿ����
 * 
 * @author 25833
 *
 */
public class BookDao {
	/**
	 * ͼ�����
	 * 
	 * @param con
	 * @param b
	 * @return
	 * @throws SQLException
	 */
	public static int addBook(java.sql.Connection con, Book b) throws SQLException {
		String sql = "insert into book values(null,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		// ע��ǰ������֣�ÿ�����Բ��ԣ���tm�ڸ��ϴ��ǵģ�������칦��

		pst.setString(1, b.getB_name());
		pst.setString(2, b.getB_writer());
		pst.setString(3, b.getB_publisher());
		pst.setInt(4, b.getKind_id());

		pst.setString(5, b.getB_description());
		pst.setInt(6, 0);// ���ͼ��ʱ��Ĭ��ͼ��Ϊ�ڿ� b_status 0-�ڿ�

		return pst.executeUpdate();
	}

	/**
	 * ͼ���ѯ
	 * 
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */

	public static ResultSet list(Connection con, Book book) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select * from booktype bt,book b,status s where b.kind_id=bt.bt_id and b.b_status=s.status_id");
		if (StringUtil.isNotEmpty(book.getB_name())) {// ����ʱisNOTempty������������
			sb.append(" and b.b_name like '%" + book.getB_name() + "%'");
		}
		if (StringUtil.isNotEmpty(book.getB_writer())) {
			sb.append(" and b.b_writer like '%" + book.getB_writer() + "%'");
		}
		if (book.getKind_id() != null && book.getKind_id() != -1) {
			sb.append(" and b.kind_id like '%" + book.getKind_id() + "%'");// tnnd���һ��Ҳ�ü�%������
		}
		PreparedStatement pstm = con.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}

	/**
	 * ͼ����Ϣɾ��
	 * 
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static int delete(Connection con, int id) throws Exception {
		String sql = "delete from book where b_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
	}

	/**
	 * ͼ����Ϣ�޸�
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int update(Connection con, Book book) throws Exception {
		String sql = "update book set b_name=?,b_writer=?,b_publisher=?,kind_id=?,b_description=? where b_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book.getB_name());
		pstmt.setString(2, book.getB_writer());
		pstmt.setString(3, book.getB_publisher());
		pstmt.setInt(4, book.getKind_id());
		pstmt.setString(5, book.getB_description());
		pstmt.setInt(6, book.getB_id());

		return pstmt.executeUpdate();
	}

	/**
	 * ָ��ͼ��������Ƿ����ͼ��
	 * 
	 * @param con
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public static boolean existBookByBookTypeId(Connection con, String bookTypeId) throws Exception {
		String sql = "select * from book where kind_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}

}
