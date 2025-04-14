package com.bong.jpaquerydsl.repository;

import static org.hibernate.annotations.QueryHints.COMMENT;

import java.util.Optional;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User>, UserRepositoryCustom {

	@QueryHints(value = {@QueryHint(name = COMMENT, value = "UserRepository.findByNicknm")})
	Optional<User> findByNicknm(String nickNm);
}
