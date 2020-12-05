package com.lvtech.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.lvtech.config.JwtTokenUtil;
import com.lvtech.model.AdminDto;
import com.lvtech.model.Employee;
import com.lvtech.model.JwtRequest;
import com.lvtech.model.JwtResponse;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.service.JwtUserDetailsService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		Employee employee = employeeRepository.findByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final Long idEmployee = employee.getIdEmployee();
		final String employeeName = employee.getEmployeeName();
		final String employeeAddress = employee.getEmployeeAddress();
		final String employeePhone = employee.getEmployeePhone();
		final String employeeEmail = employee.getEmployeeEmail();
		final String username = employee.getUsername();
		final String password = employee.getPassword();
		final Long idGroub = employee.getGroub().getIdGroub();

		return ResponseEntity.ok(new JwtResponse(token ,idEmployee ,employeeName ,employeeAddress,
				employeePhone ,employeeEmail ,username,password,idGroub));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody AdminDto user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}
	
	@RequestMapping(value = "/register/{groub_id}", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody AdminDto user,
			@PathVariable("groub_id") Long groub_id) throws Exception {
		user.setIdGroub(groub_id);
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}