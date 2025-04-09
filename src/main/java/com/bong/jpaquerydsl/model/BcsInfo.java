package com.bong.jpaquerydsl.model;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.bong.jpaquerydsl.common.converter.SpecialTypeConverter;
import com.bong.jpaquerydsl.common.converter.UpKindCdConverter;
import com.bong.jpaquerydsl.common.enums.SpecialType;
import com.bong.jpaquerydsl.common.enums.UpkindCd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Getter
@Entity
@Table(name = "bcs_info")
public class BcsInfo {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bcs_info_id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "tinyint")
    private Integer bcsScore;

    @Convert(converter = UpKindCdConverter.class)
    @Column(nullable = false, columnDefinition = "tinyint")
    private UpkindCd upkindCd;

    @Convert(converter = SpecialTypeConverter.class)
    @Column(columnDefinition = "smallint")
    private SpecialType specialType;

    /* 측면 */
    private BigDecimal sideMin;
    private BigDecimal sideMax;

    /* 부감 */
    private BigDecimal topMin;
    private BigDecimal topMax;

    @CreatedDate
    private LocalDateTime createDt;
}