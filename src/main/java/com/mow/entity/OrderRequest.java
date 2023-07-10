package com.mow.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class OrderRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;
	
	@Column
	private Long member_id;
	
	@Column
	private String member_name;
	
	@Column
	private String location;
	
	@Column
	private String meal_name;
	
	@Column
	private String order_status;
	
	@CreatedDate
	private String order_date;

	@PrePersist
	private void onOrder() {
	    DateFormat dateTime = new SimpleDateFormat("dd MMMMM yyyy HH:mm:ss");
	    order_date = dateTime.format(new Date());
	}

	public OrderRequest() {
		super();
	}

	public OrderRequest(Long order_id, Long member_id, String member_name, String location, String meal_name,
			String order_status, String order_date) {
		super();
		this.order_id = order_id;
		this.member_id = member_id;
		this.member_name = member_name;
		this.location = location;
		this.meal_name = meal_name;
		this.order_status = order_status;
		this.order_date = order_date;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getMember_id() {
		return member_id;
	}

	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMeal_name() {
		return meal_name;
	}

	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	
}