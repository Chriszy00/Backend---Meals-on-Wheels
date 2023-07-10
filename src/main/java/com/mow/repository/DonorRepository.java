package com.mow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mow.entity.Donor;

import java.util.List;


public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByRoleId(Long roleId);
    int countByRoleId(Long roleId);
    
    @Query("SELECT donor FROM Donor donor WHERE " 
			+ "donor.name LIKE '%' || :keyword || '%'"
			+ " OR donor.email LIKE '%' || :keyword || '%'" 
			+ " OR donor.donated LIKE '%' || :keyword || '%'"
			+ " OR donor.roleId LIKE '%' || :keyword || '%'")
	public List<Donor> search(@Param("keyword") String keyword);

	@Query(value = "SELECT donor FROM Donor donor WHERE donor.donated BETWEEN :min AND :max")
	public List<Donor> searchByPrice(@Param("min") Double min, @Param("max") Double max);
}
