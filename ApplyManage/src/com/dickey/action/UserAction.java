package com.dickey.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.Role;
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
	
	//搜索可用的字段
	private String[] fields = {"id", "username"};
	private Map<String, String> properties = new HashMap<String, String>();
	
	//搜索时的字段类别
	private String property;
	
	//搜索时的关键字
	private String keyword;
	
	//页面标题
	private String title;
	
	//关联查询的类
	private String refClass;
	
	//关联查询的类的id
	private String refId;
	
	//模型驱动的实例集
	private List<User> models = new LinkedList<User>();
	
	//系统所有的角色
	private List<Role> sysRoles = new LinkedList<Role>();
	
	//当前用户所有的角色
	private List<String> roles = new LinkedList<String>();
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		for (String field : fields) {
			properties.put(field, getText("user." + field));
		}
		setModels(userService.findUsersByProp(property, keyword));
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		for (String field : fields) {
			properties.put(field, getText("user." + field));
		}
		setModels(userService.findUsers());
		return SUCCESS;
	}
	
	/*
	 * 关联查询
	 */
	public String queryByRef(){
		setModels(userService.findUsersByRef(refClass, refId));
		return SUCCESS;
	}
	
	/*
	 * 加载增加页面
	 */
	public String add(){
		title = "创建新";
		
		//处理关联的角色字段
		sysRoles = userService.findRoles();
		
		return INPUT;
	}
	
	/*
	 * 加载修改页面
	 */
	public String edit(){
		title = "编辑";
		
		model = userService.findUser(id);
		
		//处理关联的角色字段
		sysRoles = userService.findRoles();
		for (Role role : model.getRoles()) {
			roles.add(role.getId());
		}
		
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
		//加密密码
		String crypto = new Md5Hash(model.getPassword()).toHex(); 
		model.setPassword(crypto);
		
		//处理角色绑定
		Set<Role> roleList = new HashSet<Role>();
		for (String roleId : roles) {
			if(!roleId.equals("")){
				Role role = userService.findRole(roleId);
				roleList.add(role);
			}
		}
		model.setRoles(roleList);
		
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
			userService.deleteUser(id);
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
	
	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<User> getModels() {
		return models;
	}

	public void setModels(List<User> models) {
		this.models = models;
	}

	public List<Role> getSysRoles() {
		return sysRoles;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	

}
