package com.dickey.action;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;


import com.dickey.action.base.BaseAction;
import com.dickey.domain.Application;
import com.dickey.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class ApplicationAction extends BaseAction{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	//需要编辑的model的id
	private String id = "";
	
	//模型驱动的实例
	private Application model = new Application();
	
	//删除时的选中项的id
	private String[] checkItems;
	
	//搜索时的字段类别
	private String property;
	
	//搜索时的关键字
	private String keyword;
	
	//当前用户
	private User user = (User) ActionContext.getContext().getSession().get("user");
	
	private List<Application> applications = new LinkedList<Application>();
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		setApplications(userService.findApplicationsByProp(property, keyword, user));
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		setApplications(userService.findApplicationsByUser(user));
		return SUCCESS;
	}
	
	/*
	 * 加载增加页面
	 */
	public String add(){
		return INPUT;
	}
	
	/*
	 * 加载修改页面
	 */
	public String edit(){
		model = userService.findApplication(id);
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
		if(model.getId().equals("")){
			//处理与User关联的数据
			try {
				Method method = model.getClass().getDeclaredMethod("setUser", User.class);
				method.invoke(model, user);
			} catch (Exception e) {
				System.out.println("与User无关联");
			}
			//处理新建
			userService.addApplication(model);
		}else{
			//处理更新
			userService.updateApplication(model);
		}
		return query();
	}
	
	/*
	 * 处理删除
	 */
	public String delete(){
		for (String id : checkItems) {
			userService.delApplication(id);
		}
		return query();
	}
	/*
	 * Getters 和 Setters
	 */
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}	

	public List<Application> getApplications() {
		return applications;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Application getModel() {
		return model;
	}

	public void setModel(Application model) {
		this.model = model;
	}

	public String[] getCheckItems() {
		return checkItems;
	}

	public void setCheckItems(String[] checkItems) {
		this.checkItems = checkItems;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



}
