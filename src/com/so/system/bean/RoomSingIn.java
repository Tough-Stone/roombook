package com.so.system.bean;

import java.util.Date;
/**
 * 签到记录Entity
 * @author so
 * @version V1.0
 */
public class RoomSingIn {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String studentId;		// 学生编号
	private String signTime;		// 签到时间
	private String roomBookId;		// 会议ID
	
	public RoomSingIn() {
		super();
	}

	public RoomSingIn(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	
	public String getRoomBookId() {
		return roomBookId;
	}

	public void setRoomBookId(String roomBookId) {
		this.roomBookId = roomBookId;
	}
	
}