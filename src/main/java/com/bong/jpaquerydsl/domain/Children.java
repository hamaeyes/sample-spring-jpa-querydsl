package com.bong.jpaquerydsl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "children")
public class Children {

	@Id
	@GeneratedValue
	@Column(name = "CHILDREN_ID")
	private Long id;

	@Column(name = "MEMBER_ID", nullable = false)
	private Long memberId;

	@Column(name = "CHILDREN_NAME")
	private String childName;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "GENDER")
	private String gender;
}
