package com.bong.jpaquerydsl.common.converter;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.bong.jpaquerydsl.common.enums.UpkindCd;

@Converter
public class UpKindCdConverter implements AttributeConverter<UpkindCd, Integer> {
    
	@Override
    public Integer convertToDatabaseColumn(UpkindCd attribute) {
        return Optional.ofNullable(attribute).map(UpkindCd::getCode).orElse(null);
    }

    @Override
    public UpkindCd convertToEntityAttribute(Integer dbData) {
        return Optional.ofNullable(dbData).map(UpkindCd::valueOf).orElse(null);
    }
}
