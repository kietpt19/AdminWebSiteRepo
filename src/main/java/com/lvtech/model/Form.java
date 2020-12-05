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
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idForm;
	@Column(length = 50, nullable = false, unique = true)
	private String codeForm;
	@Column(length = 50, nullable = false, unique = true)
	private String nameForm;
	
	@JsonIgnore
	@OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
	private List<Role> roles = new ArrayList<Role>();

	public Form() {
		super();
	}

	public Form(String codeForm, String nameForm) {
		super();
		this.codeForm = codeForm;
		this.nameForm = nameForm;
	}

	public Long getIdForm() {
		return idForm;
	}

	public void setIdForm(Long idForm) {
		this.idForm = idForm;
	}

	public String getCodeForm() {
		return codeForm;
	}

	public void setCodeForm(String codeForm) {
		this.codeForm = codeForm;
	}

	public String getNameForm() {
		return nameForm;
	}

	public void setNameForm(String nameForm) {
		this.nameForm = nameForm;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
