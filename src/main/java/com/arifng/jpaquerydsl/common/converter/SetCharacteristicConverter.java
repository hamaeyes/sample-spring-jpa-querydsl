package com.arifng.jpaquerydsl.common.converter;

import static java.util.stream.Collectors.toCollection;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Converter;

import org.springframework.util.StringUtils;

import com.arifng.jpaquerydsl.Characteristic;

import static java.util.stream.Collectors.joining;

@Converter
public class SetCharacteristicConverter extends SetConverter<Characteristic> {

    @Override
    public Set<Characteristic> convertToEntityAttribute(String dbData) {
    	System.err.println("convertToEntityAttribute ...");
        return Optional.ofNullable(dbData)
                .filter(data -> !data.isEmpty())
                .map(StringUtils::trimAllWhitespace)
                .map(data -> data.split(SEPARATOR))
                .map(this::convertArrayToSet)
                .orElseGet(LinkedHashSet::new);
    }

    
    public String convertToDatabaseColumn(Set<Characteristic> attribute) {
        return Optional.ofNullable(attribute)
                .map(data -> data.stream().map(Enum::name).collect(joining(SEPARATOR)))
                .orElse(null);
    }

    @Override
    public Set<Characteristic> convertArrayToSet(String... array) {
    	System.err.println("convertArrayToSet ... ");
        return Arrays.stream(array).map(Characteristic::valueOf).collect(toCollection(LinkedHashSet::new));
    }
}