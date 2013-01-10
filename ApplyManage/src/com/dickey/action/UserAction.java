package com.dickey.action;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class UserAction extends BaseAction{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	//需要编辑的model的id
	private String id = "";
	
	//模型驱动的实例
	private User model = new User();
	
	//模型是否与User相关联
	private boolean userRel = false;
	
	//删除时的选中项的id
	private String[] checkItems;
	
	//搜索时的字段类别
	private String property;
	
	//搜索时的关键字
	private String keyword;
	
	//当前用户
	private User user = (User) ActionContext.getContext().getSession().get("user");
	
	private List<User> users = new LinkedList<User>();
	
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		
		//处理与User关联的数据
		checkUserRel();
		
		setUsers(userService.findUsersByProp(property, keyword, userRel, user));
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	@SuppressWarnings("unchecked")
	public String query(){
		
		//处理与User关联的数据
		checkUserRel();
		
		Method method = null;
		if(userRel){
			try {
				method = userService.getClass().getDeclaredMethod("findUsersByUser", User.class);
				setUsers((List<User>) method.invoke(userService, user));
			} catch (Exception e) {
				System.err.println("userService中无法找到按用户查找数据的方法");
			}
		}else{
			try {
				method = userService.getClass().getDeclaredMethod("findUsers");
				setUsers((List<User>) method.invoke(userService));
			} catch (Exception e) {
				System.err.println("userService中无法找到查找所有数据的方法");
			}
		}

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
		model = userService.findUser(id);
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
			userService.addUser(model);
		}else{
			//处理更新
			userService.updateUser(model);
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
	 * 判断当前模型实例是否与用户相关联
	 */
	private void checkUserRel(){
		//处理与User关联的数据
		try {
			model.getClass().getDeclaredField("user");
			userRel = true;
		}  catch (Exception e) {
			System.out.println(model.getClass().getName() + "与用户无关联");
		}
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

	public User getModel() {
		return model;
	}

	public void setModel(User model) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


}
