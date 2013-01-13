package com.dickey.action;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import com.dickey.action.base.BaseAction;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;

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
	
	//搜索时的字段类别
	private String property;
	
	//搜索时的关键字
	private String keyword;
	
	//页面标题
	private String title;
	
	//模型驱动的实例集
	private List<Role> models = new LinkedList<Role>();
	
	//系统所有的权限
	private List<Permission> sysPermissions = new LinkedList<Permission>();
		
	//当前用户所有的角色
	private List<String> permissions = new LinkedList<String>();
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
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
	 * 加载增加页面
	 */
	public String add(){
		title = "创建新";
		sysPermissions = userService.findPermissions();
		return INPUT;
	}
	
	/*
	 * 加载修改页面
	 */
	public String edit(){
		title = "编辑";
		sysPermissions = userService.findPermissions();
		model = userService.findRole(id);
		
		for (Permission permission : model.getPermissions()) {
			permissions.add(permission.getId());
		}
		System.out.println(permissions);
		
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
		
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


}
