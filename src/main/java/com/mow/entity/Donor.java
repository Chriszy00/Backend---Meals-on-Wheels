package com.mow.entity;

import javax.persistence.*;

@Entity
@Table(name = "donors")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String email;
	private String name;
	private Double donated;
	private Long roleId;
	private String dateDonated;
    public Donor() {
    }
    public Donor(String email, String name, Double donated, Long roleId, String dateDonated) {
        this.email = email;
        this.name = name;
        this.donated = donated;
        this.roleId = roleId;
        this.dateDonated = dateDonated;
    }

	@Override
	public String toString() {
		return "Donor [id=" + id + ", email=" + email + ", name=" + name + ", donated=" + donated + ", roleId=" + roleId
				+ ", dateDonated=" + dateDonated + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getDonated() {
		return donated;
	}
	public void setDonated(Double donated) {
		this.donated = donated;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getDateDonated() {
		return dateDonated;
	}
	public void setDateDonated(String dateDonated) {
		this.dateDonated = dateDonated;
	}
    
}
