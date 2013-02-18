package com.dickey.action;


import java.util.LinkedList;
import java.util.List;


import org.jbpm.api.task.Task;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.Application;
import com.dickey.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class TaskAction extends BaseAction{

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
	
	//页面标题
	private String title;
	
	//当前用户
	private User user = (User) ActionContext.getContext().getSession().get("user");
	
	//查询出的实例集
	private List<Task> models = new LinkedList<Task>();

	
	/*
	 * 查询待处理任务
	 */
	public String queryPending(){

		setModels(userService.getTaskList(user));
		return SUCCESS;
	}
	
	/*
	 * 查询已处理任务
	 */
	public String queryProcessed(){

		setModels(userService.getTaskList(user));
		return SUCCESS;
	}
	
	/*
	 * 加载增加页面
	 */
	public String add(){
		title = "创建新";
		
		//处理多对一关联的ApplicationType类
		//sysApplicationTypes = userService.findApplicationTypes();
		
		return INPUT;
	}
	
	/*
	 * 加载修改页面
	 */
	public String edit(){
		title = "编辑";
		
		//处理多对一关联的ApplicationType类
		//sysApplicationTypes = userService.findApplicationTypes();
		
		if(!id.trim().isEmpty()){
			model = userService.findApplication(id);
		}
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
		//是否有关联类操作（只在一对一情况下会进入）
		boolean flag = false;
		
		if(model.getUser() == null){
			model.setUser(user);
		}
		
		//处理多对一关联的ApplicationType
		if(model.getApplicationType() != null){
			String id =model.getApplicationType().getId();
			if(id != null && !id.equals("")){
				model.setApplicationType(userService.findApplicationType(id));
			}
		}
		
		if(model.getId().equals("")){
			//处理新建
			userService.addApplication(model);
		}else{
			//处理更新
			userService.updateApplication(model);
		}
		return queryPending();
	}
	
	/*
	 * 处理删除
	 */
	public String delete(){
		
		for (String id : checkItems) {
			userService.delApplication(id);
		}
		return queryPending();
	}
	
	
	/*
	 * Getters 和 Setters
	 */
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Task> getModels() {
		return models;
	}

	public void setModels(List<Task> models) {
		this.models = models;
	}

}
