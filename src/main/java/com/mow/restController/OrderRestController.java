package com.mow.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mow.entity.OrderRequest;
import com.mow.repository.OrderRepository;
import com.mow.service.OrderService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderRestController {

  @Autowired
  OrderService orderService;
  
  @Autowired
  OrderRepository orderRepository;
  
  @PostMapping("/add_order")
  @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'PARTNER')")
  public String addOrder(@RequestBody OrderRequest order) {
      // Save the order to the database
      orderRepository.save(order);
         
      return "Order successfully added";
  }
  
  @GetMapping("/showOrder")
  @PreAuthorize("hasRole('ADMINISTRATOR')")
  public List<OrderRequest> showOrder() {
  	List<OrderRequest> menu = orderService.showOrder();
  	return menu;
  }

}