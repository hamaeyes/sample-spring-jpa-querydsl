package com.arifng.jpaquerydsl.common.converter;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.arifng.jpaquerydsl.common.enums.GenderCd;

@Converter
public class GenderCdConverter implements AttributeConverter<GenderCd, Integer> {
   
	@Override
    public Integer convertToDatabaseColumn(GenderCd attribute) {
        return Optional.ofNullable(attribute).map(GenderCd::getCode).orElse(null);
    }

    @Override
    public GenderCd convertToEntityAttribute(Integer dbData) {
        return Optional.ofNullable(dbData).map(GenderCd::valueOf).orElse(null);
    }
}
