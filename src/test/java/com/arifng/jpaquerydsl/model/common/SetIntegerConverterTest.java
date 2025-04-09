package com.arifng.jpaquerydsl.model.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bong.jpaquerydsl.common.converter.SetIntegerConverter;
import com.google.common.collect.Sets;

@DisplayName("MySQL Set Type을 Set<Integer>으로 변환 테스트")
class SetIntegerConverterTest {

	SetIntegerConverter converter;

	@BeforeEach
	void setUp() {
		converter = new SetIntegerConverter();
	}

	@Test
	void convertToEntityAttribute_Success() {

		String dbData = "1,2,3,4,5";

		Set<Integer> integerSet = converter.convertToEntityAttribute(dbData);

		assertThat(integerSet).isNotEmpty();
		assertThat(integerSet).isEqualTo(Sets.newHashSet(1, 2, 3, 4, 5));
	}

	@Test
	void convertToEntityAttribute_Failure() {

		String dbData = "1,2,3,4,A";

		Set<Integer> integerSet = converter.convertToEntityAttribute(dbData);

		assertThat(integerSet).isNotEmpty();
		assertThat(integerSet).isEqualTo(Sets.newHashSet(1, 2, 3, 4));
	}

	@Test
	void convertToDatabaseColumn_Success() {

		Set<Integer> intSet = Sets.newHashSet(4, 5, 6, 7);
		String converted = converter.convertToDatabaseColumn(intSet);

		assertThat(converted).isNotEmpty();
		assertThat(converted).isEqualTo("4,5,6,7");
	}
}