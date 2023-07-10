package com.mow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mow.entity.Role;
import com.mow.entity.RoleName;


public interface RoleRepository extends JpaRepository<Role, Long> {

     Role findByName(String name);
	 Optional<Role> findByName(RoleName roleName);
}

