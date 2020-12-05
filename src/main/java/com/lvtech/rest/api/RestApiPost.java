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
import com.lvtech.model.Post;
import com.lvtech.model.Subject;
import com.lvtech.repository.EmployeeRepository;
import com.lvtech.repository.PostRepository;
import com.lvtech.repository.SubjectRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/post")
public class RestApiPost {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(value = "")
	private List<Post> getAllPost(){
		return (List<Post>) postRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Post getPost(@PathVariable("id") Long id) {
		return postRepository.findById(id).get();
	}
	
	@PostMapping("/{subject_id}/{employee_id}")
	public ResponseEntity<?> createPost(@RequestBody @Valid Post post,
			@PathVariable("subject_id") Long subject_id,
			@PathVariable("employee_id") Long employee_id) {
		Post savedPost = new Post();
		Subject saveSubject = subjectRepository.findById(subject_id).get();
		Employee saveEmployee = employeeRepository.findById(employee_id).get();
		
		post.setSubject(saveSubject);
		post.setEmployee(saveEmployee);
		savedPost = postRepository.save(post);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedPost, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deletePost(@PathVariable("id") Long id) {
		Post post = postRepository.findById(id).get();
		postRepository.deleteById(post.getIdPost());
	}

	@PutMapping("/{id}")
	public Post updatePost(@PathVariable("id") Long id, @RequestBody @Valid Post post) {
		postRepository.findById(id).get();
		return postRepository.save(post);
	}
	
	@GetMapping("/searchPost/{titlePost}/{page}")
	public List<Post> searchPostByTitle(@PathVariable("titlePost") String titlePost,
			@PathVariable("page") int page) {
		return postRepository.filterByName(titlePost, PageRequest.of(page, 3, Sort.by("id").descending()));
	}
	
	@GetMapping("/getPostBySubject/{subject_id}/{page}")
	public List<Post> getPostBySubject(@PathVariable("subject_id") Long subject_id,
			@PathVariable("page") int page) {
		return postRepository.getPostBySubject(subject_id, PageRequest.of(page, 10, Sort.by("id").descending()));
	}
	
	@GetMapping("/getPostByCountry/{country_id}/{page}")
	public List<Post> getPostByCountry(@PathVariable("country_id") Long country_id,
			@PathVariable("page") int page) {
		return postRepository.getPostByCountry(country_id, PageRequest.of(page, 3, Sort.by("id").descending()));
	}
	
	@GetMapping("/getData/{start}")
	public List<Post> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return postRepository.getData(page);
	}
	
	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = postRepository.getNumberLine();
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
	public List<Post> getDataInClient(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 3, Sort.by("id").descending());
		return postRepository.getData(page);
	}
	
	@GetMapping("/numberLineInClient")
	public List<Integer> getNumberLineInClient() {
		int num = postRepository.getNumberLine();
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
	
	@GetMapping("/numberLine/{titlePost}")
	public List<Integer> getNumberLineForFilter(@PathVariable("titlePost") String titlePost) {
		int num = postRepository.getNumberLineForFilter(titlePost);
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
	
	@GetMapping("/numberLine/country/{id}")
	public List<Integer> getNumberLineForCountry(@PathVariable("id") Long id) {
		int num = postRepository.getNumberLineForCountry(id);
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
	
	@GetMapping("/numberLine/subject/{id}")
	public List<Integer> getNumberLineForSubject(@PathVariable("id") Long id) {
		int num = postRepository.getNumberLineForSubject(id);
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
