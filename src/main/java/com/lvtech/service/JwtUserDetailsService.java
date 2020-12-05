package com.lvtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lvtech.model.AdminDto;
import com.lvtech.model.Employee;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.repository.GroubRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private EmployeeRepository userDao;
	
	@Autowired
	private GroubRepository groubRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername()
				, user.getPassword(),
				new ArrayList<>());
	}

	public Employee save(AdminDto user) {
		Employee newUser = new Employee();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmployeeAddress(user.getEmployeeAddress());
		newUser.setEmployeeEmail(user.getEmployeeEmail());
		newUser.setEmployeeName(user.getEmployeeName());
		newUser.setEmployeePhone(user.getEmployeePhone());
		newUser = userDao.save(newUser);
		newUser.setGroub(groubRepository.findById(user.getIdGroub()).get());
		newUser.setEmployeeCode(newUser.getEmployeePhone().substring(7) + newUser.getIdEmployee());
		return userDao.save(newUser);
	}
}