package com.arifng.jpaquerydsl.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name = "curation_item")
public class CurationItem {

	/* 아이템 아이디 */
    @Id  @GeneratedValue(strategy = IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Integer id;

	/* corishop 아이템 아이디 */
    @Column(name = "origin_item_id")
    private Integer originItemId;

	/* 연령대(Puppy,Adult,Senior,Whole) */
    @Column(name = "age_group", columnDefinition = "char")
    private String ageGroup;

	/* 축종코드(개:1, 고양이:2) */
    @Column(name = "upkind_cd", nullable = false, columnDefinition = "tinyint")
    private Integer upkindCd;

	/* 아이템 타입 (1:사료, 2:간식) */
    @Column(name = "item_type", nullable = false, columnDefinition = "tinyint")
    private Integer itemType;

	/* 건조 타입(1건식,2습식) */
    @Column(name = "dry_ty", columnDefinition = "tinyint")
    private Integer dryTy;

	/* 스페셜타입(0모름,100기타,101소형견,102중형견,103대형견,200기타,201단모종,202장모종) */
    @Column(name = "special_type", columnDefinition = "smallint")
    private Integer specialType;

	/* 아이템명 */
    @Column(name = "item_nm")
    private String itemNm;

	/* 아이템 원산지 */
    @Column(name = "country")
    private String country;

	/* 브랜드 아이디 */
    @Column(name = "brand_id")
    private Integer brandId;

	/* 아이템 상세설명 이미지 */
    @Column(name = "item_detail_img")
    private String itemDetailImg;

	/* 아이템 상세설명 이미지2 */
    @Column(name = "item_detail_img2")
    private String itemDetailImg2;

	/* 아이템 대표 이미지 경로 */
    @Column(name = "img_path")
    private String imgPath;

	/* 조단백질 */
    @Column(name = "crude_protein")
    private String crudeProtein;

	/* 조지방 */
    @Column(name = "crude_fat")
    private String crudeFat;

	/* 조섬유 */
    @Column(name = "crude_fiber")
    private String crudeFiber;

	/* 조회분 */
    @Column(name = "crude_ash")
    private String crudeAsh;

	/* 칼슘 */
    @Column(name = "calcium")
    private String calcium;

	/* 인 */
    @Column(name = "phosphorus")
    private String phosphorus;

	/* 수분 */
    @Column(name = "moisture")
    private String moisture;

	/* 탄수화물 */
    @Column(name = "carbohydrate", nullable = false)
    private String carbohydrate;

	/* 탄수화물(kcal) */
    @Column(name = "kcal", nullable = false)
    private String kcal;

	/* 알갱이 크기 */
    @Column(name = "size")
    private String size;

	/* 알갱리 크기분류 */
    @Column(name = "size_type", columnDefinition = "tinyint")
    private Integer sizeType;

	/* 아이템 함유 성분 */
    @Column(name = "ingredients")
    private String ingredients;

	/* 아이템 원료구성 */
    @Column(name = "material_composition")
    private String materialComposition;

	/* 아이템 정보고시 유형 코드 */
    @Column(name = "item_notice_code")
    private String itemNoticeCode;

	/* 네이버api 검색어 */
    @Column(name = "naver_keyword", nullable = false)
    private String naverKeyword;

	/* 네이버api 상태(0:정상, 1:검색어X, 2:검색0개, 3:검색3개미만, 4:에러) */
    @Column(name = "naver_status", columnDefinition = "tinyint")
    private Integer naverStatus;

    /* 구매 링크 */
    @Column(name = "buy_link")
    private String buyLink;

	/* 아이템 등록일 */
    @Column(name = "create_dt", nullable = false)
    private Date createDt;

	/* 아이템 수정일 */
    @Column(name = "update_dt")
    private Date updateDt;

	/* 아이템 조회수 */
    @Column(name = "view_cnt")
    private Integer viewCnt;

	/* 사용여부 */
    @Column(name = "use_yn", columnDefinition = "char")
    private String useYn;

	/* 총 평점 점수 */
    @Column(name = "total_avg_score", nullable = false)
    private BigDecimal totalAvgScore;

	/* 기능들 */
    @Column(name = "functions")
    private String functions;

    public Integer getItemId() {
        return this.id;
    }
}

