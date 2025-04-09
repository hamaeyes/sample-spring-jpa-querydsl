package com.arifng.jpaquerydsl.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.arifng.jpaquerydsl.common.converter.UpKindCdConverter;
import com.arifng.jpaquerydsl.common.enums.UpkindCd;
import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.ToString;

@Getter
@DynamicUpdate
@Entity
@Table(name = "hc_sickness")
public class HcSickness {

	/* 질병아이디 */
    @Id
    @Column(name = "sickness_id", nullable = false)
    private String id;

	/* 질병명 */
    @Column(name = "sickness_nm", nullable = false)
    private String sicknessNm;

	/* 축종코드 */
    @Convert(converter = UpKindCdConverter.class)
    @Column(nullable = false, columnDefinition = "tinyint")
    private UpkindCd upkindCd;

    /* 발생빈도(1~4) */
    @Column(name = "rate", nullable = false, columnDefinition = "tinyint")
    private Integer rate;

	/* 응급도 */
    @Column(name = "emergency_yn", nullable = false, columnDefinition = "char")
    private String emergencyYn;

	/* 질병 요약 */
    @Column(name = "sickness_summary")
    private String sicknessSummary;

	/* 질병상세 */
    @Column(name = "sickness_detail")
    private String sicknessDetail;

	/* 질병예방법 */
    @Column(name = "sickness_prevent")
    private String sicknessPrevent;

	/* 질병검사방법 */
    @Column(name = "sickness_check")
    private String sicknessCheck;

	/* 질병치료방법 */
    @Column(name = "sickness_treat")
    private String sicknessTreat;

	/* 질병처방방법 */
    @Column(name = "sickness_recipe")
    private String sicknessRecipe;

	/* 질병수술방법 */
    @Column(name = "sickness_surgery")
    private String sicknessSurgery;

	/* 질병처치방법 */
    @Column(name = "sickness_care")
    private String sicknessCare;

	/* 노출여부 */
    @Column(name = "use_yn", columnDefinition = "char")
    private String useYn;

	/* 수술비여부 */
    @Column(name = "surgery_fee_yn", columnDefinition = "char")
    private String surgeryFeeYn;

	/* 질병들 */
    @Column(name = "symptoms")
    private String symptoms;

	/* 뷰카운트 */
    @Column(name = "view_cnt")
    private Integer viewCnt;

    /* 질병 이미지 */
    @ToString.Exclude
    @OneToMany(mappedBy = "hcSickness", fetch = LAZY, orphanRemoval = true, cascade = ALL)
    private Set<HcSicknessImage> images;

    public String getSicknessId() {
        return this.id;
    }

    public void addImage(HcSicknessImage hcSicknessImage) {
        this.images = Optional.ofNullable(images).orElse(Sets.newHashSet());

        hcSicknessImage.setHcSickness(this);
        this.images.add(hcSicknessImage);
    }

    public void increaseViewCnt() {
        this.viewCnt += 1;
    }
}
