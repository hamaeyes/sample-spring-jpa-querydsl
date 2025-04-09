package com.arifng.jpaquerydsl.common.converter;

import java.io.IOException;
import java.util.Optional;

import javax.persistence.Converter;

import com.arifng.jpaquerydsl.common.dto.BcsMeasurementData;
import com.arifng.jpaquerydsl.common.util.FunctionExceptionWrapper;
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