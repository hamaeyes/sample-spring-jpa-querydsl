package com.bong.jpaquerydsl.common.converter;

import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.bong.jpaquerydsl.common.enums.SpecialType;

@Converter
public class SpecialTypeConverter implements AttributeConverter<SpecialType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SpecialType attribute) {
        return Optional.ofNullable(attribute).map(SpecialType::getCode).orElse(null);
    }

    @Override
    public SpecialType convertToEntityAttribute(Integer dbData) {
        return Optional.ofNullable(dbData).map(SpecialType::valueOf).orElse(null);
    }
}
