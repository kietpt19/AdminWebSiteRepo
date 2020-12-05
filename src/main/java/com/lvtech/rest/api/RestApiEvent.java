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
import com.lvtech.model.Event;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.repository.EventRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/event")
public class RestApiEvent {
	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(value = "")
	private List<Event> getAllEvent(){
		return (List<Event>) eventRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Event getEvent(@PathVariable("id") Long id) {
		return eventRepository.findById(id).get();
	}
	
	@PostMapping("/{employee_id}")
	public ResponseEntity<?> createEvent(@RequestBody @Valid Event Event,
			@PathVariable("employee_id") Long employee_id) {
		Event savedEvent = new Event();
		Employee saveEmployee = employeeRepository.findById(employee_id).get();
		
		Event.setEmployee(saveEmployee);
		savedEvent = eventRepository.save(Event);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedEvent, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable("id") Long id) {
		Event Event = eventRepository.findById(id).get();
		eventRepository.deleteById(Event.getIdEvent());
	}

	@PutMapping("/{id}")
	public Event updateEvent(@PathVariable("id") Long id, @RequestBody @Valid Event Event) {
		eventRepository.findById(id).get();
		return eventRepository.save(Event);
	}
	
	@GetMapping("/searchEvent/{titleEvent}/{page}")
	public List<Event> searchEventByTitle(@PathVariable("titleEvent") String titleEvent,
			@PathVariable("page") int page) {
		return eventRepository.filterByName(titleEvent, PageRequest.of(page, 3, Sort.by("id").descending()));
	}
	
	@GetMapping("/getData/{start}")
	public List<Event> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return eventRepository.getData(page);
	}
	
	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = eventRepository.getNumberLine();
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(num % 10 == 0) {
			for(int i = 0; i < num/10; i++) {
				list.add(i);
			}
		}else{
			for(int i = 0; i < (num/10 + 1); i++) {
				list.add(i);
			}
		}
		return list;
	}
	
	@GetMapping("/getDataInClient/{start}")
	public List<Event> getDataInClient(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 3, Sort.by("id").descending());
		return eventRepository.getData(page);
	}
	
	@GetMapping("/numberLineInClient")
	public List<Integer> getNumberLineInClient() {
		int num = eventRepository.getNumberLine();
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(num % 3 == 0) {
			for(int i = 0; i < num/3; i++) {
				list.add(i);
			}
		}else{
			for(int i = 0; i < (num/3 + 1); i++) {
				list.add(i);
			}
		}
		return list;
	}
	
	@GetMapping("/numberLine/{titleEvent}")
	public List<Integer> getNumberLineForFilter(@PathVariable("titleEvent") String titleEvent) {
		int num = eventRepository.getNumberLineForFilter(titleEvent);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(num % 3 == 0) {
			for(int i = 0; i < num/3; i++) {
				list.add(i);
			}
		}else{
			for(int i = 0; i < (num/3 + 1); i++) {
				list.add(i);
			}
		}
		return list;
	}
}
