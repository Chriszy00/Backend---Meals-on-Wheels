package com.mow.restController;

import java.security.Principal;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mow.Exception.UserNotFoundException;
import com.mow.entity.Role;
import com.mow.entity.User;
import com.mow.repository.RoleRepository;
import com.mow.repository.UserRepository;
import com.mow.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/{userId}/address")
    public void saveUserAddress(@PathVariable("userId") Long userId, @RequestBody String address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        user.setAddress(address);
        userRepository.save(user);
    }
    
    @GetMapping("/showPartners")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'PARTNER')")
    public ResponseEntity<List<User>> getPartnerUsers() {
        List<User> partnerUsers = userService.getPartnerUsers();
        return ResponseEntity.ok(partnerUsers);
    }
	
    @GetMapping("/showAdmins")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<User>> getAdminUsers() {
        List<User> adminUsers = userService.getAdminUsers();
        return ResponseEntity.ok(adminUsers);
    }
	
    @GetMapping("/showMembers")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<List<User>> getMemberUsers() {
        List<User> memberUsers = userService.getMemberUsers();
        return ResponseEntity.ok(memberUsers);
    }

    @GetMapping("/find/{email}")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
    @PostMapping("/find")
    @PreAuthorize("hasRole('MEMBER')")
    public ResponseEntity<Optional<User>> getFindByEmail(@RequestBody String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/countPartners")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'PARTNER')")
    public ResponseEntity<Integer> getTotalPartners() {
        int totalPartners = userService.getTotalPartners();
        return ResponseEntity.ok(totalPartners);
    }
	
    @GetMapping("/userDetails/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    
//    @GetMapping
//    public ResponseEntity<?> getAllRoles() {
//        List<Role> roles = roleRepository.findAll();
//        return ResponseEntity.ok(roles);
//    }
}
