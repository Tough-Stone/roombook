package com.so.system.utils;

import javax.servlet.http.HttpServletRequest;

import com.so.system.bean.User;

public class UserUtil {
	
	public static User currentUser(HttpServletRequest request){
		Object login = request.getSession().getAttribute("login");
		if (login!=null) {
			return (User) login;
		}else{
			return null;
		}
	}
}
