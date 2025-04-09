package com.arifng.jpaquerydsl.common.converter;

import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Converter;

import org.springframework.util.StringUtils;

import com.google.common.collect.Sets;

@Converter
public class SetIntegerConverter extends SetConverter<Integer> {

    @Override
    public Set<Integer> convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData)
                .filter(data -> !data.isEmpty())
                .map(StringUtils::trimAllWhitespace)
                .map(data -> data.split(SEPARATOR))
                .map(this::convertArrayToSet)
                .orElse(Sets.newHashSet());
    }

    @Override
    public Set<Integer> convertArrayToSet(String... array) {
        return Arrays.stream(array)
                .filter(e -> e.chars().allMatch(Character::isDigit)) // isNumeric
                .mapToInt(Integer::parseInt).boxed()
                .collect(toSet());
    }


}