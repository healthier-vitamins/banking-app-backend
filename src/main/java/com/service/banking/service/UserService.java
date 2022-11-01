package com.service.banking.service;

import java.util.List;

import com.service.banking.exception.RoleNotFoundException;
import com.service.banking.model.Role;
import com.service.banking.model.User;

public interface UserService {

	public User saveUser(User user);
	public Role saveRole(Role role);
	public void addRoleToUser(String username, String roleName) throws RoleNotFoundException;
	public User getUser(String username);
	public List<User> getUsers();
	public void deleteById(Long id);
	public User getUserById(Long id);
	public User getByUsername(String username);
//	public User saveUpdatedUser(User user);
}
