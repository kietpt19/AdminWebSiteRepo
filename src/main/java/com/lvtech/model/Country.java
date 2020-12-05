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
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCountry;
	@Column(length = 200, nullable = false, unique = true)
	private String nameCountry;

	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private List<Subject> subjects = new ArrayList<Subject>();

	public Country() {
		super();
	}

	public Country(String nameCountry) {
		super();
		this.nameCountry = nameCountry;
	}

	public Long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	public String getNameCountry() {
		return nameCountry;
	}

	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}
