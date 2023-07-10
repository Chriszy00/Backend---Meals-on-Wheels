package com.mow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Meal {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long meal_id;
	 
	 @Column
	 private String meal_name;
	 
	 @Column
	 private String meal_desc;
	  
	 @Column
	 private String meal_nutri;
	 
	 @Column
	 private String category;
	 
	 @Column 
	 private String type;
	 
	 @Column
	 private String meal_img_path;
	 
	 @Column
	 private String meal_photo;

	public Meal() {
		super();
	}

	public Meal(Long meal_id, String meal_name, String meal_desc, String meal_nutri, String category, String type,
			String meal_img_path, String meal_photo) {
		super();
		this.meal_id = meal_id;
		this.meal_name = meal_name;
		this.meal_desc = meal_desc;
		this.meal_nutri = meal_nutri;
		this.category = category;
		this.type = type;
		this.meal_img_path = meal_img_path;
		this.meal_photo = meal_photo;
	}

	@Override
	public String toString() {
		return "Meal [meal_id=" + meal_id + ", meal_name=" + meal_name + ", meal_desc=" + meal_desc + ", meal_nutri="
				+ meal_nutri + ", category=" + category + ", type=" + type + ", meal_img_path=" + meal_img_path
				+ ", meal_photo=" + meal_photo + "]";
	}

	public Long getMeal_id() {
		return meal_id;
	}

	public void setMeal_id(Long meal_id) {
		this.meal_id = meal_id;
	}

	public String getMeal_name() {
		return meal_name;
	}

	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}

	public String getMeal_desc() {
		return meal_desc;
	}

	public void setMeal_desc(String meal_desc) {
		this.meal_desc = meal_desc;
	}

	public String getMeal_nutri() {
		return meal_nutri;
	}

	public void setMeal_nutri(String meal_nutri) {
		this.meal_nutri = meal_nutri;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMeal_img_path() {
		return meal_img_path;
	}

	public void setMeal_img_path(String meal_img_path) {
		this.meal_img_path = meal_img_path;
	}

	public String getMeal_photo() {
		return meal_photo;
	}

	public void setMeal_photo(String meal_photo) {
		this.meal_photo = meal_photo;
	}
}