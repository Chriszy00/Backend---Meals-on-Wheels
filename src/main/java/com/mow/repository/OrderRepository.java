package com.mow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mow.entity.OrderRequest;

@Repository
public interface OrderRepository extends JpaRepository<OrderRequest, Long>{

}