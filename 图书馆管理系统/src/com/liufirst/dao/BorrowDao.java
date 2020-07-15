package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liufirst.model.Book;
import com.liufirst.model.Borrow;
import com.liufirst.model.Student;
import com.liufirst.util.StringUtil;

/**
 * 借阅表与数据库操作
 * 
 * @author 25833
 *
 */
public class BorrowDao {
	/**
	 * 图书借阅--图书查询
	 * 
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */

	public static ResultSet list(Connection con, Book book) throws Exception {
		// 在借阅面，只搜索在库的，在归还界面，只搜索借阅状态的
		StringBuffer sb = new StringBuffer(
				"select * from book b,status s where  b.b_status=s.status_id and b.b_status like '%0%' ");//
		if (StringUtil.isNotEmpty(book.getB_name())) {// 这里时isNOTempty！！！啊啊啊
			sb.append(" and b.b_name like '%" + book.getB_name() + "%'");
		}
		if (StringUtil.isNotEmpty(book.getB_writer())) {
			sb.append(" and b.b_writer like '%" + book.getB_writer() + "%'");
		}

		PreparedStatement pstm = con.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}

	/**
	 * 图书借阅--学生查询
	 * 
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */

	public static ResultSet list(Connection con, Student s) throws Exception {
		StringBuffer sb = new StringBuffer("select*from student");
		if (StringUtil.isNotEmpty(s.getS_id())) {// 操他妈的！！这里时isNOTempty！！！啊啊啊
			sb.append(" and s_id like '%" + s.getS_id() + "%'");
		}
		if (StringUtil.isNotEmpty(s.getS_name())) {
			sb.append(" and s_name like '%" + s.getS_name() + "%'");
		}
		PreparedStatement pstm = con.prepareStatement(sb.toString().replaceFirst("and", "where"));//
		return pstm.executeQuery();
	}

	/**
	 * 添加借阅对象
	 * 
	 * @param con
	 * @param bo
	 * @return
	 * @throws Exception
	 */
	public static int addBorrow(Connection con, Borrow bo) throws Exception {
		String sql = "insert into borrow values(null,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		// 注意前面的数字，每个都对不对，别tm在跟上次是的，耽误半天功夫

		pst.setInt(1, bo.getBo_bookid());
		pst.setString(2, bo.getBo_studentid());
		pst.setString(3,"借阅");

		return pst.executeUpdate();
	}

	/**
	 * 更新图书状态为借阅，不是单独使用，是在借阅对象生成方法里配合使用
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int Update_1(Connection con, Book book) throws Exception {

		String sql = "update book set b_status=? where b_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, 1);
		pstmt.setInt(2, book.getB_id());

		return pstmt.executeUpdate();
	}

	/**
	 * 更新图书状态为在库，不是单独使用，配合使用
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int Update_0(Connection con, Book book) throws Exception {

		String sql = "update book set b_status=? where b_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, 0);
		pstmt.setInt(2, book.getB_id());

		return pstmt.executeUpdate();
	}
	
	/**
	 * 归还图书--查询 borrow 表单
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static ResultSet search(Connection con, Borrow borrow) throws Exception {
		/**
		 * 原版本
		 *
		 * StringBuffer sb=new StringBuffer("select * from borrow bo,book b,student
		 * stu,status s where bo.book_id=b.b_id and b.b_status=s.status_id and
		 * bo.student_id=stu.s_id"); if(StringUtil.isNotEmpty(new
		 * Integer(borrow.getBo_bookid()).toString())) {//这里时isNOTempty！！！啊啊啊
		 * sb.append(" and b.b_name like '%"+borrow.getBo_bookid()+"%'"); }
		 * if(StringUtil.isNotEmpty(borrow.getBo_studentid())) { sb.append(" and
		 * stu.s_id like '%"+borrow.getBo_studentid()+"%'"); PreparedStatement
		 * pstm=con.prepareStatement(sb.toString()); return pstm.executeQuery(); }
		 **/

		// 第一次测试，是否能初始化表单
		StringBuffer sb = new StringBuffer(
				"select * from borrow bo,student stu,book b,status sta where bo.student_id=stu.s_id and bo.book_id=b.b_id and b_status=sta.status_id ");
		if (StringUtil.isNotEmpty(borrow.getBo_bookname())) {// 这里时isNOTempty！！！啊啊啊
			sb.append(" and b.b_name like '%" + borrow.getBo_bookname() + "%'");
		}
		if (StringUtil.isNotEmpty(borrow.getBo_studentid())) {
			sb.append(" and stu.s_id like '%" + borrow.getBo_studentid() + "%'");
		}
		PreparedStatement pstm = con.prepareStatement(sb.toString());

		return pstm.executeQuery();

	}
	
	/**
	 * 更新borrow表中，borrow_kind为归还，不是单独使用，配合使用
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int Update_return(Connection con, Borrow borrow) throws Exception {

		String sql = "update borrow set borrow_kind=? where borrow_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "归还");
		pstmt.setInt(2, borrow.getBo_id());

		return pstmt.executeUpdate();
	}
	
	
}
