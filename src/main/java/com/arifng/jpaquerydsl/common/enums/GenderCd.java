package com.arifng.jpaquerydsl.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum GenderCd {
    NONE(0, "모름"), MALE(1, "남"), FEMALE(2, "여");

    private final Integer code;
    private final String desc;
    GenderCd(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static GenderCd valueOf(Integer code) {
        return Arrays.stream(values())
                .filter(e -> Objects.equals(e.code, code))
                .findFirst()
                .orElse(null);
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return code.toString();
    }
}
