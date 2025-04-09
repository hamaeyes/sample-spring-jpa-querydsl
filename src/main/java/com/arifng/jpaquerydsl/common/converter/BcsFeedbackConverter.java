package com.arifng.jpaquerydsl.common.converter;

import javax.persistence.Converter;

import com.arifng.jpaquerydsl.common.dto.BcsFeedback;

@Converter
public class BcsFeedbackConverter extends JsonConverter<BcsFeedback> {
    
	@Override
    public BcsFeedback convertToEntityAttribute(String dbData) {
        return GSON.fromJson(dbData, BcsFeedback.class);
    }
}
