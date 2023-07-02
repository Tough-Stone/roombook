package com.so.timetask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务管理 采用jdk自带的定时任务
 * @author Administrator
 *
 */
public class TaskManager {
	private static final long ONE_MINUTE = 10 * 1000;// 10秒刷新一次
	private static final long ONE_HOUR =60 * 60 * 1000;// 1小时
	private static final long ONE_DAY =24 * 60 * 60 * 1000;// 1天
	public TaskManager() {
		Timer timer = new Timer();
		UpdateAuditTypeTask updateAuditTypeTask = new UpdateAuditTypeTask();
		//启动更新预约状态定时任务
		//timer.schedule(updateAuditTypeTask, bookTime(3, 0, 0), ONE_DAY);
		timer.schedule(updateAuditTypeTask, 0, ONE_MINUTE);
		//启动更新浏览量定时任务 每天凌晨1点更新
	}
	/**
	 * 设置固定时间
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	private Date bookTime(int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		Date date = calendar.getTime();
		return date;
	}
	public static void main(String[] args){
		new TaskManager();
	}
}
