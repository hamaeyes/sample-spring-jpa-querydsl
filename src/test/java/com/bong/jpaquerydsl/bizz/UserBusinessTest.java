package com.bong.jpaquerydsl.bizz;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.bong.jpaquerydsl.model.User;
import com.bong.jpaquerydsl.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 서비스를 띄우고 DB에 값을 저장한다. 
 * @Transactional 어노테이션이 있기 때문에 마지막에 롤백된다. 
 * 
 * @author bongki-choi
 *
 */
@DisplayName("유저 비즈니스 로직 테스트")
@Slf4j
@ActiveProfiles("local")
@SpringBootTest
@Transactional // @Commit or @Rollback(false) 은 실제 DB에 반영된다.
public class UserBusinessTest {

	@Autowired
	UserService userService;
	
	
	@Test
	void userRegist() {
		
		User user = User.builder().mobileNum("01011112225").nicknm("하나e").build();
		userService.save(user);
		
		User savedUser = userService.findByNicknm("하나e");
		System.err.println("Saved User Nicknm :: " + savedUser.getNicknm());
		log.debug("Saved User Nicknm :: " + savedUser.getNicknm());
		
		assertThat(savedUser).isNotNull();
	}
}
