package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liufirst.model.Book;
import com.liufirst.model.Borrow;
import com.liufirst.model.Student;
import com.liufirst.util.StringUtil;

/**
 * ���ı������ݿ����
 * 
 * @author 25833
 *
 */
public class BorrowDao {
	/**
	 * ͼ�����--ͼ���ѯ
	 * 
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */

	public static ResultSet list(Connection con, Book book) throws Exception {
		// �ڽ����棬ֻ�����ڿ�ģ��ڹ黹���棬ֻ��������״̬��
		StringBuffer sb = new StringBuffer(
				"select * from book b,status s where  b.b_status=s.status_id and b.b_status like '%0%' ");//
		if (StringUtil.isNotEmpty(book.getB_name())) {// ����ʱisNOTempty������������
			sb.append(" and b.b_name like '%" + book.getB_name() + "%'");
		}
		if (StringUtil.isNotEmpty(book.getB_writer())) {
			sb.append(" and b.b_writer like '%" + book.getB_writer() + "%'");
		}

		PreparedStatement pstm = con.prepareStatement(sb.toString());
		return pstm.executeQuery();
	}

	/**
	 * ͼ�����--ѧ����ѯ
	 * 
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */

	public static ResultSet list(Connection con, Student s) throws Exception {
		StringBuffer sb = new StringBuffer("select*from student");
		if (StringUtil.isNotEmpty(s.getS_id())) {// ������ģ�������ʱisNOTempty������������
			sb.append(" and s_id like '%" + s.getS_id() + "%'");
		}
		if (StringUtil.isNotEmpty(s.getS_name())) {
			sb.append(" and s_name like '%" + s.getS_name() + "%'");
		}
		PreparedStatement pstm = con.prepareStatement(sb.toString().replaceFirst("and", "where"));//
		return pstm.executeQuery();
	}

	/**
	 * ��ӽ��Ķ���
	 * 
	 * @param con
	 * @param bo
	 * @return
	 * @throws Exception
	 */
	public static int addBorrow(Connection con, Borrow bo) throws Exception {
		String sql = "insert into borrow values(null,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		// ע��ǰ������֣�ÿ�����Բ��ԣ���tm�ڸ��ϴ��ǵģ�������칦��

		pst.setInt(1, bo.getBo_bookid());
		pst.setString(2, bo.getBo_studentid());
		pst.setString(3,"����");

		return pst.executeUpdate();
	}

	/**
	 * ����ͼ��״̬Ϊ���ģ����ǵ���ʹ�ã����ڽ��Ķ������ɷ��������ʹ��
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
	 * ����ͼ��״̬Ϊ�ڿ⣬���ǵ���ʹ�ã����ʹ��
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
	 * �黹ͼ��--��ѯ borrow ��
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static ResultSet search(Connection con, Borrow borrow) throws Exception {
		/**
		 * ԭ�汾
		 *
		 * StringBuffer sb=new StringBuffer("select * from borrow bo,book b,student
		 * stu,status s where bo.book_id=b.b_id and b.b_status=s.status_id and
		 * bo.student_id=stu.s_id"); if(StringUtil.isNotEmpty(new
		 * Integer(borrow.getBo_bookid()).toString())) {//����ʱisNOTempty������������
		 * sb.append(" and b.b_name like '%"+borrow.getBo_bookid()+"%'"); }
		 * if(StringUtil.isNotEmpty(borrow.getBo_studentid())) { sb.append(" and
		 * stu.s_id like '%"+borrow.getBo_studentid()+"%'"); PreparedStatement
		 * pstm=con.prepareStatement(sb.toString()); return pstm.executeQuery(); }
		 **/

		// ��һ�β��ԣ��Ƿ��ܳ�ʼ����
		StringBuffer sb = new StringBuffer(
				"select * from borrow bo,student stu,book b,status sta where bo.student_id=stu.s_id and bo.book_id=b.b_id and b_status=sta.status_id ");
		if (StringUtil.isNotEmpty(borrow.getBo_bookname())) {// ����ʱisNOTempty������������
			sb.append(" and b.b_name like '%" + borrow.getBo_bookname() + "%'");
		}
		if (StringUtil.isNotEmpty(borrow.getBo_studentid())) {
			sb.append(" and stu.s_id like '%" + borrow.getBo_studentid() + "%'");
		}
		PreparedStatement pstm = con.prepareStatement(sb.toString());

		return pstm.executeQuery();

	}
	
	/**
	 * ����borrow���У�borrow_kindΪ�黹�����ǵ���ʹ�ã����ʹ��
	 * 
	 * @param con
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public static int Update_return(Connection con, Borrow borrow) throws Exception {

		String sql = "update borrow set borrow_kind=? where borrow_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "�黹");
		pstmt.setInt(2, borrow.getBo_id());

		return pstmt.executeUpdate();
	}
	
	
}
