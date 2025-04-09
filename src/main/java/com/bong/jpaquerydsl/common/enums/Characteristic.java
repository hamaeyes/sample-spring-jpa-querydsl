package com.bong.jpaquerydsl.common.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import lombok.Getter;

import static java.util.stream.Collectors.toList;

@Getter
public enum Characteristic {
    a(1), b(2), c(4), d(8), e(16), f(32), g(64), h(128);

    private final Integer bit;
    Characteristic(Integer bit) {
        this.bit = bit;
    }

    public static Set<Characteristic> parse(Integer num) {
        return Optional.ofNullable(num).filter(e -> !e.equals(0)).map(e -> {
                    List<Characteristic> reverseList = Arrays.stream(values())
                            .sorted(Comparator.comparingInt(Characteristic::getBit).reversed()).collect(toList());

                    return getCharacteristics(e, reverseList).stream()
                            .sorted(Comparator.comparingInt(Characteristic::getBit))
                            .collect(Collectors.toCollection(LinkedHashSet::new));
                })
                .orElse(new LinkedHashSet<>());
    }

    private static Set<Characteristic> getCharacteristics(final Integer num, final List<Characteristic> reverseList) {
        Integer input = num;
        Set<Characteristic> resultSet = Sets.newHashSet();
        for (Characteristic c : reverseList) {
            int remainder = input % c.getBit();
            if (remainder < input || remainder == 0) {
                // 나누기 성공
                resultSet.add(c);
                input = remainder;

                if(remainder == 0) break;
            }
        }
        return resultSet;
    }
}