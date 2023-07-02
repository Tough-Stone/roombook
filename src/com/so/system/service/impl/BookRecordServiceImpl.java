package com.so.system.service.impl;


import java.sql.Connection;
import java.util.List;

import org.apache.commons.utils.Page;

import com.so.utils.DbUtil;
import com.so.utils.PropertiesUtil;
import com.so.system.bean.BookRecord;
import com.so.system.dao.BookRecordDao;
import com.so.system.service.BookRecordService;


/**
 * 预约记录DAO接口
 * @author so
 * @version V1.0
 */
public class BookRecordServiceImpl  implements BookRecordService  {
	
	BookRecordDao bookRecordDao = new BookRecordDao();
	
	@Override
	public int add(BookRecord bookRecord) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			Integer result =bookRecordDao.add(con, bookRecord);
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
			int delete = bookRecordDao.delete(con, id);
			DbUtil.closeCon(con);
			return delete;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(BookRecord bookRecord) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int update = bookRecordDao.update(con, bookRecord);
			DbUtil.closeCon(con);
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Page<BookRecord> page(BookRecord bookRecord, Page<BookRecord> page) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			int count = bookRecordDao.count(con,bookRecord);
			page.setCount(count);
			page.setPrev(page.getPageNo()-1);
			page.setNext(page.getPageNo() + 1);// 下一页
			page.setLast((count - 1) / page.getPageSize() + 1);// 总也数
			List<BookRecord> list = bookRecordDao.list(con, bookRecord, page);
			page.setList(list);
			DbUtil.closeCon(con);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BookRecord getById(String id) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			BookRecord bookRecord = bookRecordDao.getById(con, id);
			DbUtil.closeCon(con);
			return bookRecord;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<BookRecord> findAll(BookRecord bookRecord) {
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			List<BookRecord> list = bookRecordDao.findAll(con, bookRecord);
			DbUtil.closeCon(con);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean check(BookRecord bookRecord) {
		boolean re = false;
		try {
			Connection con = DbUtil.getDbUtil().getCon();
			re = bookRecordDao.check(con, bookRecord);
			DbUtil.closeCon(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
		
}