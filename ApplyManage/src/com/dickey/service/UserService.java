package com.dickey.service;

import java.util.List;

import com.dickey.domain.Application;
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
	void delUser(User user);
	
	/**
	 * 删除用户
	 * @param id 要删除的用户的user实例的id
	 */
	void delUser(String id);
	
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
}
