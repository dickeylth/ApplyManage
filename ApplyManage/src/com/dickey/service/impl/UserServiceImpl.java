package com.dickey.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;

import com.dickey.dao.AddressDao;
import com.dickey.dao.ApplicationDao;
import com.dickey.dao.ApplicationTypeDao;
import com.dickey.dao.PermissionDao;
import com.dickey.dao.RoleDao;
import com.dickey.dao.UserDao;
import com.dickey.domain.Address;
import com.dickey.domain.Application;
import com.dickey.domain.ApplicationType;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;
import com.dickey.domain.User;
import com.dickey.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	private ApplicationDao applicationDao;
	private RoleDao roleDao;
	private PermissionDao permissionDao;
	private ApplicationTypeDao applicationTypeDao;
	private AddressDao addressDao;
	
	//jBPM
	private ProcessEngine processEngine;
	private RepositoryService repositoryService;
	private ExecutionService executionService;
	private TaskService taskService;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public ApplicationDao getApplicationDao() {
		return applicationDao;
	}

	public void setApplicationDao(ApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public ApplicationTypeDao getApplicationTypeDao() {
		return applicationTypeDao;
	}

	public void setApplicationTypeDao(ApplicationTypeDao applicationTypeDao) {
		this.applicationTypeDao = applicationTypeDao;
	}

	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public ExecutionService getExecutionService() {
		return executionService;
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	/*------------- End of Getters & Setters -----------------*/

	@Override
	public String addUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public void deleteUser(String id) {
		userDao.delete(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	@Override
	public User findUser(String id) {
		return userDao.get(id);
	}
	
	@Override
	public List<User> findUsers() {
		return userDao.getAll();
	}

	@Override
	public List<User> findUsersByProp(String property, String keyword) {
		return userDao.findByProp(property, keyword);
	}

	@Override
	public List<User> findUsersByRef(String refClass, String refId) {
		return userDao.findByRef(refClass, refId);
	}
	

	@Override
	public String addRole(Role role) {
		return roleDao.save(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleDao.delete(role);
	}

	@Override
	public void deleteRole(String id) {
		roleDao.delete(id);
	}

	@Override
	public void updateRole(Role role) {
		roleDao.update(role);
	}

	@Override
	public Role findRole(String id) {
		return roleDao.get(id);
	}

	@Override
	public List<Role> findRoles() {
		return roleDao.getAll();
	}

	@Override
	public List<Role> findRolesByProp(String property, String keyword) {
		return roleDao.findByProp(property, keyword);
	}

	@Override
	public List<Role> findRolesByRef(String refClass, String refId) {
		return roleDao.findByRef(refClass, refId);
	}


	@Override
	public String addPermission(Permission permission) {
		return permissionDao.save(permission);
	}

	@Override
	public void deletePermission(Permission permission) {
		permissionDao.delete(permission);
	}

	@Override
	public void deletePermission(String id) {
		permissionDao.delete(id);
	}

	@Override
	public void updatePermission(Permission permission) {
		permissionDao.update(permission);
	}

	@Override
	public Permission findPermission(String id) {
		return permissionDao.get(id);
	}

	@Override
	public List<Permission> findPermissions() {
		return permissionDao.getAll();
	}

	@Override
	public List<Permission> findPermissionsByProp(String property, String keyword) {
		return permissionDao.findByProp(property, keyword);
	}

	@Override
	public List<Permission> findPermissionsByRef(String refClass, String refId) {
		return permissionDao.findByRef(refClass, refId);
	}
	
	@Override
	public String addApplication(Application application) {
		return applicationDao.save(application);
	}

	@Override
	public void delApplication(Application application) {
		applicationDao.delete(application);
	}

	@Override
	public void delApplication(String id) {
		applicationDao.delete(id);
	}

	@Override
	public void updateApplication(Application application) {
		applicationDao.update(application);
	}

	@Override
	public Application findApplication(String id) {
		return applicationDao.get(id);
	}
	
	@Override
	public List<Application> findApplications() {
		return applicationDao.getAll();
	}

	@Override
	public List<Application> findApplicationsByUser(User user) {
		return applicationDao.findByUser(user);
	}

	@Override
	public List<Application> findApplicationsByProp(String property, String keyword, boolean userRel, User user) {
		return applicationDao.findByProp(property, keyword, userRel, user);
	}
	
	@Override
	public List<Application> findApplicationsByRef(String refClass, String refId) {
		return applicationDao.findByRef(refClass, refId);
	}
	
	
	@Override
	public String addApplicationType(ApplicationType applicationType) {
		return applicationTypeDao.save(applicationType);
	}

	@Override
	public void delApplicationType(ApplicationType applicationType) {
		applicationTypeDao.delete(applicationType);
	}

	@Override
	public void delApplicationType(String id) {
		applicationTypeDao.delete(id);
	}

	@Override
	public void updateApplicationType(ApplicationType applicationType) {
		applicationTypeDao.update(applicationType);
	}

	@Override
	public ApplicationType findApplicationType(String id) {
		return applicationTypeDao.get(id);
	}
	
	@Override
	public List<ApplicationType> findApplicationTypes() {
		return applicationTypeDao.getAll();
	}

	@Override
	public List<ApplicationType> findApplicationTypesByProp(String property, String keyword, boolean userRel, User user) {
		return applicationTypeDao.findByProp(property, keyword, userRel, user);
	}

	@Override
	public List<ApplicationType> findApplicationTypesByRef(String refClass, String refId) {
		return applicationTypeDao.findByRef(refClass, refId);
	}

	@Override
	public String addAddress(Address address) {
		return addressDao.save(address);
	}

	@Override
	public void deleteAddress(Address address) {
		addressDao.delete(address);
	}

	@Override
	public void deleteAddress(String id) {
		addressDao.delete(id);
	}

	@Override
	public void updateAddress(Address address) {
		addressDao.update(address);
	}

	@Override
	public Address findAddress(String id) {
		return addressDao.get(id);
	}

	@Override
	public List<Address> findAddresss() {
		return addressDao.getAll();
	}

	@Override
	public List<Address> findAddresssByProp(String property, String keyword,
			boolean userRel, User user) {
		return addressDao.findByProp(property, keyword, userRel, user);
	}

	@Override
	public List<Address> findAddresssByRef(String refClass, String refId) {
		return addressDao.findByRef(refClass, refId);
	}

	@Override
	public List<Address> findAddresssByUser(User user) {
		return addressDao.findByUser(user);
	}
	
	@Override
	public void checkProcessDeploy(){
		//获取当前系统中已部署流程
		List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().list();
		List<String> processList = new LinkedList<>();
		boolean flag = false;
		if(definitions.isEmpty()){
			flag = true;
		}else{
			for (ProcessDefinition processDefinition : definitions) {
				processList.add(processDefinition.getName());
			}
		}
		
		System.out.println("流程定义count：" + processList.size());
		//遍历jpdl目录下流程定义文件，检查每个文件是否已被部署过
		List<File> files = getProcessFiles();
		System.out.println("流程定义文件count：" + files.size());
		for (File file : files) {
			String name = file.getName().substring(0, file.getName().indexOf("."));
			if(flag || !processList.contains(name)){
				//未被部署，执行流程部署操作
				deployProcess(file);
			}
		}
	}
	private List<File> getProcessFiles(){
		File dir = new File(getClass().getResource("/jpdl").getFile());
		File[] files = {};
		if(dir.exists()){
			files = dir.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".jpdl.xml");
				}
			});
		}
		
		return Arrays.asList(files);
	}
	private void deployProcess(File file){
		String deployId = repositoryService.createDeployment().addResourceFromFile(file).deploy();
		System.out.println(file.getName() + "流程定义文件已部署，id为：" + deployId);
	}

	
	

}
