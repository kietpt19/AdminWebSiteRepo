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
import com.lvtech.repository.FormRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/form")
public class RestApiForm {
	
	@Autowired
	private FormRepository formRepository;
	
	@GetMapping(value = "")
	public List<Form> getAllGuest() {
		return (List<Form>) formRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Form getForm(@PathVariable("id") Long id) {
		return formRepository.findById(id).get();
	}
	
	@PostMapping
	public ResponseEntity<?> createForm(@RequestBody @Valid Form form) {
		Form savedForm = formRepository.save(form);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedForm, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteForm(@PathVariable("id") Long id) {
		Form form = formRepository.findById(id).get();
		formRepository.deleteById(form.getIdForm());
	}

	@PutMapping("/{id}")
	public Form updateForm(@PathVariable("id") Long id, @RequestBody @Valid Form form) {
		formRepository.findById(id).get();
		return formRepository.save(form);
	}

	@GetMapping("/searchForm/{codeForm}/{page}")
	public List<Form> searchFormBycodeForm(@PathVariable("codeForm") String codeForm,
			@PathVariable("page") int page) {
		return formRepository.filterByCodeForm(codeForm, PageRequest.of(page, 10, Sort.by("id").descending()));
	}

	@GetMapping("/getData/{start}")
	public List<Form> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return formRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = formRepository.getNumberLine();
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

	@GetMapping("/numberLine/{codeForm}")
	public List<Integer> getNumberLineForFilter(@PathVariable("codeForm") String codeForm) {
		int num = formRepository.getNumberLineForFilter(codeForm);
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
