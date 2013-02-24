package com.dickey.action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;

import com.dickey.action.base.BaseAction;
import com.dickey.domain.Application;
import com.dickey.domain.BizWorkflow;
import com.dickey.domain.User;
import com.opensymphony.xwork2.ActionContext;

public class ApplicationAction extends BaseAction{

	/**
	 * 默认序列化UID
	 */
	private static final long serialVersionUID = 1L;
	
	//需要编辑的model的id
	private String id = "";
	
	//与流程相关，审批时传入的任务id（TaskId)
	private String taskId = "";
	
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
	
	/*
	 * 按字段查询
	 */
	public String queryByProp(){
		initQuery();
		
		if(!property.trim().equals("") && !keyword.trim().equals("")){
			setModels(userService.findApplicationsByProp(property, keyword, true, user));
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
	 * 查询流程任务
	 */
	public String queryTask(){
		initQuery();
		//遍历业务表，为各业务entry设置taskId，以传给后续审批用
		List<Application> applications = new LinkedList<Application>();
		Map<String, Task> tasks = userService.getTaskList("Application", user);
		Iterator<Entry<String, Task>> iterator = tasks.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Task> entry = (Map.Entry<String, Task>) iterator.next();
			Application application = userService.findApplication(entry.getKey());
			application.getBizWorkflow().setTaskId(entry.getValue().getId());
			applications.add(application);
		}
		setModels(applications);
		return "task";
	}
	
	/*
	 * 查询流程历史任务
	 */
	public String queryHist(){
		initQuery();
		//遍历业务表，为各业务entry设置taskId，以传给后续审批用
		List<Application> applications = new LinkedList<Application>();
		Map<String, HistoryTask> tasks = userService.getHistTaskList("Application", user);
		Iterator<Entry<String, HistoryTask>> iterator = tasks.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, HistoryTask> entry = (Map.Entry<String, HistoryTask>) iterator.next();
			Application application = userService.findApplication(entry.getKey());
			application.getBizWorkflow().setTaskId(entry.getValue().getId());
			applications.add(application);
		}
		setModels(applications);
		return "hist";
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
	 * 处理流程申请
	 */
	public String apply() throws Exception{
		String processInstanceId = userService.procApply("Application", id, user);
		updateBizStatus("申请单已提交", true, processInstanceId);
		return query();
	}
	
	/*
	 * 处理流程批准
	 */
	public String approve() throws Exception{
		updateBizStatus(userService.procApprove(taskId, user), true, null);
		return "task";
	}
	
	/*
	 * 处理流程驳回
	 */
	public String reject() throws Exception{
		updateBizStatus(userService.procReject(taskId, user), false, null);
		return "task";
	}
	
	/**
	 * 处理流程中业务数据状态更新
	 * @param String status 状态名
	 * @param boolean direction 审批通过？驳回
	 * @param String processInstanceId 流程实例id，仅在申请时绑定
	 */
	private void updateBizStatus(String status, boolean direction, String processInstanceId){
		Application application = userService.findApplication(id);
		application.getBizWorkflow().setStatus(status);
		if(processInstanceId != null){
			application.getBizWorkflow().setProcessInstanceId(processInstanceId);
		}
		
		if(direction){
			application.getBizWorkflow().stepForward();
		}else{
			application.getBizWorkflow().stepBackword();
		}
		userService.updateApplication(application);
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
				System.err.println("Application中找不到set"+refClass+"方法！");
			}
			flag = true;
		}
		
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
			model.setBizWorkflow(new BizWorkflow());
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
		boolean flag = refClass != null && refId != null && !refClass.trim().equals("") && !refId.trim().equals("");
				
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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

}
