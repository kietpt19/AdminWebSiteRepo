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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmployee;
	@Column(length = 50, nullable = false)
	private String employeeCode = "";
	@Column(length = 50, nullable = false)
	private String employeeName;
	@Column(length = 100, nullable = false)
	private String employeeAddress;
	@Column(length = 50, nullable = false)
	private String employeePhone;
	@Column(length = 50, nullable = false)
	private String employeeEmail;
	@Column(length = 50, unique = true, nullable = false)
	private String username;
	@Column(length = 100, nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn
	private Groub groub;

	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Introduce> introduces = new ArrayList<Introduce>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<Post>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Event> events = new ArrayList<Event>();

	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<OtherInfo> otherInfos = new ArrayList<OtherInfo>();

	public Employee() {
		super();
	}

	public Employee(String employeeCode, String employeeName, String employeeAddress, String employeePhone,
			String employeeEmail, String username, String password) {
		super();
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
		this.username = username;
		this.password = password;
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
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

	public Groub getGroub() {
		return groub;
	}

	public void setGroub(Groub groub) {
		this.groub = groub;
	}

	public List<Introduce> getIntroduces() {
		return introduces;
	}

	public void setIntroduces(List<Introduce> introduces) {
		this.introduces = introduces;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<OtherInfo> getOtherInfos() {
		return otherInfos;
	}

	public void setOtherInfos(List<OtherInfo> otherInfos) {
		this.otherInfos = otherInfos;
	}
}
