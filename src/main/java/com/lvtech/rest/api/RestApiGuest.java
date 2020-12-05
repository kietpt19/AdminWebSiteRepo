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

import com.lvtech.model.Guest;
import com.lvtech.repository.GuestRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/guest")
public class RestApiGuest {
	@Autowired
	private GuestRepository guestRepository;
	
	@GetMapping(value = "")
	public List<Guest> getAllGuest() {
		return (List<Guest>) guestRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Guest getGuest(@PathVariable("id") Long id) {
		return guestRepository.findById(id).get();
	}
	
	@PostMapping
	public ResponseEntity<?> createGuest(@RequestBody @Valid Guest guest) {
		Guest savedGuest = guestRepository.save(guest);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(guestRepository.save(savedGuest), responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteGuest(@PathVariable("id") Long id) {
		Guest guest = guestRepository.findById(id).get();
		guestRepository.deleteById(guest.getIdGuest());
	}

	@PutMapping("/{id}")
	public Guest updateGuest(@PathVariable("id") Long id, @RequestBody @Valid Guest guest) {
		guestRepository.findById(id).get();
		return guestRepository.save(guest);
	}
	
	@GetMapping("/searchGuestPhone/{phoneGuest}")
	public Guest searchGuestPhone(@PathVariable("phoneGuest") String phoneGuest) {
		return guestRepository.searchPhoneGuest(phoneGuest);
	}

	@GetMapping("/searchGuest/{guest}/{page}")
	public List<Guest> filterGuest(@PathVariable("guest") String guest, @PathVariable("page") int page) {
		return guestRepository.filterGuest(guest, PageRequest.of(page, 10, Sort.by("id").descending()));
	}

	@GetMapping("/getData/{start}")
	public List<Guest> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return guestRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = guestRepository.getNumberLine();
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

	@GetMapping("/numberLine/{guest}")
	public List<Integer> getNumberLineForFilter(@PathVariable("guest") String guest) {
		int num = guestRepository.getNumberLineForFilter(guest);
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
}
