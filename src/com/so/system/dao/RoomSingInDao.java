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

import com.so.system.bean.RoomSingIn;
import com.so.utils.IdGenUtil;

/**
 * 签到记录DAO接口
 * @author so
 * @version V1.0
 */
public class RoomSingInDao {

	
	/**
	 * 添加
	 * @param con
	 * @param roomSingIn
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,RoomSingIn roomSingIn)throws Exception{
		roomSingIn.setId(IdGenUtil.getUUID());
		String sql="insert into room_sing_in values(?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,roomSingIn.getId());
		pstmt.setString(2,roomSingIn.getStudentId());
		pstmt.setString(3,roomSingIn.getSignTime());
		pstmt.setString(4,roomSingIn.getRoomBookId());
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
		String sql="delete from room_sing_in where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 更新
	 * @param con
	 * @param
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,RoomSingIn roomSingIn)throws Exception{
		String sql="update room_sing_in set student_id=?,sign_time=?,room_book_id=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(4,roomSingIn.getId());
		pstmt.setString(1,roomSingIn.getStudentId());
		pstmt.setString(2,roomSingIn.getSignTime());
		pstmt.setString(3,roomSingIn.getRoomBookId());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param roomSingIn
	 * @return
	 * @throws Exception
	 */
	public List<RoomSingIn> list(Connection con,RoomSingIn roomSingIn,Page<RoomSingIn> page)throws Exception{
		List<RoomSingIn> list = new ArrayList<RoomSingIn>();
		RoomSingIn entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from room_sing_in a where 1=1");
		//拼接分页条件
		String studentId =  roomSingIn.getStudentId();
		if(studentId != null && studentId !=""){
			sqlBuffer.append(" and a.student_id = '"+studentId+"'");
		}
		
		String roomBookId =  roomSingIn.getRoomBookId();
		if(roomBookId != null && roomBookId !=""){
			sqlBuffer.append(" and a.room_book_id = '"+roomBookId+"'");
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
	 * @param roomSingIn
	 * @return
	 * @throws Exception
	 */
	public List<RoomSingIn> findAll(Connection con,RoomSingIn roomSingIn)throws Exception{
		List<RoomSingIn> list = new ArrayList<RoomSingIn>();
		RoomSingIn entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from room_sing_in where 1=1");
		//拼接分页条件
		String studentId =  roomSingIn.getStudentId();
		if(studentId != null && studentId !=""){
			sqlBuffer.append(" and student_id = '"+studentId+"'");
		}
		
		String roomBookId =  roomSingIn.getRoomBookId();
		if(roomBookId != null && roomBookId !=""){
			sqlBuffer.append(" and room_book_id = '"+roomBookId+"'");
		}
		
		String sql=sqlBuffer.toString();
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
	public RoomSingIn getById(Connection con,String id)throws Exception{
		RoomSingIn roomSingIn=null;
		String sql="select * from room_sing_in where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			roomSingIn=getDictFormDb(rs);
		}
		return roomSingIn;
	}
	
	//获取总数 分页使用
	public int count(Connection con,RoomSingIn roomSingIn)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from room_sing_in where 1=1");
		//拼接分页条件
		String studentId =  roomSingIn.getStudentId();
		if(studentId != null && studentId !=""){
			sqlBuffer.append(" and student_id = '"+studentId+"'");
		}
		
		String roomBookId =  roomSingIn.getRoomBookId();
		if(roomBookId != null && roomBookId !=""){
			sqlBuffer.append(" and room_book_id = '"+roomBookId+"'");
		}
		
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	private RoomSingIn getDictFormDb(ResultSet rs) throws SQLException{
		RoomSingIn roomSingIn=new RoomSingIn();
		roomSingIn.setId(rs.getString("id"));
		roomSingIn.setStudentId(rs.getString("student_id"));
		roomSingIn.setSignTime(rs.getString("sign_time"));
		roomSingIn.setRoomBookId(rs.getString("room_book_id"));
		return roomSingIn;
	}

	public String selectStudentByCardId(Connection con,String cardId)throws Exception{
		String sql="select id from db_student where cardnumber = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, cardId);
		ResultSet rs=pstmt.executeQuery();
        String id="";
        while(rs.next()){
            id = rs.getString("id");
        }
        return id;
	}
}