package com.lvtech.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSubject;
	@Column(nullable = false)
	private String nameSubject;

	@ManyToOne
	@JoinColumn
	private Country country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<Post>();

	public Subject() {
		super();
	}

	public Subject(String nameSubject) {
		super();
		this.nameSubject = nameSubject;
	}

	public Long getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(Long idSubject) {
		this.idSubject = idSubject;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
