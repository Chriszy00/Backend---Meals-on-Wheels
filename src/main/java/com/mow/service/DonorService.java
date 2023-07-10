package com.mow.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mow.entity.Donor;
import com.mow.repository.DonorRepository;

import java.util.List;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public List<Donor> getAllDonor() {
        return donorRepository.findAll();
    }

    public Donor createDonor(Donor donor) {
        return donorRepository.save(donor);
    }


    public Donor getDonorById(Long donorId) {
        return donorRepository.findById(donorId).orElse(null);
    }

    public Donor updateDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public void deleteDonor(Long donorId) {
        donorRepository.deleteById(donorId);
    }

    public List<Donor> getDonorsByRole(Long roleId) {
        return donorRepository.findByRoleId(roleId);
    }
    

    public int getTotalDonors() {
        Long roleId = 5L;
        return donorRepository.countByRoleId(roleId);
    }
    
    public List<Donor> searchByPrice(Double min, Double max) {
		return donorRepository.searchByPrice(min, max);
	}



    public List<Donor> search(String keyword) {
		return donorRepository.search(keyword);
	}
}
