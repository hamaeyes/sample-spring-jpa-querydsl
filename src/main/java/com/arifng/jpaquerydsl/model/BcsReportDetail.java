package com.arifng.jpaquerydsl.model;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.arifng.jpaquerydsl.common.converter.BcsAnalysisDataConverter;
import com.arifng.jpaquerydsl.common.converter.SpecialTypeConverter;
import com.arifng.jpaquerydsl.common.dto.BcsAnalysisData;
import com.arifng.jpaquerydsl.common.enums.ReportDetailType;
import com.arifng.jpaquerydsl.common.enums.SpecialType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "bcs_report_detail")
public class BcsReportDetail {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bcs_report_detail_id")
    private Integer id;

    private ReportDetailType reportDetailType;

    @ManyToOne
    @JoinColumn(name = "bcs_report_id")
    private BcsReport bcsReport;

    @Convert(converter = SpecialTypeConverter.class)
    @Column(nullable = false, columnDefinition = "smallint")
    private SpecialType specialType;

    private BigDecimal weight;
    @Column(columnDefinition = "smallint")
    private Integer month;

    @Convert(converter = BcsAnalysisDataConverter.class)
    @Column(columnDefinition = "json")
    private Map<String, BcsAnalysisData> data;
    
    @CreatedDate
    private LocalDateTime createDt;

    public void setBcsReport(BcsReport bcsReport) {
        this.bcsReport = bcsReport;
    }
}