package com.bong.jpaquerydsl.common.converter;

import java.io.IOException;
import java.util.Optional;

import javax.persistence.Converter;

import com.bong.jpaquerydsl.common.dto.BcsMeasurementData;
import com.bong.jpaquerydsl.common.util.FunctionExceptionWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@Converter
public class BcsMeasurementDataConverter extends JsonConverter<BcsMeasurementData> {
    
	@Override
    public BcsMeasurementData convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData, BcsMeasurementData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToDatabaseColumn(BcsMeasurementData attribute) {
        return Optional.ofNullable(attribute)
                .map(e -> FunctionExceptionWrapper.wrapper(OBJECT_MAPPER::writeValueAsString).apply(attribute))
                .orElse(null);
    }
}