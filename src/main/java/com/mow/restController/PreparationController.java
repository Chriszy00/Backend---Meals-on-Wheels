package com.mow.restController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mow.entity.Delivery;
import com.mow.entity.OrderRequest;
import com.mow.entity.Preparation;
import com.mow.entity.RoleName;
import com.mow.entity.User;
import com.mow.service.DeliveryService;
import com.mow.service.OrderService;
import com.mow.service.PartnerService;
import com.mow.service.UserService;

@RestController
@RequestMapping("/preparation")
public class PreparationController {

	@Autowired
	PartnerService preparationService;

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	DeliveryService deliveryService;

	@GetMapping("/partnerOrders")
	@PreAuthorize("hasRole('PARTNER')")
	public ResponseEntity<List<Preparation>> getAllOrders() {
		List<Preparation> preparations = preparationService.getAllPreparations();
		return ResponseEntity.ok(preparations);
	}

//    @GetMapping("/availableRiders")
//    public List<User> getAllRiders() {
//        return preparationService.getAllRiders();
//    }

	@GetMapping("/availableRiders")
	@PreAuthorize("hasRole('PARTNER')")
	public ResponseEntity<List<User>> getAllRiders() {
		List<User> riders = preparationService.getAllRiders();
		return ResponseEntity.ok(riders);
	}

	@GetMapping(value = "/showRiders")
	@PreAuthorize("hasRole('PARTNER')")
	public List<User> getMenu() {
		List<User> menu = preparationService.getRiderUsers();
		return menu;
	}

	@PostMapping("/assignRider/{orderId}")
	@PreAuthorize("hasRole('PARTNER')")
	public ResponseEntity<String> assignRider(@PathVariable Long orderId, @RequestBody Map<String, Long> requestBody) {
		Long riderId = requestBody.get("riderId");
//	    Long partnerId = requestBody.get("partnerId");

		OrderRequest order = orderService.getOrderById(orderId);
		User rider = userService.getUserById(riderId);

		if (order == null) {
			return ResponseEntity.badRequest().body("Invalid order ID");
		}

		if (rider == null || !rider.getRoles().stream().anyMatch(role -> role.getName() == RoleName.ROLE_RIDER)) {
			return ResponseEntity.badRequest().body("Invalid rider ID");
		}

//	    User partner = userService.getUserById(partnerId);

		Delivery delivery = new Delivery();
		delivery.setOrder(order);
		delivery.setRider(rider);
		delivery.setStatus("Assigned");
		delivery.setDeliveryDate(LocalDateTime.now());

		// Save the delivery entity
		deliveryService.saveDelivery(delivery);

		return ResponseEntity.ok("Rider assigned successfully");
	}

	@GetMapping("/{deliveryId}")
	@PreAuthorize("hasRole('RIDER')")
	public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long deliveryId) {
		Delivery delivery = deliveryService.getDeliveryById(deliveryId);
		return ResponseEntity.ok(delivery);
	}

	@GetMapping("/assigned")
	@PreAuthorize("hasRole('RIDER')")
	public ResponseEntity<List<Delivery>> getAssignedDeliveries() {
		List<Delivery> deliveries = deliveryService.getAssignedDeliveries();
		return ResponseEntity.ok(deliveries);
	}

	@GetMapping("/deliveries")
	@PreAuthorize("hasRole('RIDER')")
	public ResponseEntity<List<Delivery>> getAllDeliveriesWithOrderDetails() {
		List<Delivery> deliveries = deliveryService.getAllDeliveriesWithOrderDetails();
		return ResponseEntity.ok(deliveries);
	}

}