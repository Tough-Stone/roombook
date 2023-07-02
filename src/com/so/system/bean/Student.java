package com.so.system.bean;

import java.util.Date;
/**
 * 学生管理Entity
 * @author so
 * @version V1.0
 */
public class Student {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String studentNo;		// 学号
	private String studentName;		// 学生姓名
	private String sex;		// 性别
	private String collegeRoom;		// 学院
	private String major;		// 专业
	private String email;		// 邮箱
	private String tel;		// 联系电话
	private String content;		// 简介
	private String  cardnumber; //磁卡卡号
	
	public Student() {
		super();
	}

	public Student(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getCollegeRoom() {
		return collegeRoom;
	}

	public void setCollegeRoom(String collegeRoom) {
		this.collegeRoom = collegeRoom;
	}
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }
}