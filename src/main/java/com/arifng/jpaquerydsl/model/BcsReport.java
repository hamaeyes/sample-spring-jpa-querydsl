package com.arifng.jpaquerydsl.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.annotation.CreatedDate;

import com.arifng.jpaquerydsl.common.converter.BcsFeedbackConverter;
import com.arifng.jpaquerydsl.common.converter.BcsMeasurementDataConverter;
import com.arifng.jpaquerydsl.common.converter.GenderCdConverter;
import com.arifng.jpaquerydsl.common.converter.SpecialTypeConverter;
import com.arifng.jpaquerydsl.common.converter.UpKindCdConverter;
import com.arifng.jpaquerydsl.common.dto.BcsFeedback;
import com.arifng.jpaquerydsl.common.dto.BcsMeasurementData;
import com.arifng.jpaquerydsl.common.enums.GenderCd;
import com.arifng.jpaquerydsl.common.enums.SpecialType;
import com.arifng.jpaquerydsl.common.enums.UpkindCd;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "bcs_report")
public class BcsReport {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bcs_report_id")
    private Integer id;

    @Column(name = "bcs_report_uuid")
    private String uuid;

    private Integer bcsInfoId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "usn")
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;
    private Integer petId;

    @Convert(converter = UpKindCdConverter.class)
    @Column(nullable = false, columnDefinition = "tinyint")
    private UpkindCd upkindCd;

    private String kindCd;

    @Convert(converter = GenderCdConverter.class)
    @Column(nullable = false, columnDefinition = "tinyint")
    private GenderCd genderCd;

    @Convert(converter = SpecialTypeConverter.class)
    @Column(nullable = false, columnDefinition = "smallint")
    private SpecialType specialType;

    private LocalDate birthDt;
    @Column(length = 20)
    private String petNm;
    @Column(length = 200)
    private String petImagePath;
    private String petAge;
    private BigDecimal weight;

    private BigDecimal sideRate;
    private BigDecimal topRate;

    /* 측정 데이터 */
    @Convert(converter = BcsMeasurementDataConverter.class)
    @Column(columnDefinition = "json")
    private  BcsMeasurementData measurementData;

    private Integer bcsScore;

    /* 리포트 공유 여부 */
    @Column(name = "share_yn", columnDefinition = "char")
    @Builder.Default
    private String shareYn = "Y";

    /* 리포트 삭제 여부 */
    @Column(name = "delete_yn", columnDefinition = "char")
    @Builder.Default
    private String deleteYn = "N";

    @Convert(converter = BcsFeedbackConverter.class)
    @Column(columnDefinition = "json")
    private BcsFeedback bcsFeedback;

    @Builder.Default
    @OneToMany(fetch = LAZY, mappedBy = "bcsReport", cascade = ALL, orphanRemoval = true)
    private Set<BcsReportDetail> details = Sets.newHashSet();

    @OneToOne(fetch = LAZY, mappedBy = "bcsReport", cascade = ALL, orphanRemoval = true)
    private BcsRemeasurement bcsRemeasurementInfo;

    @Builder.Default
    @OneToMany(fetch = LAZY, mappedBy = "originBcsReport", cascade = ALL, orphanRemoval = true)
    private Set<BcsRemeasurement> remeasurements = Sets.newHashSet();

    @CreatedDate
    private LocalDateTime createDt;

    public void addDetail(BcsReportDetail detail) {
        if (detail == null) return;

        this.details = Optional.ofNullable(this.details).filter(e -> !e.isEmpty()).orElse(Sets.newHashSet());
        detail.setBcsReport(this);
        this.details.add(detail);
    }

    public void setPetInfo(Integer petId, String petNm, String petImagePath) {
        this.petId = petId;
        this.petNm = petNm;
        this.petImagePath = petImagePath;
    }

    public void setBcsReportId(Integer bcsReportId) {
        this.id = bcsReportId;
    }

    public void setShareYn(String shareYn) {
        this.shareYn = shareYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    public void changeUser(User user) {
        this.user = user;
    }

    public void setOriginBcsReport(BcsReport origin) {
        this.bcsRemeasurementInfo = Optional.ofNullable(this.bcsRemeasurementInfo)
                .orElse(BcsRemeasurement.builder().bcsReport(this).build());

        this.bcsRemeasurementInfo.setOriginBcsReport(origin);
    }

    public Boolean isReMeasurement() {
        return Optional.ofNullable(this.bcsRemeasurementInfo)
                .filter(info -> info.getOriginBcsReport() != null)
                .filter(e -> this.user.getId() != null)
                .isPresent();
    }
}