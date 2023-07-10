package com.mow.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mow.entity.Donor;
import com.mow.repository.DonorRepository;
import com.mow.service.DonorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DonorRestController {

	@Autowired
	DonorService donorService;

	@Autowired
	DonorRepository donorRepository;

	@PostMapping("addDonor")
	@PreAuthorize("hasRole('DONOR')")
	Donor newDonor(@RequestBody Donor Donor) {
		return donorRepository.save(Donor);
	}

	// Show all Donors
	@GetMapping("showDonors")
	@PreAuthorize("hasRole('DONOR')")
	List<Donor> getAllDonors() {
		return donorRepository.findAll();
	}

	// Search by keywords
	@GetMapping("searchDonors")
	public List<Donor> searchDonor(@RequestParam String keyword) {
		return donorService.search(keyword);
	}

	// Search by price
	@GetMapping("searchByPrice")
	public List<Donor> searchByPrice(@RequestParam Double min, @RequestParam Double max) {
		List<Donor> priceResult = donorService.searchByPrice(min, max);
		return priceResult;
	}

	public DonorRestController(DonorService donorService) {
		this.donorService = donorService;
	}

	@GetMapping("/donors")
	public List<Donor> getAllDonor() {
		return donorService.getDonorsByRole(Long.valueOf(5));
	}

	@PostMapping
	public Donor createDonor(@RequestBody Donor donor) {
		return donorService.createDonor(donor);
	}

	@GetMapping("/{donorId}")
	public Donor getDonorById(@PathVariable Long donorId) {
		return donorService.getDonorById(donorId);
	}

	@DeleteMapping("/{donorId}")
	public void deleteDonor(@PathVariable Long donorId) {
		donorService.deleteDonor(donorId);
	}

	@GetMapping("/totalDonors")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'DONOR')")
	public ResponseEntity<Integer> getTotalDonors() {
		int totalDonors = donorService.getTotalDonors();
		return ResponseEntity.ok(totalDonors);
	}

}
