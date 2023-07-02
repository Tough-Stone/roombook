package com.so.timetask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * web容器启动销毁监听器
 * 
 * @author Administrator
 *
 */
@WebListener
public class WebContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("========================stop容器==========================");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		new TaskManager();//开启定时任务
		System.out.println("欢迎使用会议室预约系统。");
	}
}
