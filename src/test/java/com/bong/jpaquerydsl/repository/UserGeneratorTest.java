package com.bong.jpaquerydsl.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bong.jpaquerydsl.model.User;
import com.bong.jpaquerydsl.service.UserServiceImpl;

 @ExtendWith(MockitoExtension.class)
 class UserGeneratorTest {

	@Mock // 가짜 객체 생성, DB가 없어도 동작 
	UserRepository userRepository;
	
	@InjectMocks // 목을 주입(생성자,세터,필드 모드 가능)
	UserServiceImpl userService;

	@BeforeEach // 테스트 수행전 실행할 작업
	void setUp() {
	}

	@DisplayName("서비스로 모킹한다.")
	@Test
	void case1() {
		// given
		User user = User.builder()
				.mobileNum("010-6822-1111")
				.nicknm("하마2")
				.passwd("1111")
				.role("ADMIN_ROLE")
				.build();
		
		when(userRepository.save(any(User.class))).thenReturn(user); // repository 목 동작 정의
        when(userRepository.findByNicknm("하마2")).thenReturn(Optional.of(user));
		
		// when 
        User result = userService.save(user);

		// then
        assertNotNull(result);
        assertEquals("하마", result.getNicknm());
		
		
		System.err.println("JUnit 5 테스트 실행됨");
		
	}
	
	@DisplayName("레포지토리로만 모킹한다.")
	@Test
	void case2() {
	    User user = User.builder()
	            .mobileNum("010-6822-1111")
	            .nicknm("하마")
	            .passwd("1111")
	            .role("ADMIN_ROLE")
	            .build();

	    when(userRepository.save(any(User.class))).thenReturn(user);

	    userRepository.save(user); // ← 호출하지 않음

	    verify(userRepository, times(1)).save(user); // 호출 안 했는데 검증해서 실패!
	}
	

}
