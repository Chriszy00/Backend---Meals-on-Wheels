package com.mow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mow.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	Boolean existsByEmail(String email);
	List<User> findByIdIn(List<Long> userIds);
	List<User> findByRoles_Id(Long roleId); //findbyRoleId to findByRoles_Id
	int countByRoles(Long roleId); //countByRoleId to countByRoles
	
}