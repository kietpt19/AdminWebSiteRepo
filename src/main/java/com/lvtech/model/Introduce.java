package com.lvtech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Introduce {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIntroduce;
	@Column(nullable = false)
	private String titleIntroduce;
	@Lob
	@Column(name = "content", columnDefinition="MEDIUMTEXT")
	private String content;

	@ManyToOne
	@JoinColumn
	private Employee employee;
	
	public Introduce() {
		super();
	}

	public Introduce(String titleIntroduce, String content) {
		super();
		this.titleIntroduce = titleIntroduce;
		this.content = content;
	}
	
	public String getTitleIntroduce() {
		return titleIntroduce;
	}
	
	public void setTitleIntroduce(String titleIntroduce) {
		this.titleIntroduce = titleIntroduce;
	}

	public Long getIdIntroduce() {
		return idIntroduce;
	}

	public void setIdIntroduce(Long idIntroduce) {
		this.idIntroduce = idIntroduce;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
