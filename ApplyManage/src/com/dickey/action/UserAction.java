package com.dickey.action;

import java.util.LinkedList;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.User;

public class UserAction extends BaseAction{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	//需要编辑的model的id
	private String id = "";
	
	//模型驱动的实例
	private User model = new User();
	
	//删除时的选中项的id
	private String[] checkItems;
	
	//搜索时的字段类别
	private String property;
	
	//搜索时的关键字
	private String keyword;
	
	//页面标题
	private String title;
	
	//模型驱动的实例集
	private List<User> users = new LinkedList<User>();
	
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		setUsers(userService.findUsersByProp(property, keyword));
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		setUsers(userService.findUsers());
		return SUCCESS;
	}
	
	/*
	 * 加载增加页面
	 */
	public String add(){
		title = "创建新";
		return INPUT;
	}
	
	/*
	 * 加载修改页面
	 */
	public String edit(){
		title = "编辑";
		model = userService.findUser(id);
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
		//加密密码
		String crypto = new Md5Hash(model.getPassword()).toHex(); 
		model.setPassword(crypto);
		
		if(model.getId().equals("")){
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
