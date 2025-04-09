package com.arifng.jpaquerydsl.common.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BcsMeasurementData {

	//이미지 경로
    private String sideOriginImgPath;
    private String sideCalibratedImgPath;
    private String sideFinalImgPath;
    private String topOriginImgPath;
    private String topCalibratedImgPath;
    private String topFinalImgPath;

    //점과 점 사이 거리
    private BigDecimal sideLeftDistance;
    private BigDecimal sideRightDistance;
    private BigDecimal topLeftDistance;
    private BigDecimal topRightDistance;

}
