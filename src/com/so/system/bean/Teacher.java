package com.so.system.bean;

import java.util.Date;
/**
 * 教师管理Entity
 * @author so
 * @version V1.0
 */
public class Teacher {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String teacherNo;		// 教师编号
	private String teacherName;		// 教师姓名
	private String teaTitle;		// 教师职称
	private String sex;		// 性别
	private String tel;		// 联系电话
	private String content;		// 简介
	
	public Teacher() {
		super();
	}

	public Teacher(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getTeacherNo() {
		return teacherNo;
	}

	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public String getTeaTitle() {
		return teaTitle;
	}

	public void setTeaTitle(String teaTitle) {
		this.teaTitle = teaTitle;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}