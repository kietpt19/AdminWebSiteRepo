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

import com.lvtech.model.Employee;
import com.lvtech.model.Introduce;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.repository.IntroduceRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/introduce")
public class RestApiIntroduce {
	@Autowired
	private IntroduceRepository introduceRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = "")
	public List<Introduce> getAllGuest() {
		return (List<Introduce>) introduceRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public Introduce getIntroduce(@PathVariable("id") Long id) {
		return introduceRepository.findById(id).get();
	}

	@PostMapping("/{employee_id}")
	public ResponseEntity<?> createIntroduce(@RequestBody @Valid Introduce introduce,
			@PathVariable("employee_id") Long employee_id) {
		
		Introduce savedIntroduce = new Introduce();
		Employee saveEmployee = employeeRepository.findById(employee_id).get();
		
		introduce.setEmployee(saveEmployee);
		
		savedIntroduce = introduceRepository.save(introduce);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedIntroduce, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteIntroduce(@PathVariable("id") Long id) {
		Introduce introduce = introduceRepository.findById(id).get();
		introduceRepository.deleteById(introduce.getIdIntroduce());
	}

	@PutMapping("/{id}")
	public Introduce updateIntroduce(@PathVariable("id") Long id, @RequestBody @Valid Introduce introduce) {
		introduceRepository.findById(id).get();
		return introduceRepository.save(introduce);
	}

	@GetMapping("/searchIntroduce/{introduce}/{page}")
	public List<Introduce> searchIntroduce(@PathVariable("introduce") String introduce,
			@PathVariable("page") int page) {
		return introduceRepository.filterByIntroduce(introduce, PageRequest.of(page, 10, Sort.by("id").descending()));
	}

	@GetMapping("/getData/{start}")
	public List<Introduce> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return introduceRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = introduceRepository.getNumberLine();
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

	@GetMapping("/numberLine/{introduce}")
	public List<Integer> getNumberLineForFilter(@PathVariable("introduce") String introduce) {
		int num = introduceRepository.getNumberLineForFilter(introduce);
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
