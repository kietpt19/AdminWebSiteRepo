package com.lvtech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRole;

	@ManyToOne
	@JoinColumn
	private Groub groub;

	@ManyToOne
	@JoinColumn
	private Form form;
	
	public Role() {
		super();
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public Groub getGroub() {
		return groub;
	}

	public void setGroub(Groub groub) {
		this.groub = groub;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
}
