package com.bong.jpaquerydsl.bizz;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.bong.jpaquerydsl.model.User;
import com.bong.jpaquerydsl.repository.UserRepository;

/**
 * 통합 테스트 
 * 
 * 1. 서버를 띄운다.
 * 2. 서버에 전문을 요청한다. 
 * 3. 응답 결과를 확인한다. 
 * 4. 데이터를 롤백한다(새로운 트랜젝션을 생성했기에 롤백은 안된다!!). 
 * 
 * @author bongki-choi
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles("test") // H2 쓰는 test 환경을 따로 구성해도 좋아요
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 사용자_등록_조회_통합테스트() {
        // given
        String baseUrl = "http://localhost:" + port;
        User user = User.builder()
                .mobileNum("01012345677")
                .nicknm("카카오곰2")
                .passwd("1234")
                .role("USER")
                .build();

        // when
        ResponseEntity<User> response = restTemplate.postForEntity(baseUrl + "/users", user, User.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNicknm()).isEqualTo("카카오곰2");

        // 실제 DB에 저장되었는지도 확인 가능
        Optional<User> savedUser = userRepository.findByNicknm("카카오곰2");
        assertThat(savedUser).isPresent();
    }
}
