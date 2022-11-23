package com.service.banking.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.service.banking.exception.RoleNotFoundException;
import com.service.banking.exception.UserUsernameNotFoundException;
import com.service.banking.model.Role;
import com.service.banking.model.User;
import com.service.banking.repo.RoleRepo;
import com.service.banking.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) throw new UsernameNotFoundException(username + " not found");
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public User saveUser(User user) {
		System.out.println("[TERMINAL] -- saving user " + user.getUsername() + " --");
		return userRepo.save(user);
	}
	
	@Override
	public void deleteById(Long id) {
		userRepo.deleteById(id);
	}
	
	@Override
	public List<User> getAllUsers() {
		System.out.println("[TERMINAL] -- fetching all users --");
		return userRepo.findAll();
	}
	
	@Override
	public User getUserById(Long id) {
		return userRepo.getReferenceById(id);
	}
	
	@Override
	public User getByUsername(String username) throws UserUsernameNotFoundException{
		Optional<User> user = Optional.of(userRepo.findByUsername(username));
		if(user.isEmpty()) throw new UserUsernameNotFoundException(username);
		return user.get();
	}
	
	@Override
	public Role saveRole(Role role) {
		System.out.println("[TERMINAL] -- saving role " + role.getName() + " --");
		return roleRepo.save(role);
	}
	
	@Override
	public void addRoleToUser(String username, String roleName) throws RoleNotFoundException {
		User user = userRepo.findByUsername(username);
		Optional<Role> role = Optional.of(roleRepo.getByName(roleName));
		if(role.isEmpty()) throw new RoleNotFoundException(roleName);
		user.getRole().add(role.get());
		userRepo.save(user);
		System.out.println("[TERMINAL] -- added " + role.get().getName() + " to user " + username + " --");
	}

	@Override
	public User encodeAndSaveUser(User user) {
		String encodedPw = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPw);
		return userRepo.save(user);
	}

}
