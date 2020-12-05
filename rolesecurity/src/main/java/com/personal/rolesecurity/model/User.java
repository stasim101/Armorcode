package com.personal.rolesecurity.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SEC_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	@OneToOne(cascade = CascadeType.MERGE)
	private Role role;

	public User() {
	}

	public User(String name, String email, Role role) {
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Name : " + this.name + "\r" + "Email : " + this.email + "\r" + "Designation : "
				+ this.role.getDesignation() + "\r" + "Token : " + this.role.getToken()+"\r"+ "Organization : "
				+ this.role.getOrganization();
	}
}
