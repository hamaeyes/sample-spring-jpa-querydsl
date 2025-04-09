package com.bong.jpaquerydsl.common.enums;

import java.util.Arrays;
import java.util.Objects;

public enum UpkindCd {
    ALL(0, "전체"), DOG(1, "강아지"), CAT(2, "고양이");

    private final Integer code;
    private final String desc;
    
    UpkindCd(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UpkindCd valueOf(Integer code) {
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
