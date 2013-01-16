package com.dickey.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.Application;
import com.dickey.domain.ApplicationType;
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
	
	//搜索可用的字段
	private String[] fields = {"id", "start", "end", "reason"};
	private Map<String, String> properties = new HashMap<String, String>();
	
	//搜索时的字段类别
	private String property;
	
	//搜索时的关键字
	private String keyword;
	
	//页面标题
	private String title;
	
	//当前用户
	private User user = (User) ActionContext.getContext().getSession().get("user");
	
	//关联查询的类
	private String refClass;
	
	//关联查询的类的id
	private String refId;
	
	//查询出的实例集
	private List<Application> models = new LinkedList<Application>();
	
	//{Start处理多对一
	//系统所有的申请类型
	private List<ApplicationType> sysApplicationTypes = new LinkedList<ApplicationType>();
	
	//当前申请单的类型
	private String applicationType;
	//End处理多对一}
	
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		for (String field : fields) {
			properties.put(field, getText("application." + field));
		}
		boolean flag = false;
		//处理与User关联的数据
		try {
			model.getClass().getDeclaredField("user");
			flag = true;
		}  catch (Exception e) {
			System.out.println(model.getClass().getName() + "与用户无关联");
		}
		
		if(!property.trim().equals("") && !keyword.trim().equals("")){
			setModels(userService.findApplicationsByProp(property, keyword, flag, user));
		}
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		initQuery();
		setModels(userService.findApplicationsByUser(user));
		return SUCCESS;
	}
	
	/*
	 * 关联查询
	 */
	public String queryByRef(){
		initQuery();
		if(refClass != null && refId != null){
			setModels(userService.findApplicationsByRef(toLowerFirst(refClass), refId));
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
		
		//处理多对一关联的ApplicationType类
		sysApplicationTypes = userService.findApplicationTypes();
		
		return INPUT;
	}
	
	/*
	 * 加载修改页面
	 */
	public String edit(){
		title = "编辑";
		
		//处理多对一关联的ApplicationType类
		sysApplicationTypes = userService.findApplicationTypes();
		
		if(!id.trim().isEmpty()){
			model = userService.findApplication(id);
		}else if(refClass != null && refId != null){
			refClass = refClass.trim();
			refId = refId.trim();
			if(!refClass.equals("") && !refId.equals("")){
				List<Application> list = userService.findApplicationsByRef(toLowerFirst(refClass), refId);
				if(!list.isEmpty()){
					model = list.get(0);
				}
			}
		}
		return INPUT;
	}
	
	/*
	 * 处理增加/修改
	 */
	public String editSubmit(){
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
		
		if(model.getUser() == null){
			model.setUser(user);
		}
		
		//处理多对一关联的ApplicationType
		if(model.getApplicationType() == null){
			model.setApplicationType(userService.findApplicationType(applicationType));
		}
		
		if(model.getId().equals("")){
			//处理新建
			userService.addApplication(model);
		}else{
			//处理更新
			userService.updateApplication(model);
		}
		return flag ? queryByRef() : query();
	}
	
	/*
	 * 处理删除
	 */
	public String delete(){
		//是否有关联类操作
		boolean flag = !refClass.trim().equals("") && !refId.trim().equals("");
				
		for (String id : checkItems) {
			userService.delApplication(id);
		}
		return flag ? queryByRef() : query();
	}
	
	/*
	 * 初始化搜索字段
	 */
	private void initQuery(){
		for (String field : fields) {
			properties.put(field, getText("application." + field));
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

	public List<Application> getModels() {
		return models;
	}

	public void setModels(List<Application> models) {
		this.models = models;
	}

	public List<ApplicationType> getSysApplicationTypes() {
		return sysApplicationTypes;
	}

	public void setSysApplicationTypes(List<ApplicationType> sysApplicationTypes) {
		this.sysApplicationTypes = sysApplicationTypes;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}



}
