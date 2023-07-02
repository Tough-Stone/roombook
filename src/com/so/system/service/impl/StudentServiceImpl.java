package com.so.system.service.impl;


import java.sql.Connection;
import java.util.List;

import org.apache.commons.utils.Page;

import com.so.utils.DbUtil;
import com.so.utils.PropertiesUtil;
import com.so.system.bean.Student;
import com.so.system.bean.User;
import com.so.system.dao.StudentDao;
import com.so.system.dao.UserDao;
import com.so.system.service.StudentService;


/**
 * 学生管理DAO接口
 * @author so
 * @version V1.0
 */
public class StudentServiceImpl  implements StudentService  {
	
	StudentDao studentDao = new StudentDao();
	
	UserDao userDao = new UserDao();
	
	@Override
	public int add(Student student) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =studentDao.add(con, student);
			//插入对应的学生系统用户
			User user = new User();
			user.setId(student.getId());
			user.setUsername(student.getStudentNo());
			user.setName(student.getStudentName());
			user.setPhone(student.getTel());
			user.setSex(student.getSex());
			user.setContent(student.getContent());
			user.setPassword("123456");//设置初始密码
			user.setRole("3");
			userDao.add(con, user);
			DbUtil.closeCon(con);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int delete = studentDao.delete(con, id);
			userDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Student student) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = studentDao.update(con, student);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<Student> page(Student student, Page<Student> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = studentDao.count(con,student);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<Student> list = studentDao.list(con, student, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Student student = studentDao.getById(con, id);
			DbUtil.closeCon(con);
			return student;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Student> findAll(Student student) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<Student> list = studentDao.findAll(con, student);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}