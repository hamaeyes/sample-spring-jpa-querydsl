package com.bong.jpaquerydsl.bizz;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.bong.jpaquerydsl.model.User;
import com.bong.jpaquerydsl.service.UserService; 

/**
 * Mock으로 서비스에 대한 응답값을 정의하고
 * 나머지는 모두 흉내를 내는 방식이다. 
 * 
 * @author bongki-choi
 *
 */
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void 사용자_조회_테스트() throws Exception {
        // given
    	User mockUser = User.builder().mobileNum("01011112225").nicknm("하나e").build();
        given(userService.findByNicknm("하나e")).willReturn(mockUser);

        // when & then
        mockMvc.perform(get("/users/하나e"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nicknm").value("하나e"));
    }
}