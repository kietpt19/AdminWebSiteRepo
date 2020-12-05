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
import com.lvtech.model.OtherInfo;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.repository.OtherInfoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/otherInfo")
public class RestApiOtherInfo {
	@Autowired
	private OtherInfoRepository otherInfoRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = "")
	public List<OtherInfo> getAllGuest() {
		return (List<OtherInfo>) otherInfoRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public OtherInfo getOtherInfo(@PathVariable("id") Long id) {
		return otherInfoRepository.findById(id).get();
	}

	@PostMapping("/{employee_id}")
	public ResponseEntity<?> createOtherInfo(@RequestBody @Valid OtherInfo otherInfo,
			@PathVariable("employee_id") Long employee_id) {
		
		OtherInfo savedOtherInfo = new OtherInfo();
		Employee saveEmployee = employeeRepository.findById(employee_id).get();

		otherInfo.setEmployee(saveEmployee);

		savedOtherInfo = otherInfoRepository.save(otherInfo);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedOtherInfo, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteOtherInfo(@PathVariable("id") Long id) {
		OtherInfo otherInfo = otherInfoRepository.findById(id).get();
		otherInfoRepository.deleteById(otherInfo.getIdOtherInfo());
	}

	@PutMapping("/{id}")
	public OtherInfo updateOtherInfo(@PathVariable("id") Long id, @RequestBody @Valid OtherInfo otherInfo) {
		otherInfoRepository.findById(id).get();
		return otherInfoRepository.save(otherInfo);
	}

	@GetMapping("/getData/{start}")
	public List<OtherInfo> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return otherInfoRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = otherInfoRepository.getNumberLine();
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
