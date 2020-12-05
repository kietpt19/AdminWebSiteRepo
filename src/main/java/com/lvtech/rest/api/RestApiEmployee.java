package com.lvtech.rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvtech.model.Employee;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.repository.GroubRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/employee")
public class RestApiEmployee {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private GroubRepository groubRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@GetMapping(value = "")
	public List<Employee> getAllEmployee() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		return employeeRepository.findById(id).get();
	}

	@PostMapping("/{idGroup}")
	public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee,
			@PathVariable("idGroup") Long idGroup) {
		Employee savedEmployee = employeeRepository.save(employee);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		employeeRepository.findById(employee.getIdEmployee()).get();
		savedEmployee.setGroub(groubRepository.findById(idGroup).get());
		employee.setEmployeeCode(employee.getEmployeePhone().substring(7) + employee.getIdEmployee());
		employeeRepository.save(employee);
		return new ResponseEntity<>(savedEmployee, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		Employee employee = employeeRepository.findById(id).get();
		employeeRepository.deleteById(employee.getIdEmployee());
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody @Valid Employee employee) {
		
		Employee savedEmployee = employeeRepository.findById(id).get();
		if (!savedEmployee.getPassword().equals(employee.getPassword())) {
			employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
			savedEmployee.setPassword(employee.getPassword());
		}
		savedEmployee.setGroub(employee.getGroub());
		savedEmployee.setEmployeePhone(employee.getEmployeePhone());
		savedEmployee.setEmployeeName(employee.getEmployeeName());
		savedEmployee.setEmployeeAddress(employee.getEmployeeAddress());
		savedEmployee.setEmployeeEmail(employee.getEmployeeEmail());
		savedEmployee.setUsername(employee.getUsername());
		return employeeRepository.save(savedEmployee);
	}

	@GetMapping("/filterName/{employee}/{page}")
	public List<Employee> filterName(@PathVariable("employee") String employee, @PathVariable("page") int page) {
		return employeeRepository.filterName(employee, PageRequest.of(page, 10, Sort.by("id").descending()));
	}

	@GetMapping("/getData/{start}")
	public List<Employee> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return employeeRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = employeeRepository.getNumberLine();
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (num % 10 == 0) {
			for (int i = 0; i < num / 10; i++) {
				list.add(i);
			}
		} else {
			for (int i = 0; i < (num / 10 + 1); i++) {
				list.add(i);
			}
		}
		return list;
	}

	@GetMapping("/numberLine/{employee}")
	public List<Integer> getNumberLineForFilter(@PathVariable("employee") String employee) {
		int num = employeeRepository.getNumberLineForFilter(employee);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (num % 10 == 0) {
			for (int i = 0; i < num / 10; i++) {
				list.add(i);
			}
		} else {
			for (int i = 0; i < (num / 10 + 1); i++) {
				list.add(i);
			}
		}
		return list;
	}
}
