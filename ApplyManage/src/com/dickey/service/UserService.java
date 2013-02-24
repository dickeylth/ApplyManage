package com.dickey.service;

import java.util.List;
import java.util.Map;

import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;

import com.dickey.domain.*;

public interface UserService {
	/**
	 * 新增用户
	 * @param user 新增用户的User实例
	 * @return 新增用户的主键
	 */
	String addUser(User user);
	
	/**
	 * 删除用户
	 * @param user 要删除的用户的user实例
	 */
	void deleteUser(User user);
	
	/**
	 * 删除用户
	 * @param id 要删除的用户的user实例的id
	 */
	void deleteUser(String id);
	
	/**
	 * 更新用户
	 * @param user 要更新的用户的User实例
	 */
	void updateUser(User user);
	
	/**
	 * 根据Id查找用户
	 */
	User findUser(String id);
	
	/**
	 * 根据用户名查找用户
	 */
	User findUserByName(String username);
	
	
	/**
	 * 列举出所有用户
	 * @return 返回所有用户
	 */
	List<User> findUsers();
	
	/**
	 * 根据条件查找用户
	 * @param property 搜索属性
	 * @param keyword 搜索关键字
	 * @return 返回所有用户
	 */
	List<User> findUsersByProp(String property, String keyword);
	
	/**
	 * 根据关联类查找用户
	 * @param refClass 关联类名
	 * @param refId 关联类的id
	 * @return 返回所有用户
	 */
	List<User> findUsersByRef(String refClass, String refId);
	
	
	/**
	 * 新增角色
	 * @param role 新增角色的role实例
	 * @return 新增角色的主键
	 */
	String addRole(Role role);
	
	/**
	 * 删除角色
	 * @param role 要删除的角色的Role实例
	 */
	void deleteRole(Role role);
	
	/**
	 * 删除角色
	 * @param id 要删除的角色的Role实例的id
	 */
	void deleteRole(String id);
	
	/**
	 * 更新角色
	 * @param role 要更新的角色的Role实例
	 */
	void updateRole(Role role);
	
	/**
	 * 根据Id查找角色
	 */
	Role findRole(String id);
	
	/**
	 * 列举出所有角色
	 * @return 返回所有用户
	 */
	List<Role> findRoles();
	
	/**
	 * 根据条件查找角色
	 * @param property 搜索属性
	 * @param keyword 搜索关键字
	 * @return 返回所有角色
	 */
	List<Role> findRolesByProp(String property, String keyword);
	
	/**
	 * 根据关联类查找角色
	 * @param refClass 关联类名
	 * @param keyword 关联类实例的id
	 * @return 返回所有角色
	 */
	List<Role> findRolesByRef(String refClass, String refId);
	
	/**
	 * 新增权限
	 * @param permission 新增权限的Permission实例
	 * @return 新增权限的主键
	 */
	String addPermission(Permission permission);
	
	/**
	 * 删除权限
	 * @param permission 要删除的权限的Permission实例
	 */
	void deletePermission(Permission permission);
	
	/**
	 * 删除权限
	 * @param id 要删除的权限的Permission实例的id
	 */
	void deletePermission(String id);
	
	/**
	 * 更新权限
	 * @param permission 要更新的权限的Permission实例
	 */
	void updatePermission(Permission permission);
	
	/**
	 * 根据Id查找权限
	 */
	Permission findPermission(String id);
	
	/**
	 * 列举出所有权限
	 * @return 返回所有权限
	 */
	List<Permission> findPermissions();
	
	/**
	 * 根据条件查找权限
	 * @param property 搜索属性
	 * @param keyword 搜索关键字
	 * @return 返回所有权限
	 */
	List<Permission> findPermissionsByProp(String property, String keyword);
	
	/**
	 * 根据关联类查找权限
	 * @param refClass 关联类名
	 * @param refId 关联类实例id
	 * @return 返回所有权限
	 */
	List<Permission> findPermissionsByRef(String refClass, String refId);
	
	/**
	 * 新增申请
	 * @param application 新增申请的Application实例
	 * @return 新增申请的主键
	 */
	String addApplication(Application application);
	
	/**
	 * 删除申请
	 * @param application 要删除的申请的Application实例
	 */
	void delApplication(Application application);
	
	/**
	 * 删除申请
	 * @param id 要删除的申请的user的Application实例id
	 */
	void delApplication(String id);
	
	/**
	 * 更新用户
	 * @param application 要更新的用户的Application实例
	 */
	void updateApplication(Application application);
	
	/**
	 * 按id查找申请
	 * @return 返回申请
	 */
	Application findApplication(String id);
	
	/**
	 * 列举出所有申请
	 * @return 返回所有申请
	 */
	List<Application> findApplications();
	
	/**
	 * 根据用户列举出所有申请
	 * @param user 申请的用户
	 * @return 返回所有申请
	 */
	List<Application> findApplicationsByUser(User user);
	
	/**
	 * 根据搜索条件列举出所有申请
	 * @param property 搜索属性
	 * @param keyword 搜索关键字
	 * @param userRel 是否与当前用户相关联
	 * @param user 申请的用户(当前用户)
	 * @return 返回所有申请
	 */
	List<Application> findApplicationsByProp(String property, String keyword, boolean userRel, User user);
	
	/**
	 * 根据关联类列举出所有申请
	 * @param refClass 关联类
	 * @param refId 关联类的id
	 * @return 返回所有申请
	 */
	List<Application> findApplicationsByRef(String refClass, String refId);

	//ApplicationType CRUD
	String addApplicationType(ApplicationType applicationType);

	void delApplicationType(ApplicationType applicationType);

	void delApplicationType(String id);

	void updateApplicationType(ApplicationType applicationType);

	ApplicationType findApplicationType(String id);

	List<ApplicationType> findApplicationTypes();

	List<ApplicationType> findApplicationTypesByProp(String property,
			String keyword, boolean userRel, User user);

	List<ApplicationType> findApplicationTypesByRef(String refClass,
			String refId);
	//ApplicationType CRUD
	
	//Address CRUD
	String addAddress(Address address);

	void deleteAddress(Address address);

	void deleteAddress(String id);

	void updateAddress(Address address);

	Address findAddress(String id);

	List<Address> findAddresss();
	
	List<Address> findAddresssByUser(User user);
	
	List<Address> findAddresssByProp(String property,
			String keyword, boolean userRel, User user);

	List<Address> findAddresssByRef(String refClass,
			String refId);
	//Address CRUD

	/*
	 * 系统初始化时对流程定义文件的部署
	 */
	void checkProcessDeploy();
	/**
	 * 获取当前角色任务列表
	 * @param String bizName 业务名
	 * @param User user 用户
	 * @return Map<业务id, Task>
	 */
	Map<String, Task> getTaskList(String bizName, User user);
	
	/**
	 * 获取当前角色历史任务列表
	 * @param String bizName 业务名
	 * @param User user 用户
	 * @return Map<业务id, Task>
	 */
	Map<String, HistoryTask> getHistTaskList(String bizName, User user);
	
	/**
	 * 流程-处理申请
	 * @param String bizName 业务名
	 * @param String bizId 业务id
	 * @param User user 业务执行用户
	 * @return String 状态信息
	 */
	String procApply(String bizName, String bizId, User user) throws Exception;
	
	/**
	 * 流程-处理批准
	 * @param String taskId 任务id
	 * @return String 业务状态
	 */
	String procApprove(String taskId, User user) throws Exception;
	
	/**
	 * 流程-处理驳回
	 * @param String taskId 任务id
	 * @return String 业务状态
	 */
	String procReject(String taskId, User user) throws Exception;



}
