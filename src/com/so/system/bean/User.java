package com.so.system.bean;

import java.util.Date;
/**
 * 系统用户Entity
 * @author so
  * @version V1.0
 */
public class User {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;		// 用户名
	private String password;		// 密码
	private String name;		// 昵称
	private String sex;		// 性别
	private String phone;		// 联系方式
	
	private String content;//自我简介
	
	private String role;//角色字段
	
	public User() {
		super();
	}

	public User(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}