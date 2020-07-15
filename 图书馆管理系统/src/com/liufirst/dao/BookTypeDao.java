package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.util.StringUtil;

/**
 * ͼ����������ݿ����
 * @author 25833
 *
 */
public class BookTypeDao {
	/**
	 * ͼ��������
	 * @param con
	 * @param bt
	 * @return
	 * @throws SQLException
	 */
	public static int addBookType(java.sql.Connection con,BookType bt)throws SQLException{
		String sql="insert into booktype values(null,?,?)";//ͼ�����id�����ݿ������õ��������ģ�������null
		PreparedStatement pst=con.prepareStatement(sql);
		//ע��ǰ������֣�ÿ�����Բ��ԣ���tm�ڸ��ϴ��ǵģ�������칦��
	    pst.setString(1, bt.getBt_name());
	    pst.setString(2, bt.getBt_description());
	  

		return pst.executeUpdate();
	}
	/**
	 * ͼ������ѯ
	 * @param con
	 * @param bt
	 * @return
	 */
	public static ResultSet list(Connection con,BookType bt)throws Exception {
		StringBuffer sb=new StringBuffer("select*from booktype");
		if(StringUtil.isNotEmpty(bt.getBt_name())) {//������ģ�������ʱisNOTempty������������
			sb.append(" and bt_name like '%"+bt.getBt_name()+"%'");
		}
		PreparedStatement pstm=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstm.executeQuery();
	}
	/**
	 * ͼ�����ɾ��
	 * @param con
	 * @param bt_id
	 * @return
	 * @throws Exception
	 */
	public static int delete(Connection con,String bt_id)throws Exception {
		String sql="delete from booktype where bt_id=?";
		PreparedStatement pstm=con.prepareStatement(sql);
		
		pstm.setString(1, bt_id);
		return pstm.executeUpdate();
		
	}
	/**
	 * ����ͼ�����
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */
	public static int update(Connection con,BookType bt)throws Exception{
		String sql="update booktype set bt_name=?,bt_description=? where bt_id=?";//ע����set
		PreparedStatement pstm=con.prepareStatement(sql);
		
		pstm.setString(1,bt.getBt_name() );
		pstm.setString(2,bt.getBt_description() );
		pstm.setInt(3,bt.getBt_id() );
		
		return pstm.executeUpdate();
		
	}
	
}
