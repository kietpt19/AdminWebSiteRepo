package com.lvtech.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Groub {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGroub;
	@Column(nullable = false, unique = true)
	private String nameGroub;
	
	@JsonIgnore
	@OneToMany(mappedBy = "groub", cascade = CascadeType.ALL)
	private List<Employee> employees = new ArrayList<Employee>();

	@JsonIgnore
	@OneToMany(mappedBy = "groub", cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<Role>();

	public Groub() {
		super();
	}

	public Groub(String nameGroub) {
		super();
		this.nameGroub = nameGroub;
	}

	public Long getIdGroub() {
		return idGroub;
	}

	public void setIdGroub(Long idGroub) {
		this.idGroub = idGroub;
	}

	public String getNameGroub() {
		return nameGroub;
	}

	public void setNameGroub(String nameGroub) {
		this.nameGroub = nameGroub;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
