package com.mow.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mow.entity.Preparation;



public interface PreparationRepository extends JpaRepository<Preparation, Long> {
	List<Preparation> findByPartnerId(Long partnerId);
	
}