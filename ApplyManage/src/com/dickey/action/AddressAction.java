package com.dickey.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.Address;
import com.dickey.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class AddressAction extends BaseAction{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	//需要编辑的model的id
	private String id = "";
	
	//模型驱动的实例
	private Address model = new Address();
	
	//删除时的选中项的id
	private String[] checkItems;
	
	//搜索可用的字段
	private String[] fields = {"id", "country", "city"};
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
	private List<Address> models = new LinkedList<Address>();
	
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		initQuery();
		boolean flag = false;
		//处理与User关联的数据
		try {
			model.getClass().getDeclaredField("user");
			flag = true;
		}  catch (Exception e) {
			System.out.println(model.getClass().getName() + "与用户无关联");
		}
		
		if(!property.trim().equals("") && !keyword.trim().equals("")){
			setModels(userService.findAddresssByProp(property, keyword, flag, user));
		}
		return SUCCESS;
	}
	
	/*
	 * 查询
	 */
	public String query(){
		initQuery();
		setModels(userService.findAddresssByUser(user));
		return SUCCESS;
	}
	
	/*
	 * 关联查询
	 */
	public String queryByRef(){
		
		initQuery();
		if(refClass != null && refId != null){
			setModels(userService.findAddresssByRef(toLowerFirst(refClass), refId));
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
		if(!id.trim().isEmpty()){
			model = userService.findAddress(id);
		}else if(refClass != null && refId != null){
			refClass = refClass.trim();
			refId = refId.trim();
			if(!refClass.equals("") && !refId.equals("")){
				List<Address> list = userService.findAddresssByRef(toLowerFirst(refClass), refId);
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
		//是否有关联类操作（只在一对一情况下会进入）
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
				System.err.println("Address中找不到set"+refClass+"方法！");
			}
			flag = true;
		}
		
		if(model.getUser() == null){
			model.setUser(user);
		}
		
		if(model.getId().equals("")){
			//处理新建
			userService.addAddress(model);
		}else{
			//处理更新
			userService.updateAddress(model);
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
			userService.deleteAddress(id);
		}
		return flag ? queryByRef() : query();
	}
	
	/*
	 * 初始化搜索字段
	 */
	private void initQuery(){
		for (String field : fields) {
			properties.put(field, getText("address." + field));
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

	public Address getModel() {
		return model;
	}

	public void setModel(Address model) {
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

	public List<Address> getModels() {
		return models;
	}

	public void setModels(List<Address> models) {
		this.models = models;
	}


}
