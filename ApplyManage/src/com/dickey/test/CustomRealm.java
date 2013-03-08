package com.dickey.test;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.Hibernate;

import com.dickey.dao.UserDao;
import com.dickey.domain.Permission;
import com.dickey.domain.Role;
import com.dickey.domain.User;

public class CustomRealm extends AuthorizingRealm {

	private UserDao userDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//UsernamePasswordToken token = (UsernamePasswordToken) principals.fromRealm(getName()).iterator().next();
		
		String username = (String) principals.fromRealm(getName()).iterator().next();
		//System.out.println(username);
		
		User user = userDao.findUserByName(username);

		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			Hibernate.initialize(user.getRoles());
			for (Role role : user.getRoles()) {
				Hibernate.initialize(role.getPermissions());
				List<Permission> permissions = role.getPermissions();
				info.addRole(role.getName());

				for (Permission permission : permissions) {
					info.addStringPermission(permission.getPermission());
				}
			}
			return info;
		} else {
			return null;
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		// TODO Auto-generated method stub
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userDao.findUserByName(token.getUsername());
		System.out.println(user);
		if (user != null) {
			//String password = new Md5Hash(user.getPassword()).toString();
			System.out.println(user.getPassword());
			return new SimpleAuthenticationInfo(
					user.getUsername(), user.getPassword(), getName());
		} else {
			return null;
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
