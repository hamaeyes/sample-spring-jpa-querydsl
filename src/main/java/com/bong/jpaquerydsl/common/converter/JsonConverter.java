package com.bong.jpaquerydsl.common.converter;

import java.util.Optional;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class JsonConverter<T> implements AttributeConverter<T, String> {
    protected final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    protected final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public String convertToDatabaseColumn(T attribute) {
        return Optional.ofNullable(attribute)
                .map(GSON::toJson)
                .orElse(null);
    }
}