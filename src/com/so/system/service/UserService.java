package com.so.system.service;

import java.util.List;

import org.apache.commons.utils.Page;

import com.so.system.bean.User;

/**
 * 系统用户DAO接口
 * @author so
 * @version V1.0
 */
public interface UserService {
	
	//添加
	public int add(User user);
	//删除
	public int delete(String id);
	//修改
	public int update(User user);
	//查询分页
	public Page<User> page(User user,Page<User> page);
	//根据ID查询
	public User getById(String id);
	//查询所有
	public List<User> findAll(User user);
	
	public User login(String username,String password);
		
}