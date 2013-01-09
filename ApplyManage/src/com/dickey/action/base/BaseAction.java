package com.dickey.action.base;


import com.dickey.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//依赖的业务逻辑组件
	protected UserService userService;
	//依赖注入业务逻辑组件所必须的setter方法
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
}
