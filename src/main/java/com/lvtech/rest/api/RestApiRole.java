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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lvtech.model.Form;
import com.lvtech.model.Groub;
import com.lvtech.model.Role;
import com.lvtech.repository.FormRepository;
import com.lvtech.repository.GroubRepository;
import com.lvtech.repository.RoleRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/role")
public class RestApiRole {
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private FormRepository formRepository;
	
	@Autowired
	private GroubRepository groubRepository;
	
	@GetMapping(value = "")
	public List<Role> getRole() {
		return roleRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public Role getRole(@PathVariable("id") Long id) {
		return roleRepository.findById(id).get();
	}
	
	@PostMapping(value = "/{form_id}/{groub_id}")
	public ResponseEntity<?> createRole(@RequestBody @Valid Role role,
			@PathVariable("form_id") Long form_id,
			@PathVariable("groub_id") Long groub_id) {
		Role savedRole = new Role();

		Form saveForm = formRepository.findById(form_id).get();
		Groub saveGroub = groubRepository.findById(groub_id).get();

		savedRole.setForm(saveForm);
		savedRole.setGroub(saveGroub);
		savedRole = roleRepository.save(role);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedRole, responseHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public Role updateRole(@PathVariable("id") Long id, @RequestBody @Valid Role role) {
		roleRepository.findById(id).get();
		return roleRepository.save(role);
	}
	
	@GetMapping(value = "/getCodeForm/{id}")
	public List<String> getCodeForm(@PathVariable("id") Long id) {
		return roleRepository.getCodeForm(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		roleRepository.deleteById(id);
	}

	@GetMapping("/getData/{start}")
	public List<Role> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return roleRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = roleRepository.getNumberLine();
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
