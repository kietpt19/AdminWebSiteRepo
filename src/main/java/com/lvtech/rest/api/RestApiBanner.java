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

import com.lvtech.model.Banner;
import com.lvtech.repository.BannerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/banner")
public class RestApiBanner {
	@Autowired
	private BannerRepository bannerRepository;
	
	@GetMapping(value = "")
	public List<Banner> getAllGuest() {
		return (List<Banner>) bannerRepository.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Banner getCountries(@PathVariable("id") Long id) {
		return bannerRepository.findById(id).get();
	}
	
	@PostMapping
	public ResponseEntity<?> createBanner(@RequestBody @Valid Banner banner) {
		Banner savedCountries = bannerRepository.save(banner);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(savedCountries, responseHeaders, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void deleteBanner(@PathVariable("id") Long id) {
		Banner banner = bannerRepository.findById(id).get();
		bannerRepository.deleteById(banner.getIdBanner());
	}

	@PutMapping("/{id}")
	public Banner updateProductType(@PathVariable("id") Long id, @RequestBody @Valid Banner banner) {
		bannerRepository.findById(id).get();
		return bannerRepository.save(banner);
	}

	@GetMapping("/searchBanner/{nameBanner}/{page}")
	public List<Banner> searchBannerByName(@PathVariable("nameBanner") String nameBanner,
			@PathVariable("page") int page) {
		return bannerRepository.filterByName(nameBanner, PageRequest.of(page, 10, Sort.by("id").descending()));
	}

	@GetMapping("/getData/{start}")
	public List<Banner> getData(@PathVariable("start") int start) {
		Pageable page = PageRequest.of(start, 10, Sort.by("id").descending());
		return bannerRepository.getData(page);
	}

	@GetMapping("/numberLine")
	public List<Integer> getNumberLine() {
		int num = bannerRepository.getNumberLine();
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

	@GetMapping("/numberLine/{nameBanner}")
	public List<Integer> getNumberLineForFilter(@PathVariable("nameBanner") String nameBanner) {
		int num = bannerRepository.getNumberLineForFilter(nameBanner);
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