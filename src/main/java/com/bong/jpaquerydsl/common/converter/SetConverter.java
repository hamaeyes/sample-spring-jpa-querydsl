package com.bong.jpaquerydsl.common.converter;

import static java.util.stream.Collectors.joining;

import java.util.Optional;
import java.util.Set;

import javax.persistence.AttributeConverter;


public abstract class SetConverter<T> implements AttributeConverter<Set<T>, String> {
    protected final String SEPARATOR = ",";

    public String convertToDatabaseColumn(Set<T> attribute) {
        return Optional.ofNullable(attribute)
                .map(data -> data.stream().map(Object::toString).collect(joining(SEPARATOR)))
                .orElse(null);
    }

    public abstract Set<T> convertToEntityAttribute(String dbData);
    public abstract Set<T> convertArrayToSet(String... array);
}
