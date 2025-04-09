package com.arifng.jpaquerydsl.common.converter;

import java.util.Map;

import javax.persistence.Converter;

import com.arifng.jpaquerydsl.common.dto.BcsAnalysisData;
import com.google.gson.reflect.TypeToken;

@Converter
public class BcsAnalysisDataConverter extends JsonConverter<Map<String, BcsAnalysisData>> {
    @Override
    public Map<String, BcsAnalysisData> convertToEntityAttribute(String dbData) {
        return GSON.fromJson(dbData, new TypeToken<Map<String, BcsAnalysisData>>(){}.getType());
    }
}
