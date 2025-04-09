package com.arifng.jpaquerydsl.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static javax.persistence.FetchType.LAZY;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "bcs_remeasurement")
public class BcsRemeasurement {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bcs_remeasurement_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "bcs_report_id")
    private BcsReport bcsReport;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "origin_bcs_report_id")
    private BcsReport originBcsReport;

    private LocalDateTime notifiedDt;
    
    public void setOriginBcsReport(BcsReport origin) {
        this.originBcsReport = origin;
    }

    public void setNotifiedDt(LocalDateTime now) {
        this.notifiedDt = now;
    }
}