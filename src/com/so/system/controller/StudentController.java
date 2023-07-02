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

import com.so.system.bean.Student;
import com.so.system.dao.StudentDao;
import com.so.system.service.StudentService;
import com.so.system.service.impl.StudentServiceImpl;


@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	StudentDao studentDao=new StudentDao();
	StudentService studentService = new StudentServiceImpl();
	
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
		studentService.delete(id);
		response.sendRedirect(contextPath+"/student?method=list");
	}
	
	//修改
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String studentNo = request.getParameter("studentNo");
		String studentName = request.getParameter("studentName");
		String sex = request.getParameter("sex");
		String collegeRoom = request.getParameter("collegeRoom");
		String major = request.getParameter("major");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String content = request.getParameter("content");
		Student student = new Student();
		student.setId(id);
		student.setStudentNo(studentNo);
		student.setStudentName(studentName);
		student.setSex(sex);
		student.setCollegeRoom(collegeRoom);
		student.setMajor(major);
		student.setEmail(email);
		student.setTel(tel);
		student.setContent(content);
		if (student.getId()!=null && !"".equals(student.getId())) {
   			studentService.update(student);
   		}else{
   			studentService.add(student);
   		}
		response.sendRedirect(contextPath+"/student?method=list");
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		Student student = new Student();
		//分页有关
		Page<Student> page = new Page<Student>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String studentNo = request.getParameter("studentNo");
		if (studentNo != null && studentNo != "") {
			student.setStudentNo(studentNo);
			request.setAttribute("studentNo", studentNo);
		}
		String studentName = request.getParameter("studentName");
		if (studentName != null && studentName != "") {
			student.setStudentName(studentName);
			request.setAttribute("studentName", studentName);
		}
		String sex = request.getParameter("sex");
		if (sex != null && sex != "") {
			student.setSex(sex);
			request.setAttribute("sex", sex);
		}
		String collegeRoom = request.getParameter("collegeRoom");
		if (collegeRoom != null && collegeRoom != "") {
			student.setCollegeRoom(collegeRoom);
			request.setAttribute("collegeRoom", collegeRoom);
		}
		String major = request.getParameter("major");
		if (major != null && major != "") {
			student.setMajor(major);
			request.setAttribute("major", major);
		}
		
		//判断提示信息
		Object msg = request.getSession().getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
			request.getSession().removeAttribute("msg");
		}
		
		page = studentService.page(student, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/system/studentList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Student student = new Student();
		if (id!=null && id!="") {
			student = studentService.getById(id);
		}
		request.setAttribute("student", student);
		request.getRequestDispatcher("/views/system/studentForm.jsp").forward(request, response);
	}
	
}