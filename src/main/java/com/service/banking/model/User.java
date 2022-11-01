package com.service.banking.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@Column(name = "account_id")
	private Long bankAccId;
	private String name;
	@Column(unique = true)
	private String username;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> role;

	public User() {
		super();
	}

	public User(Long bankAccId, String name, String username, String password, Set<Role> role) {
		super();
		this.bankAccId = bankAccId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getBankAccId() {
		return bankAccId;
	}

	public void setBankAccId(Long bankAccId) {
		this.bankAccId = bankAccId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankAccId, name, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(bankAccId, other.bankAccId) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [bankAccId=" + bankAccId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", role=" + role + "]";
	}
	
}