package com.dickey.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;

public class PermissionAction extends BaseAction{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	//需要编辑的model的id
	private String id = "";
	
	//模型驱动的实例
	private Permission model = new Permission();
	
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
	private List<Permission> models = new LinkedList<Permission>();
	
	//系统所有的角色
	private List<Role> sysRoles = new LinkedList<Role>();
	
	//当前权限所对应的角色
	private List<String> roles = new LinkedList<String>();
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		for (String field : fields) {
			properties.put(field, getText("user." + field));
		}
		setModels(userService.findPermissionsByProp(property, keyword));
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		for (String field : fields) {
			properties.put(field, getText("user." + field));
		}
		setModels(userService.findPermissions());
		return SUCCESS;
	}
	
	/*
	 * 关联查询
	 */
	public String queryByRef(){
		setModels(userService.findPermissionsByRef(refClass, refId));
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
		
		model = userService.findPermission(id);
		
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
		
		//处理角色绑定
		Set<Role> roleList = new HashSet<Role>();
		for (String roleId : roles) {
			if(!roleId.equals("")){
				Role role = userService.findRole(roleId);
				roleList.add(role);
			}
		}
		model.setRoles(roleList);
		
		//是否有关联类操作
		boolean flag = false;
		if(!refClass.trim().equals("") && !refId.trim().equals("")){
			Object object = null;
			try {
				Method method = userService.getClass().getDeclaredMethod("find"+refClass, String.class);
				object = method.invoke(userService, refId.trim());
			} catch (Exception e) {
				System.err.println("UserService中找不到find"+refClass+"方法！");
			}
			
			try {
				Class<?> clazz = Class.forName("com.dickey.domain." + refClass.trim());
				Method method = model.getClass().getDeclaredMethod("set"+refClass.trim(), clazz);
				method.invoke(model, object);
			} catch (Exception e) {
				System.err.println("Application中找不到set"+refClass+"方法！");
			}
			flag = true;
			
		}
		
		if(model.getId().equals("")){
			//处理新建
			userService.addPermission(model);
		}else{
			//处理更新
			userService.updatePermission(model);
		}
		return flag ? queryByRef() : query();
	}
	
	/*
	 * 处理删除
	 */
	public String delete(){
		for (String id : checkItems) {
			userService.deletePermission(id);
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

	public List<Permission> getModels() {
		return models;
	}

	public void setModels(List<Permission> models) {
		this.models = models;
	}

	public Permission getModel() {
		return model;
	}

	public void setModel(Permission model) {
		this.model = model;
	}

	public List<Role> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(List<Role> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
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
