package com.mow.restController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mow.entity.OrderRequest;
import com.mow.entity.Preparation;
import com.mow.entity.RoleName;
import com.mow.entity.User;
import com.mow.service.OrderService;
import com.mow.service.PartnerService;
import com.mow.service.UserService;



@RestController
@RequestMapping("/preparation")
public class AdminController {
	
	@Autowired
	PartnerService partnerService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;

	
	@PostMapping("/assignPartner/{orderId}")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> assignPartner(
			@PathVariable Long orderId, 
			@RequestBody Map<String, Long> requestBody) {
	Long partnerId = requestBody.get("partnerId");
	Long memberId = requestBody.get("memberId");

	OrderRequest order = orderService.getOrderById(orderId);
	User partner = userService.getUserById(partnerId);
	User member = userService.getUserById(memberId);

	if (order == null) {
		return ResponseEntity.badRequest().body("Invalid order ID");
	}

	if (partner == null || !partner.getRoles().stream().anyMatch(role -> role.getName() == RoleName.ROLE_PARTNER)) {
		return ResponseEntity.badRequest().body("Invalid partner ID");
	}
	
	if (member == null || !member.getRoles().stream().anyMatch(role -> role.getName() == RoleName.ROLE_MEMBER)) {
		return ResponseEntity.badRequest().body("Invalid member ID");
	}

	Preparation preparation = new Preparation();
	preparation.setMember(member);
	preparation.setOrder(order);
	preparation.setPartner(partner);
	preparation.setPrepStatus("Preparing");

	// Save the delivery entity
	partnerService.savePreparation(preparation);

	return ResponseEntity.ok("Partner assigned successfully");
	}	
}

