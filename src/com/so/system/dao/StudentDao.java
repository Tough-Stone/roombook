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

import com.so.system.bean.Student;
import com.so.utils.IdGenUtil;

/**
 * 学生管理DAO接口
 * @author so
 * @version V1.0
 */
public class StudentDao {

	
	/**
	 * 添加
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,Student student)throws Exception{
		student.setId(IdGenUtil.getUUID());
		String sql="insert into db_student values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,student.getId());
		pstmt.setString(2,student.getStudentNo());
		pstmt.setString(3,student.getStudentName());
		pstmt.setString(4,student.getSex());
		pstmt.setString(5,student.getCollegeRoom());
		pstmt.setString(6,student.getMajor());
		pstmt.setString(7,student.getEmail());
		pstmt.setString(8,student.getTel());
		pstmt.setString(9,student.getContent());
		pstmt.setString(10,"0");
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
		String sql="delete from db_student where id=?";
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
	public int update(Connection con,Student student)throws Exception{
		String sql="update db_student set student_no=?,student_name=?,sex=?,college_room=?,major=?,email=?,tel=?,content=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(9,student.getId());
		pstmt.setString(1,student.getStudentNo());
		pstmt.setString(2,student.getStudentName());
		pstmt.setString(3,student.getSex());
		pstmt.setString(4,student.getCollegeRoom());
		pstmt.setString(5,student.getMajor());
		pstmt.setString(6,student.getEmail());
		pstmt.setString(7,student.getTel());
		pstmt.setString(8,student.getContent());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public List<Student> list(Connection con,Student student,Page<Student> page)throws Exception{
		List<Student> list = new ArrayList<Student>();
		Student entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_student a where 1=1");
		//拼接分页条件
		String studentNo =  student.getStudentNo();
		if(studentNo != null && studentNo !=""){
			sqlBuffer.append(" and a.student_no = '"+studentNo+"'");
		}
		
		String studentName =  student.getStudentName();
		if(studentName != null && studentName !=""){
			sqlBuffer.append(" and a.student_name like '%"+studentName+"%'");
		}
		
		String sex =  student.getSex();
		if(sex != null && sex !=""){
			sqlBuffer.append(" and a.sex = '"+sex+"'");
		}
		
		String collegeRoom =  student.getCollegeRoom();
		if(collegeRoom != null && collegeRoom !=""){
			sqlBuffer.append(" and a.college_room like '%"+collegeRoom+"%'");
		}
		
		String major =  student.getMajor();
		if(major != null && major !=""){
			sqlBuffer.append(" and a.major like '%"+major+"%'");
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
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public List<Student> findAll(Connection con,Student student)throws Exception{
		List<Student> list = new ArrayList<Student>();
		Student entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_student where 1=1");
		//拼接分页条件
		String studentNo =  student.getStudentNo();
		if(studentNo != null && studentNo !=""){
			sqlBuffer.append(" and student_no = '"+studentNo+"'");
		}
		
		String studentName =  student.getStudentName();
		if(studentName != null && studentName !=""){
			sqlBuffer.append(" and student_name = '"+studentName+"'");
		}
		
		String sex =  student.getSex();
		if(sex != null && sex !=""){
			sqlBuffer.append(" and sex = '"+sex+"'");
		}
		
		String collegeRoom =  student.getCollegeRoom();
		if(collegeRoom != null && collegeRoom !=""){
			sqlBuffer.append(" and college_room = '"+collegeRoom+"'");
		}
		
		String major =  student.getMajor();
		if(major != null && major !=""){
			sqlBuffer.append(" and major = '"+major+"'");
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
	public Student getById(Connection con,String id)throws Exception{
		Student student=null;
		String sql="select * from db_student where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			student=getDictFormDb(rs);
		}
		return student;
	}
	
	//获取总数 分页使用
	public int count(Connection con,Student student)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_student where 1=1");
		//拼接分页条件
		String studentNo =  student.getStudentNo();
		if(studentNo != null && studentNo !=""){
			sqlBuffer.append(" and student_no = '"+studentNo+"'");
		}
		
		String studentName =  student.getStudentName();
		if(studentName != null && studentName !=""){
			sqlBuffer.append(" and student_name = '"+studentName+"'");
		}
		
		String sex =  student.getSex();
		if(sex != null && sex !=""){
			sqlBuffer.append(" and sex = '"+sex+"'");
		}
		
		String collegeRoom =  student.getCollegeRoom();
		if(collegeRoom != null && collegeRoom !=""){
			sqlBuffer.append(" and college_room = '"+collegeRoom+"'");
		}
		
		String major =  student.getMajor();
		if(major != null && major !=""){
			sqlBuffer.append(" and major = '"+major+"'");
		}
		
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
	
	private Student getDictFormDb(ResultSet rs) throws SQLException{
		Student student=new Student();
		student.setId(rs.getString("id"));
		student.setStudentNo(rs.getString("student_no"));
		student.setStudentName(rs.getString("student_name"));
		student.setSex(rs.getString("sex"));
		student.setCollegeRoom(rs.getString("college_room"));
		student.setMajor(rs.getString("major"));
		student.setEmail(rs.getString("email"));
		student.setTel(rs.getString("tel"));
		student.setContent(rs.getString("content"));
		return student;
	}
	
}