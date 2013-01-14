package com.dickey.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.dickey.action.base.BaseAction;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;
import com.dickey.domain.User;

public class RoleAction extends BaseAction{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	//需要编辑的model的id
	private String id = "";
	
	//模型驱动的实例
	private Role model = new Role();
	
	//删除时的选中项的id
	private String[] checkItems;
	
	//搜索可用的字段
	private String[] fields = {"id", "rolename"};
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
	private List<Role> models = new LinkedList<Role>();
	
	//系统所有的用户
	private List<User> sysUsers = new LinkedList<User>();
		
	//当前角色所有的用户
	private List<String> users = new LinkedList<String>();
	
	//系统所有的权限
	private List<Permission> sysPermissions = new LinkedList<Permission>();
		
	//当前角色所有的权限
	private List<String> permissions = new LinkedList<String>();
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		for (String field : fields) {
			properties.put(field, getText("user." + field));
		}
		setModels(userService.findRolesByProp(property, keyword));
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		setModels(userService.findRoles());
		return SUCCESS;
	}
	
	/*
	 * 关联查询
	 */
	public String queryByRef(){
		setModels(userService.findRolesByRef(refClass, refId));
		return SUCCESS;
	}
	
	/*
	 * 加载增加页面
	 */
	public String add(){
		title = "创建新";
		
		//处理关联的权限字段
		sysPermissions = userService.findPermissions();
		
		//处理关联的用户字段
		sysUsers = userService.findUsers();
		
		return INPUT;
	}
	
	/*
	 * 加载修改页面
	 */
	public String edit(){
		title = "编辑";
		
		model = userService.findRole(id);
		
		//处理关联的用户字段
		sysUsers = userService.findUsers();
		for (User user : model.getUsers()) {
			users.add(user.getId());
		}
		
		//处理关联的权限字段
		sysPermissions = userService.findPermissions();
		for (Permission permission : model.getPermissions()) {
			permissions.add(permission.getId());
		}
		
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
		
		//处理用户绑定
		Set<User> userList = new HashSet<User>();
		for (String userId : users) {
			if(!userId.equals("")){
				User user = userService.findUser(userId);
				userList.add(user);
			}
		}
		model.setUsers(userList);
		
		//处理权限绑定
		Set<Permission> permissionList = new HashSet<Permission>();
		for (String permissionId : permissions) {
			if(!permissionId.equals("")){
				Permission permission = userService.findPermission(permissionId);
				permissionList.add(permission);
			}
		}
		model.setPermissions(permissionList);
		
		
		if(model.getId().equals("")){
			//处理新建
			userService.addRole(model);
		}else{
			//处理更新
			userService.updateRole(model);
		}
		return query();
	}
	
	/*
	 * 处理删除
	 */
	public String delete(){
		for (String id : checkItems) {
			userService.deleteRole(id);
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

	public Role getModel() {
		return model;
	}

	public void setModel(Role model) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Role> getModels() {
		return models;
	}

	public void setModels(List<Role> models) {
		this.models = models;
	}

	public List<Permission> getSysPermissions() {
		return sysPermissions;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public List<User> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(List<User> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public String getRefClass() {
		return refClass;
	}

	public void setRefClass(String refClass) {
		this.refClass = refClass;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}


}
