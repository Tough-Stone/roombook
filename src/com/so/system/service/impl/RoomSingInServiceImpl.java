package com.so.system.service.impl;


import java.sql.Connection;
import java.util.List;

import com.so.system.bean.User;
import org.apache.commons.utils.Page;

import com.so.utils.DbUtil;
import com.so.utils.PropertiesUtil;
import com.so.system.bean.RoomSingIn;
import com.so.system.dao.RoomSingInDao;
import com.so.system.service.RoomSingInService;


/**
 * 签到记录DAO接口
 * @author so
 * @version V1.0
 */
public class RoomSingInServiceImpl  implements RoomSingInService  {
	
	RoomSingInDao roomSingInDao = new RoomSingInDao();
	
	@Override
	public int add(RoomSingIn roomSingIn) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =roomSingInDao.add(con, roomSingIn);
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
			int delete = roomSingInDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(RoomSingIn roomSingIn) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = roomSingInDao.update(con, roomSingIn);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<RoomSingIn> page(RoomSingIn roomSingIn, Page<RoomSingIn> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = roomSingInDao.count(con,roomSingIn);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<RoomSingIn> list = roomSingInDao.list(con, roomSingIn, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RoomSingIn getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			RoomSingIn roomSingIn = roomSingInDao.getById(con, id);
			DbUtil.closeCon(con);
			return roomSingIn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<RoomSingIn> findAll(RoomSingIn roomSingIn) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<RoomSingIn> list = roomSingInDao.findAll(con, roomSingIn);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String selectStudentByCardId(String cardId) {
        try {
            Connection con = DbUtil.getDbUtil().getCon();
            String userId = roomSingInDao.selectStudentByCardId(con, cardId);
            DbUtil.closeCon(con);
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
}