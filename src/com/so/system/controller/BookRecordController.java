package com.so.system.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.utils.DateUtils;
import org.apache.commons.utils.Page;
import org.apache.commons.utils.PropertiesUtil;

import com.so.system.bean.BookRecord;
import com.so.system.bean.User;
import com.so.system.dao.BookRecordDao;
import com.so.system.service.BookRecordService;
import com.so.system.service.impl.BookRecordServiceImpl;
import com.so.system.utils.UserUtil;


@WebServlet("/bookRecord")
public class BookRecordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	BookRecordDao bookRecordDao=new BookRecordDao();
	BookRecordService bookRecordService = new BookRecordServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contextPath = request.getServletContext().getContextPath();
		String method = request.getParameter("method");
		if ("save".equals(method)) {
			save(request,response);
		}else if ("delete".equals(method)) {
			delete(request, response);
		}else if ("list".equals(method)) {
			list(request, response);
		}else if ("form".equals(method)) {
			form(request, response);
		}
		
	}
	
	//删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		BookRecord byId = bookRecordService.getById(id);
		byId.setAuditType("3");
		bookRecordService.update(byId);
		response.sendRedirect(contextPath+"/bookRecord?method=list");
	}
	
	//修改
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String roomNo = request.getParameter("roomNo");
		String bookTime = request.getParameter("bookTime");
		String timeQuantum = request.getParameter("timeQuantum");
		String useRemark = request.getParameter("useRemark");
		String auditType = request.getParameter("auditType");
		String bookUser = request.getParameter("bookUser");
		String mainUser = request.getParameter("mainUser");
		BookRecord bookRecord = new BookRecord();
		bookRecord.setId(id);
		bookRecord.setTitle(title);
		bookRecord.setRoomNo(roomNo);
		bookRecord.setBookTime(DateUtils.parseDate(bookTime));
		bookRecord.setTimeQuantum(DateUtils.parseDate(timeQuantum));
		bookRecord.setUseRemark(useRemark);
		bookRecord.setAuditType(auditType);
		bookRecord.setBookUser(bookUser);
		bookRecord.setMainUser(mainUser);
		if (bookRecord.getId()!=null && !"".equals(bookRecord.getId())) {
   			bookRecordService.update(bookRecord);
   			response.sendRedirect(contextPath+"/bookRecord?method=list");
   		}else{
   			//预约时获取当前登录的用户ID作为预约用户的iD
   			User currentUser = UserUtil.currentUser(request);
   			bookRecord.setBookUser(currentUser.getId());
   			//设置默认的预约状态--1：预约成功 2：已过期 3：已撤销 
   			bookRecord.setAuditType("1");
   			//添加的时候还需要判断当前预约的时间段是否被预约了
   			BookRecord query = new BookRecord();
   			query.setRoomNo(roomNo);
   			query.setBookTime(DateUtils.parseDate(bookTime));
   			query.setTimeQuantum(bookRecord.getTimeQuantum());
   			query.setAuditType("1");
   			boolean check = bookRecordService.check(query);
   			if (check) {
   				//说明被预约了
   				request.setAttribute("bookRecord", bookRecord);
   				request.setAttribute("msg", "当前时间段冲突，请选择其他时间段预约！！！");
   				request.getRequestDispatcher("/views/system/bookRecordForm.jsp").forward(request, response); 
   			}else{
   				bookRecordService.add(bookRecord);
   				response.sendRedirect(contextPath+"/bookRecord?method=list");
   			}
   		}
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		BookRecord bookRecord = new BookRecord();
		//分页有关
		Page<BookRecord> page = new Page<BookRecord>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String title = request.getParameter("title");
		if (title != null && title != "") {
			bookRecord.setTitle(title);
			request.setAttribute("title", title);
		}
		String roomNo = request.getParameter("roomNo");
		if (roomNo != null && roomNo != "") {
			bookRecord.setRoomNo(roomNo);
			request.setAttribute("roomNo", roomNo);
		}
		String bookTime = request.getParameter("bookTime");
		if (bookTime != null && bookTime != "") {
			bookRecord.setBookTime(DateUtils.parseDate(bookTime));
			request.setAttribute("bookTime", bookTime);
		}
		String timeQuantum = request.getParameter("timeQuantum");
		if (timeQuantum != null && timeQuantum != "") {
			bookRecord.setTimeQuantum(DateUtils.parseDate(timeQuantum));
			request.setAttribute("timeQuantum", timeQuantum);
		}
		String auditType = request.getParameter("auditType");
		if (auditType != null && auditType != "") {
			bookRecord.setAuditType(auditType);
			request.setAttribute("auditType", auditType);
		}
		String bookUser = request.getParameter("bookUser");
		if (bookUser != null && bookUser != "") {
			bookRecord.setBookUser(bookUser);
			request.setAttribute("bookUser", bookUser);
		}
		
		//如果是教师用户，只查自己的预约数据
		User currentUser = UserUtil.currentUser(request);
		if ("2".equals(currentUser.getRole())) {
			bookRecord.setBookUser(currentUser.getId());
		}
		
		//判断提示信息
		Object msg = request.getParameter("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
		}
		
		page = bookRecordService.page(bookRecord, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/system/bookRecordList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		BookRecord bookRecord = new BookRecord();
		if (id!=null && id!="") {
			bookRecord = bookRecordService.getById(id);
		}
		request.setAttribute("bookRecord", bookRecord);
		request.getRequestDispatcher("/views/system/bookRecordForm.jsp").forward(request, response);
	}
	
}