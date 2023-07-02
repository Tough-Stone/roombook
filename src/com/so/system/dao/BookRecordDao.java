package com.so.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.utils.Page;
import org.apache.commons.utils.DateUtils;

import com.so.system.bean.BookRecord;
import com.so.utils.IdGenUtil;

/**
 * 预约记录DAO接口
 * @author so
 * @version V1.0
 */
public class BookRecordDao {

	
	/**
	 * 添加
	 * @param con
	 * @param bookRecord
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,BookRecord bookRecord)throws Exception{
		bookRecord.setId(IdGenUtil.getUUID());
		String sql="insert into book_record values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,bookRecord.getId());
		pstmt.setString(2,bookRecord.getTitle());
		pstmt.setString(3,bookRecord.getRoomNo());
		pstmt.setTimestamp(4,Timestamp.valueOf(DateUtils.formatDateTime(bookRecord.getBookTime())));
		pstmt.setTimestamp(5,Timestamp.valueOf(DateUtils.formatDateTime(bookRecord.getTimeQuantum())));
		pstmt.setString(6,bookRecord.getUseRemark());
		pstmt.setString(7,bookRecord.getAuditType());
		pstmt.setString(8,bookRecord.getBookUser());
		pstmt.setString(9,"0");//添加的时候默认是不打开签到的
		pstmt.setString(10,bookRecord.getMainUser());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 删除
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,String id)throws Exception{
		String sql="delete from book_record where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 更新
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,BookRecord bookRecord)throws Exception{
		String sql="update book_record set title=?,room_no=?,book_time=?,time_quantum=?,use_remark=?,audit_type=?,book_user=?,is_open=?,main_user=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(10,bookRecord.getId());
		pstmt.setString(1,bookRecord.getTitle());
		pstmt.setString(2,bookRecord.getRoomNo());
		pstmt.setTimestamp(3,Timestamp.valueOf(DateUtils.formatDateTime(bookRecord.getBookTime())));
		pstmt.setTimestamp(4,Timestamp.valueOf(DateUtils.formatDateTime(bookRecord.getTimeQuantum())));
		pstmt.setString(5,bookRecord.getUseRemark());
		pstmt.setString(6,bookRecord.getAuditType());
		pstmt.setString(7,bookRecord.getBookUser());
		pstmt.setString(8,bookRecord.getIsOpen());
		pstmt.setString(9,bookRecord.getMainUser());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param bookRecord
	 * @return
	 * @throws Exception
	 */
	public List<BookRecord> list(Connection con,BookRecord bookRecord,Page<BookRecord> page)throws Exception{
		List<BookRecord> list = new ArrayList<BookRecord>();
		BookRecord entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from book_record a where 1=1");
		//拼接分页条件
		String title =  bookRecord.getTitle();
		if(title != null && title !=""){
			sqlBuffer.append(" and a.title like '%"+title+"%'");
		}
		
		String roomNo =  bookRecord.getRoomNo();
		if(roomNo != null && roomNo !=""){
			sqlBuffer.append(" and a.room_no = '"+roomNo+"'");
		}
		
		/*String bookTime =  bookRecord.getBookTime();
		if(bookTime != null && bookTime !=""){
			sqlBuffer.append(" and a.book_time = '"+bookTime+"'");
		}
		
		String timeQuantum =  bookRecord.getTimeQuantum();
		if(timeQuantum != null && timeQuantum !=""){
			sqlBuffer.append(" and a.time_quantum = '"+timeQuantum+"'");
		}*/
		
		String auditType =  bookRecord.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and a.audit_type = '"+auditType+"'");
		}
		
		String bookUser =  bookRecord.getBookUser();
		if(bookUser != null && bookUser !=""){
			sqlBuffer.append(" and a.book_user = '"+bookUser+"'");
		}
		
