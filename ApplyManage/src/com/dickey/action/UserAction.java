package com.dickey.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.Role;
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
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		setModels(userService.findUsersByProp(property, keyword));
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		initQuery();
		setModels(userService.findUsers());
		return SUCCESS;
	}
	
	/*
	 * 关联查询
	 */
	public String queryByRef(){
		initQuery();
		if(refClass != null && refId != null){
			setModels(userService.findUsersByRef(toLowerFirst(refClass), refId));
		}else{
			System.err.println("RefClass或RefId为空！");
		}
		
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
		
		//获取model实例
		if(!id.trim().isEmpty()){
			model = userService.findUser(id);
		}else if(refClass != null && refId != null){
			refClass = refClass.trim();
			refId = refId.trim();
			if(!refClass.equals("") && !refId.equals("")){
				List<User> list = userService.findUsersByRef(toLowerFirst(refClass), refId);
				if(!list.isEmpty()){
					model = list.get(0);
				}
			}
		}
		
		/**
		 * 针对密码，特殊处理，缓存当前编辑用户密码到session中供提交时比对
		 */
		ActionContext.getContext().getSession().put("pwd", model.getPassword());
		
				
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
		/*
		 * 针对密码，特殊处理，比对前后密码是否有修改，有修改时才重新加密
		 */
		Object pwd = ActionContext.getContext().getSession().get("pwd");
		if(pwd == null || !pwd.equals(model.getPassword())){
			String crypto = new Md5Hash(model.getPassword()).toHex(); 
			model.setPassword(crypto);
		}
		
		//处理角色绑定
		List<Role> roleList = new LinkedList<Role>();
		for (Role it : model.getRoles()) {
			if(it != null && it.getId() != null){		
				Role role = userService.findRole(it.getId());
				roleList.add(role);
			}
		}
		model.setRoles(roleList);
		
		//是否有关联类操作
		boolean flag = false;
		if(refClass != null && refId != null && !refClass.trim().equals("") && !refId.trim().equals("")){
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
			userService.addUser(model);
		}else{
			//处理更新
			userService.updateUser(model);
		}
		return flag ? queryByRef() : query();
	}
	
	/*
	 * 处理删除
	 */
	public String delete(){
		
		//是否有关联类操作
		boolean flag = refClass != null && refId != null && !refClass.trim().equals("") && !refId.trim().equals("");
		
		for (String id : checkItems) {
			userService.deleteUser(id);
		}
		return flag ? queryByRef() : query();
	}
	
	/*
	 * 初始化搜索字段
	 */
	private void initQuery(){
		for (String field : fields) {
			properties.put(field, getText("user." + field));
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
	
}
