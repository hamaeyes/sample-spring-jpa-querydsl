package com.arifng.jpaquerydsl.model;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "user_bcs_block")
public class UserBcsBlock {

	 /* 사용자 BCS 차단 ID */
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

	 /* 유저 시퀀스 */
    @Column(name = "usn", nullable = false)
    private Integer usn;

	 /* 등록일 */
    @Column(name = "create_dt", nullable = false)
    private LocalDateTime createDt;

}