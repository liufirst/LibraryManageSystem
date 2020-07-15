package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.util.StringUtil;

/**
 * 图书实体与数据库操作
 * 
 * @author 25833
 *
 */
public class BookDao {
	/**
	 * 图书添加
	 * 
	 * @param con
	 * @param b
	 * @return
	 * @throws SQLException
	 */
	public static int addBook(java.sql.Connection con, Book b) throws SQLException {
		String sql = "insert into book values(null,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		// 注意前面的数字，每个都对不对，别tm在跟上次是的，耽误半天功夫

		pst.setString(1, b.getB_name());
		pst.setString(2, b.getB_writer());
		pst.setString(3, b.getB_publisher());
		pst.setInt(4, b.getKind_id());

		pst.setString(5, b.getB_description());
		pst.setInt(6, 0);// 添加图书时，默认图书为在库 b_status 0-在库

		return pst.executeUpdate();
	}

	/**
	 * 图书查询
	 * 
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */

	public static ResultSet list(Connection con, Book book) throws Exception {
		StringBuffer sb = new StringBuffer(
				"select * from booktype bt,book b,status s where b.kind_id=bt.bt_id and b.b_status=s.status_id");
		if (StringUtil.isNotEmpty(book.getB_name())) {// 这里时isNOTempty！！！啊啊啊
			sb.append(" and b.b_name like '%" + book.getB_name() + "%'");
		}
		if (StringUtil.isNotEmpty(book.getB_writer())) {
			sb.append(" and b.b_writer like '%" + book.getB_writer() + "%'");
		}
		if (book.getKind_id() != null && book.getKind_id() != -1) {
			sb.append(" and b.kind_id like '%" + book.getKind_id() + "%'");// tnnd最后一行也得加%！！！
		}
		PreparedStatement pstm = con.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}

	/**
	 * 图书信息删除
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
	 * 图书信息修改
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
	 * 指定图书类别下是否存在图书
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
