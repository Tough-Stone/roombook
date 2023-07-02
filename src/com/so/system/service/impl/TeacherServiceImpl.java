package com.so.system.service.impl;


import java.sql.Connection;
import java.util.List;

import org.apache.commons.utils.Page;

import com.so.utils.DbUtil;
import com.so.utils.PropertiesUtil;
import com.so.system.bean.Teacher;
import com.so.system.bean.User;
import com.so.system.dao.TeacherDao;
import com.so.system.dao.UserDao;
import com.so.system.service.TeacherService;


/**
 * 教师管理DAO接口
 * @author so
 * @version V1.0
 */
public class TeacherServiceImpl  implements TeacherService  {
	
	TeacherDao teacherDao = new TeacherDao();
	UserDao userDao = new UserDao();
	
	@Override
	public int add(Teacher teacher) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =teacherDao.add(con, teacher);
			//插入对应的教师系统用户
			User user = new User();
			user.setId(teacher.getId());
			user.setUsername(teacher.getTeacherNo());
			user.setName(teacher.getTeacherName());
			user.setPhone(teacher.getTel());
			user.setSex(teacher.getSex());
			user.setContent(teacher.getContent());
			user.setPassword("123456");//设置初始密码
			user.setRole("2");
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
			int delete = teacherDao.delete(con, id);
			userDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Teacher teacher) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = teacherDao.update(con, teacher);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<Teacher> page(Teacher teacher, Page<Teacher> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = teacherDao.count(con,teacher);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<Teacher> list = teacherDao.list(con, teacher, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Teacher getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Teacher teacher = teacherDao.getById(con, id);
			DbUtil.closeCon(con);
			return teacher;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Teacher> findAll(Teacher teacher) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<Teacher> list = teacherDao.findAll(con, teacher);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}