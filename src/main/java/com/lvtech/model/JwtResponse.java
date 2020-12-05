package com.lvtech.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private Long idEmployee;
	private String employeeName;
	private String employeeAddress;
	private String employeePhone;
	private String employeeEmail;
	private String username;
	private String password;
	private Long idGroub;

//	public JwtResponse(String jwttoken, Long idEmployee, String employeeName, Long idGroub) {
//		this.jwttoken = jwttoken;
//		this.idEmployee = idEmployee;
//		this.employeeName = employeeName;
//		this.idGroub = idGroub;
//	}

	public JwtResponse(String jwttoken, Long idEmployee, String employeeName, String employeeAddress,
			String employeePhone, String employeeEmail, String username, String password, Long idGroub) {
		super();
		this.jwttoken = jwttoken;
		this.idEmployee = idEmployee;
		this.employeeName = employeeName;
		this.employeeAddress = employeeAddress;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
		this.username = username;
		this.password = password;
		this.idGroub = idGroub;
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

	public String getJwttoken() {
		return jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public Long getIdGroub() {
		return idGroub;
	}

	public void setIdGroub(Long idGroub) {
		this.idGroub = idGroub;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}