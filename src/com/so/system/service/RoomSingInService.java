package com.so.system.service;

import java.util.List;
import com.so.system.bean.RoomSingIn;

import com.so.system.bean.User;
import org.apache.commons.utils.Page;

/**
 * 签到记录DAO接口
 * @author so
 * @version V1.0
 */
public interface RoomSingInService {
	
	//添加
	public int add(RoomSingIn roomSingIn);
	//删除
	public int delete(String id);
	//修改
	public int update(RoomSingIn roomSingIn);
	//查询分页
	public Page<RoomSingIn> page(RoomSingIn roomSingIn,Page<RoomSingIn> page);
	//根据ID查询
	public RoomSingIn getById(String id);
	//查询所有
	public List<RoomSingIn> findAll(RoomSingIn roomSingIn);
	//根据磁卡卡号查询学员id
	public String selectStudentByCardId(String cardId);


		
}