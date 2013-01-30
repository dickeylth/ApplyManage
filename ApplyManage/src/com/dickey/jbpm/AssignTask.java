package com.dickey.jbpm;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

public class AssignTask implements AssignmentHandler{

	/**
	 * 默认序列化uid
	 */
	private static final long serialVersionUID = 1L;
	
	String roles;
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(roles);
	}

}
