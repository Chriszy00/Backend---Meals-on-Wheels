package com.mow.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mow.entity.Preparation;
import com.mow.entity.User;
import com.mow.repository.PartnerRepository;
import com.mow.repository.PreparationRepository;



@Service
@Transactional
public class PartnerService {
	
	@Autowired
	PartnerRepository partnerRepository;
	
	@Autowired
	PreparationRepository preparationRepository;
	
    public List<User> getRiderUsers() {
    	Long roles = 4L;
        return partnerRepository.findByRoles_Id(roles);//findbyRoleId to findByRoles_Id
    }
    

    public int getTotalRiders() {
        Long roles = 4L;
        return partnerRepository.countByRoles(roles);//countByRoleId to countByRoles
    }
    
    
//    List<Preparation> getPreparationsByPartner(Long partnerId);
    
//    public List<Preparation> getAllPreparations() {
//        return partnerRepository.findAll();
//    }
//
//    public Preparation getPreparationById(int prepId) {
//        return partnerRepository.findById(Long prepId)
//                .orElseThrow(() -> new NotFoundException("Preparation not found with ID: " + prepId));
//    }
    
    
    public List<Preparation> getAllPreparations() {
        return preparationRepository.findAll();
    }

    public Preparation savePreparation(Preparation preparation) {
        return preparationRepository.save(preparation);
    }
    
    public List<Preparation> getAllDeliveries() {
        return preparationRepository.findAll();
    }
    
    public Preparation getPreparationById(Long preparationId) {
        return preparationRepository.findById(preparationId).orElse(null);
    }
    
    public void deletePreparation(Long preparationId) {
        preparationRepository.deleteById(preparationId);
    }
    
    public List<User> getAllRiders() {
        Long riderRoleId = 4L;
        return partnerRepository.findByRoles_Id(riderRoleId);
    }

}