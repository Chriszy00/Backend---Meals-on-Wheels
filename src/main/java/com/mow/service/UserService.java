package com.mow.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mow.entity.User;
import com.mow.repository.UserRepository;


@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
    public List<User> getPartnerUsers() {
    	Long roles = 3L;
        return userRepository.findByRoles_Id(roles);//findbyRoleId to findByRoles_Id
    }
    public List<User> getAdminUsers() {
    	Long roles = 1L;
        return userRepository.findByRoles_Id(roles);//findbyRoleId to findByRoles_Id
    }
    public List<User> getMemberUsers() {
    	Long roles = 2L;
        return userRepository.findByRoles_Id(roles);//findbyRoleId to findByRoles_Id
    }

    public int getTotalPartners() {
        Long roles = 3L;
        return userRepository.countByRoles(roles);//countByRoleId to countByRoles
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    
//  added
  public User getUserById(Long userId) {
      return userRepository.findById(userId).orElse(null);
  }
}