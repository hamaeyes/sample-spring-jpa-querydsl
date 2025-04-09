package com.bong.jpaquerydsl.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BcsFeedback {
    private Integer bcsReportId;
    private Boolean answer1;
    private Boolean answer2;
    private Boolean answer3;
    private String content;
    private String createDt;
}