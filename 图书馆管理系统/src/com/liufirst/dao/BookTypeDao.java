package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liufirst.model.Book;
import com.liufirst.model.BookType;
import com.liufirst.util.StringUtil;

/**
 * 图书类别与数据库操作
 * @author 25833
 *
 */
public class BookTypeDao {
	/**
	 * 图书类别添加
	 * @param con
	 * @param bt
	 * @return
	 * @throws SQLException
	 */
	public static int addBookType(java.sql.Connection con,BookType bt)throws SQLException{
		String sql="insert into booktype values(null,?,?)";//图书类别id在数据库里设置的是自增的，所以是null
		PreparedStatement pst=con.prepareStatement(sql);
		//注意前面的数字，每个都对不对，别tm在跟上次是的，耽误半天功夫
	    pst.setString(1, bt.getBt_name());
	    pst.setString(2, bt.getBt_description());
	  

		return pst.executeUpdate();
	}
	/**
	 * 图书类别查询
	 * @param con
	 * @param bt
	 * @return
	 */
	public static ResultSet list(Connection con,BookType bt)throws Exception {
		StringBuffer sb=new StringBuffer("select*from booktype");
		if(StringUtil.isNotEmpty(bt.getBt_name())) {//操他妈的！！这里时isNOTempty！！！啊啊啊
			sb.append(" and bt_name like '%"+bt.getBt_name()+"%'");
		}
		PreparedStatement pstm=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstm.executeQuery();
	}
	/**
	 * 图书类别删除
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
	 * 更新图书类别
	 * @param con
	 * @param bt
	 * @return
	 * @throws Exception
	 */
	public static int update(Connection con,BookType bt)throws Exception{
		String sql="update booktype set bt_name=?,bt_description=? where bt_id=?";//注意是set
		PreparedStatement pstm=con.prepareStatement(sql);
		
		pstm.setString(1,bt.getBt_name() );
		pstm.setString(2,bt.getBt_description() );
		pstm.setInt(3,bt.getBt_id() );
		
		return pstm.executeUpdate();
		
	}
	
}
