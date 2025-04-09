package com.bong.jpaquerydsl.common.enums;

import lombok.Getter;

@Getter
public enum ReportDetailType {
    SWCS("STANDARD_WEIGHT_COMPARISON_SENIOR", "성견묘 표준체중비교"),
    SWCJ("STANDARD_WEIGHT_COMPARISON_JUNIOR", "자견묘 표준체중비교"),
    AWC("AVERAGE_WEIGHT_COMPARISON", "평균체중비교"),
    WRC("WEIGHT_RANK_COMPARISON", "체중순번비교"),
    CC("CALORIE_CALCULATION", "칼로리 계산");

    private final String eng;
    private final String kor;
    ReportDetailType(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }
}
