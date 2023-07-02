package com.so.timetask;

import java.util.Date;
import java.util.List;

import org.apache.commons.utils.DateUtils;

import com.so.system.bean.BookRecord;
import com.so.system.service.BookRecordService;
import com.so.system.service.impl.BookRecordServiceImpl;

/**
 * 更新预约状态的定时任务 使用jdk自带的定时任务
 * @author Administrator
 *
 */
public class UpdateAuditTypeTask extends java.util.TimerTask {
	
	BookRecordService bookRecordService = new BookRecordServiceImpl();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("定时更新预约状态启动");
		BookRecord bookRecord = new BookRecord();
		bookRecord.setAuditType("1");
		List<BookRecord> findAll = bookRecordService.findAll(bookRecord);
		for (BookRecord bookRecord2 : findAll) {
			Date bookTime = bookRecord2.getBookTime();
			Date timeQuantum = bookRecord2.getTimeQuantum();
		
			Date nowDate = new Date();//得到当前的时间
			if(nowDate.getTime()>timeQuantum.getTime()){
				//说明已经过期
				bookRecord2.setAuditType("2");//已过期
			}else{
				//开启签到按钮
				if (nowDate.getTime()>bookTime.getTime() && nowDate.getTime()<timeQuantum.getTime()) {
					//打开签到按钮
					bookRecord2.setIsOpen("1");
				}else{
					//关闭签到按钮
					bookRecord2.setIsOpen("0");
				}
			}
			bookRecordService.update(bookRecord2);
		}
	}

}