		String sql=page.pageSql(sqlBuffer, page.getPageNo(), page.getPageSize());
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = getDictFormDb(rs);
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param bookRecord
	 * @return
	 * @throws Exception
	 */
	public List<BookRecord> findAll(Connection con,BookRecord bookRecord)throws Exception{
		List<BookRecord> list = new ArrayList<BookRecord>();
		BookRecord entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from book_record where 1=1");
		//拼接分页条件
		String title =  bookRecord.getTitle();
		if(title != null && title !=""){
			sqlBuffer.append(" and title = '"+title+"'");
		}
		
		String roomNo =  bookRecord.getRoomNo();
		if(roomNo != null && roomNo !=""){
			sqlBuffer.append(" and room_no = '"+roomNo+"'");
		}
		
		/*String bookTime =  bookRecord.getBookTime();
		if(bookTime != null && bookTime !=""){
			sqlBuffer.append(" and book_time = '"+bookTime+"'");
		}
		
		String timeQuantum =  bookRecord.getTimeQuantum();
		if(timeQuantum != null && timeQuantum !=""){
			sqlBuffer.append(" and time_quantum = '"+timeQuantum+"'");
		}*/
		
		String auditType =  bookRecord.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String bookUser =  bookRecord.getBookUser();
		if(bookUser != null && bookUser !=""){
			sqlBuffer.append(" and book_user = '"+bookUser+"'");
		}
		
		String sql=sqlBuffer.toString();
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = getDictFormDb(rs);
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * id查询
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BookRecord getById(Connection con,String id)throws Exception{
		BookRecord bookRecord=null;
		String sql="select * from book_record where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			bookRecord=getDictFormDb(rs);
		}
		return bookRecord;
	}
	
	//获取总数 分页使用
	public int count(Connection con,BookRecord bookRecord)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from book_record where 1=1");
		//拼接分页条件
		String title =  bookRecord.getTitle();
		if(title != null && title !=""){
			sqlBuffer.append(" and title = '"+title+"'");
		}
		
		String roomNo =  bookRecord.getRoomNo();
		if(roomNo != null && roomNo !=""){
			sqlBuffer.append(" and room_no = '"+roomNo+"'");
		}
		
		/*String bookTime =  bookRecord.getBookTime();
		if(bookTime != null && bookTime !=""){
			sqlBuffer.append(" and book_time = '"+bookTime+"'");
		}
		
		String timeQuantum =  bookRecord.getTimeQuantum();
		if(timeQuantum != null && timeQuantum !=""){
			sqlBuffer.append(" and time_quantum = '"+timeQuantum+"'");
		}*/
		
		String auditType =  bookRecord.getAuditType();
		if(auditType != null && auditType !=""){
			sqlBuffer.append(" and audit_type = '"+auditType+"'");
		}
		
		String bookUser =  bookRecord.getBookUser();
		if(bookUser != null && bookUser !=""){
			sqlBuffer.append(" and book_user = '"+bookUser+"'");
		}
		
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	private BookRecord getDictFormDb(ResultSet rs) throws SQLException{
		BookRecord bookRecord=new BookRecord();
		bookRecord.setId(rs.getString("id"));
		bookRecord.setTitle(rs.getString("title"));
		bookRecord.setRoomNo(rs.getString("room_no"));
		bookRecord.setBookTime(rs.getTimestamp("book_time"));
		bookRecord.setTimeQuantum(rs.getTimestamp("time_quantum"));
		bookRecord.setUseRemark(rs.getString("use_remark"));
		bookRecord.setAuditType(rs.getString("audit_type"));
		bookRecord.setBookUser(rs.getString("book_user"));
		bookRecord.setIsOpen(rs.getString("is_open"));
		bookRecord.setMainUser(rs.getString("main_user"));
		return bookRecord;
	}
	
	public boolean check(Connection con,BookRecord bookRecord)throws Exception{
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from book_record where 1=1 and audit_type = '1' ");
		String roomNo =  bookRecord.getRoomNo();
		if(roomNo != null && roomNo !=""){
			sqlBuffer.append(" and room_no = '"+roomNo+"'");
		}
		sqlBuffer.append(" and (( book_time BETWEEN '"+DateUtils.formatDateTime(bookRecord.getBookTime()) + "' and '"+ DateUtils.formatDateTime(bookRecord.getTimeQuantum())+"' ");
		sqlBuffer.append(" or time_quantum BETWEEN '"+DateUtils.formatDateTime(bookRecord.getBookTime()) + "' and '"+ DateUtils.formatDateTime(bookRecord.getTimeQuantum())+"' ) ");
		sqlBuffer.append(" or (book_time > '"+DateUtils.formatDateTime(bookRecord.getBookTime()) + "' and time_quantum <'"+ DateUtils.formatDateTime(bookRecord.getTimeQuantum())+"') ");
		sqlBuffer.append(" or (book_time < '"+DateUtils.formatDateTime(bookRecord.getBookTime()) + "' and time_quantum >'"+ DateUtils.formatDateTime(bookRecord.getTimeQuantum())+"')) ");
		String sql=sqlBuffer.toString();
		
		System.out.println(sql);
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
}