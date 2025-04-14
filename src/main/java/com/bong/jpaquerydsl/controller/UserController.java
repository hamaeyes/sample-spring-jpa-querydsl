package com.bong.jpaquerydsl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bong.jpaquerydsl.model.User;
import com.bong.jpaquerydsl.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {
     
    private final UserService userService;

    @GetMapping(value = "/user")
    public User createUser() {
    	User user = User.builder()
				.mobileNum("010-6822-1111")
				.nicknm("하마2")
				.passwd("1111")
				.role("ADMIN_ROLE")
				.build();
    	
    	return userService.save(user); 
    }
    
    @PostMapping(value = "/users")
    public User createUsers(@RequestBody User user) {  
    	return userService.save(user); 
    }
    
    @GetMapping(value = "/users/{nicknm}")
    public User getUserByNicknm( @PathVariable String nicknm) {
    	return userService.findByNicknm(nicknm);
        
    }
    
    
}
