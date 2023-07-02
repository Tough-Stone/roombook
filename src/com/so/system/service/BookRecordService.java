package com.so.system.service;

import java.util.List;
import com.so.system.bean.BookRecord;

import org.apache.commons.utils.Page;

/**
 * 预约记录DAO接口
 * @author so
 * @version V1.0
 */
public interface BookRecordService {
	//添加
	public int add(BookRecord bookRecord);
	//删除
	public int delete(String id);
	//修改
	public int update(BookRecord bookRecord);
	//查询分页
	public Page<BookRecord> page(BookRecord bookRecord,Page<BookRecord> page);
	//根据ID查询
	public BookRecord getById(String id);
	//查询所有
	public List<BookRecord> findAll(BookRecord bookRecord);
	public boolean check(BookRecord bookRecord);
}