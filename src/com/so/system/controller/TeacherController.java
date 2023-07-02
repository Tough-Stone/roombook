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
import org.apache.commons.utils.Page;
import org.apache.commons.utils.PropertiesUtil;

import com.so.system.bean.Teacher;
import com.so.system.dao.TeacherDao;
import com.so.system.service.TeacherService;
import com.so.system.service.impl.TeacherServiceImpl;


@WebServlet("/teacher")
public class TeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	TeacherDao teacherDao=new TeacherDao();
	TeacherService teacherService = new TeacherServiceImpl();
	
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
		teacherService.delete(id);
		response.sendRedirect(contextPath+"/teacher?method=list");
	}
	
	//修改
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String teacherNo = request.getParameter("teacherNo");
		String teacherName = request.getParameter("teacherName");
		String teaTitle = request.getParameter("teaTitle");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String content = request.getParameter("content");
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setTeacherNo(teacherNo);
		teacher.setTeacherName(teacherName);
		teacher.setTeaTitle(teaTitle);
		teacher.setSex(sex);
		teacher.setTel(tel);
		teacher.setContent(content);
		if (teacher.getId()!=null && !"".equals(teacher.getId())) {
   			teacherService.update(teacher);
   		}else{
   			teacherService.add(teacher);
   		}
		response.sendRedirect(contextPath+"/teacher?method=list");
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		Teacher teacher = new Teacher();
		//分页有关
		Page<Teacher> page = new Page<Teacher>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String teacherNo = request.getParameter("teacherNo");
		if (teacherNo != null && teacherNo != "") {
			teacher.setTeacherNo(teacherNo);
			request.setAttribute("teacherNo", teacherNo);
		}
		String teacherName = request.getParameter("teacherName");
		if (teacherName != null && teacherName != "") {
			teacher.setTeacherName(teacherName);
			request.setAttribute("teacherName", teacherName);
		}
		
		//判断提示信息
		Object msg = request.getSession().getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
			request.getSession().removeAttribute("msg");
		}
		
		page = teacherService.page(teacher, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/system/teacherList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Teacher teacher = new Teacher();
		if (id!=null && id!="") {
			teacher = teacherService.getById(id);
		}
		request.setAttribute("teacher", teacher);
		request.getRequestDispatcher("/views/system/teacherForm.jsp").forward(request, response);
	}
	
}