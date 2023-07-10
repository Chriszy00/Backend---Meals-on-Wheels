package com.mow.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private User partner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private OrderRequest order;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    @JsonIgnore
    private User rider;

    @Column(name = "status")
    private String status;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    // Constructors, getters, and setters

    // Default Constructor
    public Delivery() {
        this.deliveryDate = LocalDateTime.now();
    }

    // Constructor
    public Delivery(User partner, OrderRequest order, User rider) {
        this.partner = partner;
        this.order = order;
        this.rider = rider;
        this.status = "Assigned";
        this.deliveryDate = LocalDateTime.now();
    }

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public User getPartner() {
		return partner;
	}

	public void setPartner(User partner) {
		this.partner = partner;
	}

	public OrderRequest getOrder() {
		return order;
	}

	public void setOrder(OrderRequest order) {
		this.order = order;
	}

	public User getRider() {
		return rider;
	}

	public void setRider(User rider) {
		this.rider = rider;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

    // Getters and setters

    // ...
    
    
}