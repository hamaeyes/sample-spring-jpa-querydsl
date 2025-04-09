package com.arifng.jpaquerydsl.common.enums;

import java.util.Arrays;
import java.util.Objects;

import lombok.Getter;

@Getter
public enum SpecialType {
    COMMON(0, "공통"), SMALL(101, "소형"), MEDIUM(102, "중형"), LARGE(103, "대형");

    private final Integer code;
    private final String desc;

    SpecialType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SpecialType valueOf(Integer code) {
        return Arrays.stream(SpecialType.values())
                .filter(e -> Objects.equals(e.code, code))
                .findFirst()
                .orElse(COMMON);
    }
}