package com.mow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mow.entity.Delivery;
import com.mow.entity.Meal;
import com.mow.repository.DeliveryRepository;

@Service
public class DeliveryService {
    
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
    
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
    
    public Delivery getDeliveryById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).orElse(null);
    }
    
    public void deleteDelivery(Long deliveryId) {
        deliveryRepository.deleteById(deliveryId);
    }
    
    // Add more methods as needed
    public List<Delivery> getAssignedDeliveriesByRiderId(Long riderId) {
        return deliveryRepository.findByRiderId(riderId);
    }
    
    public Delivery getDeliveries(Long deliveryId) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
        if (optionalDelivery.isPresent()) {
            return optionalDelivery.get();
        }
        throw new NoSuchElementException("Delivery not found");
    }
    
//    public List<Delivery> getAssignedDeliveries() {
//        return deliveryRepository.findAllAssignedDeliveries();
//    }
    
    public List<Delivery> getAssignedDeliveries() {
        return deliveryRepository.findByStatus("Assigned");
    }

    public List<Delivery> getAllDeliveriesWithOrderDetails() {
        return deliveryRepository.findAllDeliveriesWithOrderDetails();
    }

    
//    public List<Meal> getAssignedDeliveryMeals(Long riderId) {
//        List<Delivery> assignedDeliveries = deliveryRepository.findByRiderId(riderId);
//        List<Meal> meals = new ArrayList<>();
//        for (Delivery delivery : assignedDeliveries) {
//            meals.add(delivery.getOrder().getMeal());
//        }
//        return meals;
//    }
    
}