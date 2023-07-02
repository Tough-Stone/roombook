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

import com.so.system.bean.RoomSingIn;
import com.so.system.bean.User;
import com.so.system.dao.RoomSingInDao;
import com.so.system.service.RoomSingInService;
import com.so.system.service.impl.RoomSingInServiceImpl;
import com.so.system.utils.UserUtil;


@WebServlet("/roomSingIn")
public class RoomSingInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String contextPath = "";
	
	RoomSingInDao roomSingInDao=new RoomSingInDao();
	RoomSingInService roomSingInService = new RoomSingInServiceImpl();
	
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
		}else if ("sign".equals(method)) {
			sign(request, response);
		}else if ("mine".equals(method)) {
			mine(request, response);
		}else if ("insert".equals(method)) {
            insert(request, response);
		}
	}
	
	private void mine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		RoomSingIn roomSingIn = new RoomSingIn();
		//分页有关
		Page<RoomSingIn> page = new Page<RoomSingIn>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String studentId = UserUtil.currentUser(request).getId();
		roomSingIn.setStudentId(studentId);
		String roomBookId = request.getParameter("roomBookId");
		if (roomBookId != null && roomBookId != "") {
			roomSingIn.setRoomBookId(roomBookId);
			request.setAttribute("roomBookId", roomBookId);
		}
		
		//判断提示信息
		Object msg = request.getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
		}
		
		page = roomSingInService.page(roomSingIn, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/system/roomSingInMine.jsp").forward(request, response);
	}

	//签到
	private void sign(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		User currentUser = UserUtil.currentUser(request);//拿到当前签到的登录用户
		RoomSingIn roomSingIn = new RoomSingIn();
		roomSingIn.setRoomBookId(id);
		roomSingIn.setStudentId(currentUser.getId());
		//签到先判断是否已经签到了
		List<RoomSingIn> findAll = roomSingInService.findAll(roomSingIn);
		if (findAll!=null && findAll.size()>0) {
			//已经签到
			response.sendRedirect(contextPath+"/bookRecord?msg=2&method=list");
		}else{
			//没有签到 保存签到信息
			roomSingIn.setSignTime(DateUtils.getDateTime());
			roomSingInService.add(roomSingIn);
			response.sendRedirect(contextPath+"/bookRecord?msg=1&method=list");
		}
	}
    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String recordId = request.getParameter("recordId").replace(" ","");
        System.out.println(id);
        System.out.println(recordId);
        RoomSingIn roomSingIn = new RoomSingIn();
        roomSingIn.setRoomBookId(recordId);
        String studentId = roomSingInService.selectStudentByCardId(id);
        if(studentId != null && studentId != ""){
            roomSingIn.setStudentId(studentId);
            //签到先判断是否已经签到了
            List<RoomSingIn> findAll = roomSingInService.findAll(roomSingIn);
            if (findAll!=null && findAll.size()>0) {
//                response.sendRedirect(contextPath+"/roomSingIn?method=list&roomBookId="+recordId);
            }else{
                //没有签到 保存签到信息
                roomSingIn.setSignTime(DateUtils.getDateTime());
                roomSingInService.add(roomSingIn);

            }
        }
//        response.sendRedirect(contextPath+"/roomSingIn?method=list&roomBookId="+recordId);
    }
	//删除
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		roomSingInService.delete(id);
		response.sendRedirect(contextPath+"/roomSingIn?method=list");
	}
	
	//修改
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String studentId = request.getParameter("studentId");
		String signTime = request.getParameter("signTime");
		String roomBookId = request.getParameter("roomBookId");
		RoomSingIn roomSingIn = new RoomSingIn();
		roomSingIn.setId(id);
		roomSingIn.setStudentId(studentId);
		roomSingIn.setSignTime(signTime);
		roomSingIn.setRoomBookId(roomBookId);
		if (roomSingIn.getId()!=null && !"".equals(roomSingIn.getId())) {
   			roomSingInService.update(roomSingIn);
   		}else{
   			roomSingInService.add(roomSingIn);
   		}
		response.sendRedirect(contextPath+"/roomSingIn?method=list");
	}
	
	//列表查询
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("---开始查询---");
		RoomSingIn roomSingIn = new RoomSingIn();
		//分页有关
		Page<RoomSingIn> page = new Page<RoomSingIn>();
		//设置查询页
		String pageNoStr = request.getParameter("pageNo");
		if (pageNoStr != null && pageNoStr != "") {
			page.setPageNo(Integer.parseInt(pageNoStr));
		}
		//设置查询条件
		String studentId = request.getParameter("studentId");
		if (studentId != null && studentId != "") {
			roomSingIn.setStudentId(studentId);
			request.setAttribute("studentId", studentId);
		}
		String roomBookId = request.getParameter("roomBookId");
		if (roomBookId != null && roomBookId != "") {
			roomSingIn.setRoomBookId(roomBookId);
			request.setAttribute("roomBookId", roomBookId);
		}
		
		//判断提示信息
		Object msg = request.getAttribute("msg");
		if (msg != null) {
			request.setAttribute("msg", msg.toString());
		}
		
		page = roomSingInService.page(roomSingIn, page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/views/system/roomSingInList.jsp").forward(request, response);
	}
	
	//form跳转页面
	private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		RoomSingIn roomSingIn = new RoomSingIn();
		if (id!=null && id!="") {
			roomSingIn = roomSingInService.getById(id);
		}
		request.setAttribute("roomSingIn", roomSingIn);
		request.getRequestDispatcher("/views/system/roomSingInForm.jsp").forward(request, response);
	}
	
}