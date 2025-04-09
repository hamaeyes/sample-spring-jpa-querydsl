package com.bong.jpaquerydsl.common.dto;

import java.math.BigDecimal;
import java.util.List;

import com.bong.jpaquerydsl.common.enums.SpecialType;
import com.bong.jpaquerydsl.common.enums.StandardType;
import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BcsAnalysisData {
    @Expose(serialize = false, deserialize = false)
    private SpecialType specialType;
    @Expose(serialize = false, deserialize = false)
    private StandardType standardType;

    private BigDecimal min;
    private BigDecimal max;
    
    private BigDecimal predictedValue;
    private BigDecimal predictedRate;
    private Integer predictedMonth;

    private List<BigDecimal> minList;
    private List<BigDecimal> maxList;

    private List<BigDecimal> predictedList;
}