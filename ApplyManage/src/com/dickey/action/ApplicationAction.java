package com.dickey.action;


import java.util.LinkedList;
import java.util.List;


import com.dickey.action.base.BaseAction;
import com.dickey.domain.Application;
import com.dickey.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ApplicationAction extends BaseAction implements ModelDriven<Application>, Preparable{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id = "";
	
	private Application application = new Application();
	
	private List<Application> applications = new LinkedList<Application>();
	
	/*
	 * 查询
	 */
	public String query(){
		
		User user = (User) ActionContext.getContext().getSession().get("user");
		setApplications(userService.findApplicationsByUser(user));
		
		return SUCCESS;
	}
	
	/*
	 * 增加
	 */
	public String add(){
		return INPUT;
	}
	
	/*
	 * 修改页面加载
	 */
	public String edit(){
		application = userService.findApplication(application.getId());
		System.out.println(application);
		
		return INPUT;
	}
	
	/*
	 * 修改提交
	 */
	public String editSubmit(){
		userService.updateApplication(application);
		return query();
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	@Override
	public void prepare() throws Exception {
		application = new Application();
	}
	
	@Override
	public Application getModel() {
		return application;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.err.println("Setting");
		this.id = id;
	}



}
