package com.so.system.bean;

import java.util.Date;
/**
 * 预约记录Entity
 * @author so
 * @version V1.0
 */
public class BookRecord {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;		// 主题
	private String roomNo;		// 会议室编号
	private Date bookTime;		// 预约起始日期
	private Date timeQuantum;		// 预约结束日期
	private String useRemark;		// 使用说明
	private String auditType;		// 审核状态
	private String bookUser;		// 预约用户
	
	private String isOpen; //是否开启签到
	private String mainUser;//主讲人
	
	public BookRecord() {
		super();
	}

	public BookRecord(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	
	public Date getTimeQuantum() {
		return timeQuantum;
	}

	public void setTimeQuantum(Date timeQuantum) {
		this.timeQuantum = timeQuantum;
	}
	
	public String getUseRemark() {
		return useRemark;
	}

	public void setUseRemark(String useRemark) {
		this.useRemark = useRemark;
	}
	
	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	public String getBookUser() {
		return bookUser;
	}

	public void setBookUser(String bookUser) {
		this.bookUser = bookUser;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getMainUser() {
		return mainUser;
	}

	public void setMainUser(String mainUser) {
		this.mainUser = mainUser;
	}
	
	
	
}