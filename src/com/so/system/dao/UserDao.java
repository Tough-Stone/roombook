package com.so.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.utils.Page;

import com.so.system.bean.User;

/**
 * 系统用户DAO接口
 * @author so
 * @version V1.0
 */
public class UserDao {

	
	/**
	 * 添加
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,User user)throws Exception{
		if (StringUtils.isEmpty(user.getId())) {
			user.setId(UUID.randomUUID().toString().replace("-", ""));
		}
		String sql="insert into db_user values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getId());
		pstmt.setString(2,user.getUsername());
		pstmt.setString(3,user.getPassword());
		pstmt.setString(4,user.getName());
		pstmt.setString(5,user.getSex());
		pstmt.setString(6,user.getPhone());
		pstmt.setString(7, user.getRole());
		pstmt.setString(8, user.getContent());
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
		String sql="delete from db_user where id=?";
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
	public int update(Connection con,User user)throws Exception{
		String sql="update db_user set username=?,password=?,name=?,sex=?,phone=?,role=?,content=? where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(8,user.getId());
		pstmt.setString(1,user.getUsername());
		pstmt.setString(2,user.getPassword());
		pstmt.setString(3,user.getName());
		pstmt.setString(4,user.getSex());
		pstmt.setString(5,user.getPhone());
		pstmt.setString(6, user.getRole());
		pstmt.setString(7, user.getContent());
		return pstmt.executeUpdate();
	}
	
	
	/**
	 * 分页查询
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> list(Connection con,User user,Page<User> page)throws Exception{
		List<User> list = new ArrayList<User>();
		User entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_user where 1=1");
		//拼接分页条件
			String username =  user.getUsername();
			if(username != null && username !=""){
				sqlBuffer.append(" and username = '"+username+"'");
			}
			
			String name =  user.getName();
			if(name != null && name !=""){
				sqlBuffer.append(" and name = '"+name+"'");
			}
			
			String sex =  user.getSex();
			if(sex != null && sex !=""){
				sqlBuffer.append(" and sex = '"+sex+"'");
			}
			
			String role =  user.getRole();
			if(role != null && role !=""){
				sqlBuffer.append(" and role = '"+role+"'");
			}
		
		
		//拼装分页参数
		sqlBuffer.append(" limit "+((page.getPageNo()-1)*page.getPageSize())+","+page.getPageSize());
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new User();
			entity.setId(rs.getString("id"));
			entity.setUsername(rs.getString("username"));
			entity.setPassword(rs.getString("password"));
			entity.setName(rs.getString("name"));
			entity.setSex(rs.getString("sex"));
			entity.setPhone(rs.getString("phone"));
			entity.setRole(rs.getString("role"));
			entity.setContent(rs.getString("content"));
			list.add(entity);
		}
		return list;
	}
	
	/**
	 * 查询所有
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> findAll(Connection con,User user)throws Exception{
		List<User> list = new ArrayList<User>();
		User entity=null;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from db_user where 1=1");
		//拼接分页条件
			String username =  user.getUsername();
			if(username != null && username !=""){
				sqlBuffer.append(" and username = '"+username+"'");
			}
			
			String name =  user.getName();
			if(name != null && name !=""){
				sqlBuffer.append(" and name = '"+name+"'");
			}
			
			String sex =  user.getSex();
			if(sex != null && sex !=""){
				sqlBuffer.append(" and sex = '"+sex+"'");
			}
			String role =  user.getRole();
			if(role != null && role !=""){
				sqlBuffer.append(" and role = '"+role+"'");
			}
			
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			entity = new User();
			entity.setId(rs.getString("id"));
			entity.setUsername(rs.getString("username"));
			entity.setPassword(rs.getString("password"));
			entity.setName(rs.getString("name"));
			entity.setSex(rs.getString("sex"));
			entity.setPhone(rs.getString("phone"));
			entity.setRole(rs.getString("role"));
			entity.setContent(rs.getString("content"));
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
	public User getById(Connection con,String id)throws Exception{
		User user=null;
		String sql="select * from db_user where id = ?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			user=new User();
			user.setId(rs.getString("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getString("sex"));
			user.setPhone(rs.getString("phone"));
			user.setRole(rs.getString("role"));
			user.setContent(rs.getString("content"));
		}
		return user;
	}
	
	//获取总数 分页使用
	public int count(Connection con,User user)throws Exception{
		int count = 0;
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(*) as count from db_user where 1=1");
		//拼接分页条件
			String username =  user.getUsername();
			if(username != null && username !=""){
				sqlBuffer.append(" and username = '"+username+"'");
			}
			
			String name =  user.getName();
			if(name != null && name !=""){
				sqlBuffer.append(" and name = '"+name+"'");
			}
			
			String sex =  user.getSex();
			if(sex != null && sex !=""){
				sqlBuffer.append(" and sex = '"+sex+"'");
			}
			String role =  user.getRole();
			if(role != null && role !=""){
				sqlBuffer.append(" and role = '"+role+"'");
			}
			
		
		String sql=sqlBuffer.toString();
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}

	public User login(Connection con, String username, String password) {
		User user=null;
		String sql="select * from db_user where username = ? and password=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("role"));
				user.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
}