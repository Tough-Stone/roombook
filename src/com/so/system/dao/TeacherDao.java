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

import com.so.system.bean.Teacher;
import com.so.utils.IdGenUtil;

/**
 * 教师管理DAO接口
 * @author so
 * @version V1.0
 */
public class TeacherDao {

	
	/**
	 * 添加
	 * @param con
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Teacher teacher)throws Exception{
		teacher.setId(IdGenUtil.getUUID());
		String sql="insert into db_teacher values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,teacher.getId());
		pstmt.setString(2,teacher.getTeacherNo());
		pstmt.setString(3,teacher.getTeacherName());
		pstmt.setString(4,teacher.getTeaTitle());
		pstmt.setString(5,teacher.getSex());
		pstmt.setString(6,teacher.getTel());
		pstmt.setString(7,teacher.getContent());
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
		String sql="delete from db_teacher where id=?";
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
	public int update(Connection con,Teacher teacher)throws Exception{
		String sql="update db_teacher set teacher_no=?,teacher_name=?,tea_title=?,sex=?,tel=?,content=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(7,teacher.getId());
		pstmt.setString(1,teacher.getTeacherNo());
		pstmt.setString(2,teacher.getTeacherName());
		pstmt.setString(3,teacher.getTeaTitle());
		pstmt.setString(4,teacher.getSex());
		pstmt.setString(5,teacher.getTel());
		pstmt.setString(6,teacher.getContent());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public List<Teacher> list(Connection con,Teacher teacher,Page<Teacher> page)throws Exception{
		List<Teacher> list = new ArrayList<Teacher>();
		Teacher entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_teacher a where 1=1");
		//拼接分页条件
		String teacherNo =  teacher.getTeacherNo();
		if(teacherNo != null && teacherNo !=""){
			sqlBuffer.append(" and a.teacher_no = '"+teacherNo+"'");
		}
		
		String teacherName =  teacher.getTeacherName();
		if(teacherName != null && teacherName !=""){
			sqlBuffer.append(" and a.teacher_name like '%"+teacherName+"%'");
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
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	public List<Teacher> findAll(Connection con,Teacher teacher)throws Exception{
		List<Teacher> list = new ArrayList<Teacher>();
		Teacher entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_teacher where 1=1");
		//拼接分页条件
		String teacherNo =  teacher.getTeacherNo();
		if(teacherNo != null && teacherNo !=""){
			sqlBuffer.append(" and teacher_no = '"+teacherNo+"'");
		}
		
		String teacherName =  teacher.getTeacherName();
		if(teacherName != null && teacherName !=""){
			sqlBuffer.append(" and teacher_name = '"+teacherName+"'");
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
	public Teacher getById(Connection con,String id)throws Exception{
		Teacher teacher=null;
		String sql="select * from db_teacher where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			teacher=getDictFormDb(rs);
		}
		return teacher;
	}
	
	//获取总数 分页使用
	public int count(Connection con,Teacher teacher)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_teacher where 1=1");
		//拼接分页条件
		String teacherNo =  teacher.getTeacherNo();
		if(teacherNo != null && teacherNo !=""){
			sqlBuffer.append(" and teacher_no = '"+teacherNo+"'");
		}
		
		String teacherName =  teacher.getTeacherName();
		if(teacherName != null && teacherName !=""){
			sqlBuffer.append(" and teacher_name = '"+teacherName+"'");
		}
		
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	private Teacher getDictFormDb(ResultSet rs) throws SQLException{
		Teacher teacher=new Teacher();
		teacher.setId(rs.getString("id"));
		teacher.setTeacherNo(rs.getString("teacher_no"));
		teacher.setTeacherName(rs.getString("teacher_name"));
		teacher.setTeaTitle(rs.getString("tea_title"));
		teacher.setSex(rs.getString("sex"));
		teacher.setTel(rs.getString("tel"));
		teacher.setContent(rs.getString("content"));
		return teacher;
	}
	
}