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

import com.lvtech.model.Country;
import com.lvtech.model.Subject;
import com.lvtech.repository.CountryRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/country")
public class RestApiCountry {
	@Autowired
	private CountryRepository countryRepository;
	
	@GetMapping(value = "")
	public List<Country> getAllGuest() {
		return (List<Country>) countryRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Country getCountries(@PathVariable("id") Long id) {
		return countryRepository.findById(id).get();
	}
	
	@PostMapping
	public ResponseEntity<?> createCountry(@RequestBody @Valid Country country) {
		Country savedCountries = countryRepository.save(country);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedCountries, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteCountry(@PathVariable("id") Long id) {
		Country country = countryRepository.findById(id).get();
		countryRepository.deleteById(country.getIdCountry());
	}

	@PutMapping("/{id}")
	public Country updateCountry(@PathVariable("id") Long id, @RequestBody @Valid Country country) {
		countryRepository.findById(id).get();
		return countryRepository.save(country);
	}

	@GetMapping("/searchCountry/{nameCountry}/{page}")
	public List<Country> searchCountryByName(@PathVariable("nameCountry") String nameCountry,
			@PathVariable("page") int page) {
		return countryRepository.filterByName(nameCountry, PageRequest.of(page, 10, Sort.by("id").descending()));
	}

	@GetMapping("/getSubjectByCountry/{idCountry}")
	public List<Subject> getSubjectByCountry(@PathVariable("idCountry") Long idCountry) {
		return countryRepository.getSubjectByCountry(idCountry);
	}

	@GetMapping("/getData/{start}")
	public List<Country> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return countryRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = countryRepository.getNumberLine();
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

	@GetMapping("/numberLine/{nameCountry}")
	public List<Integer> getNumberLineForFilter(@PathVariable("nameCountry") String nameCountry) {
		int num = countryRepository.getNumberLineForFilter(nameCountry);
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
