package com.so.system.service;

import java.util.List;
import com.so.system.bean.Teacher;

import org.apache.commons.utils.Page;

/**
 * 教师管理DAO接口
 * @author so
 * @version V1.0
 */
public interface TeacherService {
	
	//添加
	public int add(Teacher teacher);
	//删除
	public int delete(String id);
	//修改
	public int update(Teacher teacher);
	//查询分页
	public Page<Teacher> page(Teacher teacher,Page<Teacher> page);
	//根据ID查询
	public Teacher getById(String id);
	//查询所有
	public List<Teacher> findAll(Teacher teacher);
		
}