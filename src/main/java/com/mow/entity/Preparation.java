package com.mow.entity;

import javax.persistence.*;

@Entity
@Table(name = "Preparation")
public class Preparation {
	
    @Id
    @Column(name = "prep_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prepId;

    @ManyToOne
    @JoinColumn(name = "id")
    private User partner;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderRequest order;

//    @OneToOne
//    @JoinColumn(name = "member_id")
//    private OrderRequest member;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private User member;

    @Column(name = "prep_status")
    private String prepStatus;

    
    
    
	public Preparation() {

	}




	public Preparation(Long prepId, User partner, OrderRequest order, User member, String prepStatus) {
		super();
		this.prepId = prepId;
		this.partner = partner;
		this.order = order;
		this.member = member;
		this.prepStatus = prepStatus;
	}




	@Override
	public String toString() {
		return "Preparation [prepId=" + prepId + ", partner=" + partner + ", order=" + order + ", member=" + member
				+ ", prepStatus=" + prepStatus + "]";
	}




	public Long getPrepId() {
		return prepId;
	}




	public void setPrepId(Long prepId) {
		this.prepId = prepId;
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




	public User getMember() {
		return member;
	}




	public void setMember(User member) {
		this.member = member;
	}




	public String getPrepStatus() {
		return prepStatus;
	}




	public void setPrepStatus(String prepStatus) {
		this.prepStatus = prepStatus;
	}

	

    
    
}
