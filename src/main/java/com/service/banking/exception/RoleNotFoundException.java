package com.service.banking.exception;

@SuppressWarnings("serial")
public class RoleNotFoundException extends Exception{
	
	private String roleName;

	public RoleNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RoleNotFoundException(String roleName) {
		super();
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "[TERMINAL] -- RoleNotFoundException " + roleName + "--";
	}
	
}
