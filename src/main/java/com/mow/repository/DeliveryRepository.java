package com.mow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mow.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    // Add custom query methods if needed
	
	List<Delivery> findByRiderId(Long riderId);
	
	@Query("SELECT d FROM Delivery d WHERE d.rider IS NOT NULL")
	List<Delivery> findAllAssignedDeliveries();
	
	
	List<Delivery> findByStatus(String status);
	
    @Query("SELECT d FROM Delivery d JOIN FETCH d.order")
    List<Delivery> findAllDeliveriesWithOrderDetails();

}