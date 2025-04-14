package com.bong.jpaquerydsl.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.bong.jpaquerydsl.config.QueryDslConfig;
import com.bong.jpaquerydsl.model.QUser;
import com.bong.jpaquerydsl.model.User;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * JPA Repository + QueryDSL 테스트.
 * 
 * @author bongki-choi
 *
 */
@DataJpaTest
@Import(QueryDslConfig.class) // 없으면 JPAQueryFactory 의존성 오류가 나온다. 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트 데이터베이스를 기본으로 찾는데 없으면 오류 발생킨다. 
public class UserRepositoryQueryDslTest {

	@Autowired
    private UserRepository userRepository;

    @Test
    void queryDslTest() {
        User user = User.builder().nicknm("홍길동").mobileNum("1111").role("ADMIN").build();
        userRepository.save(user);

        BooleanExpression predicate = QUser.user.nicknm.eq("홍길동");
        
        Optional<User> result = userRepository.findOne(predicate);

        assertThat(result).isPresent();
    }
}
