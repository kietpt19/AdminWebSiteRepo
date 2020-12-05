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
import com.lvtech.repository.SubjectRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/subject")
public class RestApiSubject {
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private CountryRepository countryRepository;
	
	@GetMapping(value = "")
	private List<Subject> getAllSubject(){
		return (List<Subject>) subjectRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Subject getSubject(@PathVariable("id") Long id) {
		return subjectRepository.findById(id).get();
	}
	
	@PostMapping("/{country_id}")
	public ResponseEntity<?> createSubject(@RequestBody @Valid Subject subject,
			@PathVariable("country_id") Long country_id) {
		Subject savedSubject = new Subject();
		Country saveCountry = countryRepository.findById(country_id).get();
		
		subject.setCountry(saveCountry);
		savedSubject = subjectRepository.save(subject);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedSubject, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteSubject(@PathVariable("id") Long id) {
		Subject subject = subjectRepository.findById(id).get();
		subjectRepository.deleteById(subject.getIdSubject());
	}

	@PutMapping("/{id}")
	public Subject updateSubject(@PathVariable("id") Long id, @RequestBody @Valid Subject subject) {
		subjectRepository.findById(id).get();
		return subjectRepository.save(subject);
	}

	@GetMapping("/searchSubject/{nameSubject}/{page}")
	public List<Subject> searchSubjectByName(@PathVariable("nameSubject") String nameSubject,
			@PathVariable("page") int page) {
		return subjectRepository.filterByName(nameSubject, PageRequest.of(page, 12, Sort.by("id").descending()));
	}

	@GetMapping("/searchAllSubjectByCountry/{idCountry}")
	public List<Subject> searchAllSubjectByCountry(@PathVariable("idCountry") Long idCountry) {
		List<Subject> getListSubject = new ArrayList<Subject>();
		if (subjectRepository.getNumberOneSubjectByCountry(idCountry) > 1) {
			getListSubject = subjectRepository.getAllSubjectByCountry(idCountry);
		}
		return getListSubject;
	}

	@GetMapping("/getNumberOneSubjectByCountry/{idCountry}")
	public Integer getNumberOneSubjectByCountry(@PathVariable("idCountry") Long idCountry) {
		return subjectRepository.getNumberOneSubjectByCountry(idCountry);
	}
	
	@GetMapping("/searchOneSubjectByCountry/{idCountry}")
	public List<Subject> searchOneSubjectByCountry(@PathVariable("idCountry") Long idCountry) {
		List<Subject> getListSubject = new ArrayList<Subject>();
		if (subjectRepository.getNumberOneSubjectByCountry(idCountry) == 1) {
			getListSubject.add(subjectRepository.getOneSubjectByCountry(idCountry));
		}
		return getListSubject;
	}

	@GetMapping("/getIdSubject")
	public List<Integer> getIdSubject() {
		return subjectRepository.getIdSubject();
	}
	
	@GetMapping("/getData/{start}")
	public List<Subject> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 12, Sort.by("id").descending());
		return subjectRepository.getData(page);
	}
	
	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = subjectRepository.getNumberLine();
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(num % 12 == 0) {
			for(int i = 0; i < num/12; i++) {
				list.add(i);
			}
		}else{
			for(int i = 0; i < (num/12 + 1); i++) {
				list.add(i);
			}
		}
		return list;
	}
	
	@GetMapping("/numberLine/{nameSubject}")
	public List<Integer> getNumberLineForFilter(@PathVariable("nameSubject") String nameSubject) {
		int num = subjectRepository.getNumberLineForFilter(nameSubject);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(num % 12 == 0) {
			for(int i = 0; i < num/12; i++) {
				list.add(i);
			}
		}else{
			for(int i = 0; i < (num/12 + 1); i++) {
				list.add(i);
			}
		}
		return list;
	}
}
