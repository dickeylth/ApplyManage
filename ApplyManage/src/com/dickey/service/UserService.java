package com.dickey.service;

import java.util.List;

import com.dickey.domain.Application;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;
import com.dickey.domain.User;

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
}
