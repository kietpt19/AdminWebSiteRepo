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

import com.lvtech.model.Groub;
import com.lvtech.repository.GroubRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/group")
public class RestApiGroub {
	
	@Autowired
	private GroubRepository groubRepository;
	
	@GetMapping(value = "")
	public List<Groub> getAllGuest() {
		return (List<Groub>) groubRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Groub getCountries(@PathVariable("id") Long id) {
		return groubRepository.findById(id).get();
	}
	
	@PostMapping
	public ResponseEntity<?> createGroub(@RequestBody @Valid Groub groub) {
		Groub savedGroub = groubRepository.save(groub);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedGroub, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteGroub(@PathVariable("id") Long id) {
		Groub groub = groubRepository.findById(id).get();
		groubRepository.deleteById(groub.getIdGroub());
	}

	@PutMapping("/{id}")
	public Groub updateGroub(@PathVariable("id") Long id, @RequestBody @Valid Groub groub) {
		groubRepository.findById(id).get();
		return groubRepository.save(groub);
	}

	@GetMapping("/searchGroub/{nameGroub}/{page}")
	public List<Groub> searchGroubByName(@PathVariable("nameGroub") String nameGroub,
			@PathVariable("page") int page) {
		return groubRepository.filterByName(nameGroub, PageRequest.of(page, 10, Sort.by("id").descending()));
	}

	@GetMapping("/getData/{start}")
	public List<Groub> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return groubRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = groubRepository.getNumberLine();
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

	@GetMapping("/numberLine/{nameGroub}")
	public List<Integer> getNumberLineForFilter(@PathVariable("nameGroub") String nameGroub) {
		int num = groubRepository.getNumberLineForFilter(nameGroub);
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
