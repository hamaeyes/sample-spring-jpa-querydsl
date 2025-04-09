package com.bong.jpaquerydsl.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.bong.jpaquerydsl.common.converter.SetCharacteristicConverter;
import com.bong.jpaquerydsl.common.enums.Characteristic;

import lombok.Getter; 

@Getter
@DynamicUpdate
@Entity
@Table(name = "abandon_pet")
public class AbandonPet {

    /* 유기번호 */
    @Id
    @Column(name = "desertion_num", nullable = false)
    private String id;

    /* 상태명 */
    @Column(name = "state_nm")
    private String stateNm;

    /* 상태코드(보호중1,종료(자연사)2,종료(안락사)3,종료(입양)4,종료(반환)5,종료(방사)6,종료(기증)7,기타0) */
    @Column(name = "state_cd", columnDefinition = "tinyint")
    private Integer stateCd;

    /* 접수일 */
    @Column(name = "happen_dt", nullable = false)
    private LocalDate happenDt;

    /* 발견장소 */
    @Column(name = "happen_place")
    private String happenPlace;

    /* 축종 */
    @Column(name = "upkind_cd", nullable = false, columnDefinition = "tinyint")
    private Integer upkindCd;

    /* 품종 */
    @Column(name = "kind_cd")
    private String kindCd;

    /* 색상 */
    @Column(name = "color_nm")
    private String colorNm;

    /* 출생년도 */
    @Column(name = "birth_year")
    private Integer birthYear;

    /* 체중 */
    @Column(name = "weight")
    private BigDecimal weight;

    /* 공고번호 */
    @Column(name = "notice_num", nullable = false)
    private String noticeNum;

    /* 공고시작일 */
    @Column(name = "notice_start_dt", nullable = false)
    private LocalDate noticeStartDt;

    /* 공고종료일 */
    @Column(name = "notice_end_dt", nullable = false)
    private LocalDate noticeEndDt;

    /* 시도코드 */
    @Column(name = "sido_cd")
    private String sidoCd;

    /* 시군구코드 */
    @Column(name = "sigungu_cd")
    private String sigunguCd;

    /* 이미지URL */
    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    /* 썸네일이미지URL */
    @Column(name = "thumb_img_url")
    private String thumbImgUrl;

    /* 성별코드(0모름,1수,2암) */
    @Column(name = "gender_cd", nullable = false, columnDefinition = "tinyint")
    private Integer genderCd;

    /* 중성화구분(0모름,1예,2아니오) */
    @Column(name = "neuter_cd", nullable = false, columnDefinition = "tinyint")
    private Integer neuterCd;

    /* 특징 */
    @Column(name = "special_mark")
    private String specialMark;

    /* 보호소명 */
    @Column(name = "care_nm")
    private String careNm;

    /* 보호소연락처 */
    @Column(name = "care_phone_num")
    private String carePhoneNum;

    /* 보호소주소 */
    @Column(name = "care_addr")
    private String careAddr;

    /* 관할기관 */
    @Column(name = "care_office_nm")
    private String careOfficeNm;

    /* 담당자명 */
    @Column(name = "charge_nm")
    private String chargeNm;

    /* 담당자연락처 */
    @Column(name = "office_phone_num")
    private String officePhoneNum;

    /* 특이사항(api값 */
    @Column(name = "notice_comment")
    private String noticeComment;

    /* 등록일 */
    @Column(name = "create_dt", nullable = false)
    private LocalDateTime createDt;

    /* 수정일 */
    @Column(name = "update_dt", nullable = false)
    private LocalDateTime updateDt;

    /* 사용여부 */
    @Column(name = "use_yn", columnDefinition = "char")
    private String useYn;

    /* 뷰카운트 */
    @Column(name = "view_cnt")
    private Integer viewCnt;

    /* 색상코드(a흰색,b검은색 */
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "color_cd", nullable = false, columnDefinition = "set")
    private Set<Characteristic> colorCds;

    public String getDesertionNum() {
        return this.id;
    }

    public AbandonPet increaseViewCnt() {
        this.viewCnt += 1;
        return this;
    }
}