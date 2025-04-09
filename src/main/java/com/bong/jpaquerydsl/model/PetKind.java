package com.bong.jpaquerydsl.model;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bong.jpaquerydsl.common.converter.SetCharacteristicConverter;
import com.bong.jpaquerydsl.common.enums.Characteristic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "pet_kind")
public class PetKind {
    //@ApiModelProperty(value = "품종코드")
    @Id // @GeneratedValue(strategy = IDENTITY)
    @Column(name = "kind_cd")
    private String id;

    //@ApiModelProperty(value = "품종명")
    @Column(name = "kind_nm")
    private String kindNm;

    //@ApiModelProperty(value = "축종코드")
    @Column(name = "upkind_cd", columnDefinition = "tinyint")
    private Integer upkindCd;

    //@ApiModelProperty(value = "스페셜타입", notes = "0모름, 100기타, 101소형견, 102중형견, 103대형견, 200기타, 201단모종, 202장모종")
    @Column(name = "special_type", columnDefinition = "smallint")
    private Integer specialType;

    //@ApiModelProperty(value = "인기품종여부")
    @Column(name = "popular_kind_yn", columnDefinition = "char")
    private String popularKindYn;

    //@ApiModelProperty(value = "그룹아이디")
    @Column(name = "kind_group_cd")
    private String kindGroupCd;

    //@ApiModelProperty(value = "품종이미지경로")
    @Column(name = "kind_img_path")
    private String kindImgPath;

    //@ApiModelProperty(value = "품종소스", notes = "0추가품종, 1동물보호센터품종")
    @Column(name = "kind_src", columnDefinition = "bit")
    private String kindSrc;

    //@ApiModelProperty(value = "품종요약")
    @Column(name = "kind_summary")
    private String kindSummary;

    //@ApiModelProperty(value = "품종특징")
    @Column(name = "kind_detail")
    private String kindDetail;

    //@ApiModelProperty(value = "품종별 권장 운동시간")
    @Column(name = "kind_exercise")
    private String kindExercise;

    //@ApiModelProperty(value = "건강관리코드", notes = "a털/피부, b눈물자국~g추위에약함")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_manage", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindManage;

    //@ApiModelProperty(value = "추천제외사항코드", notes = "a어린아기, b1인가정~e아파트")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_careful", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindCareful;

    //@ApiModelProperty(value = "크기코드", notes = "a작음, b보통, c큼")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_size", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindSize;

    //@ApiModelProperty(value = "성격코드", notes = "a조용함, b온화함~f영리함")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_character", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindCharacter;

    //@ApiModelProperty(value = "의존도코드", notes = "a작음, b보통, c큼")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_dependence", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindDependence;

    //@ApiModelProperty(value = "사회성코드", notes = "a독점력 강함, b다른 동물과 잘지냄 ~ d아이있는집제외")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_sociality", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindSociality;

    //@ApiModelProperty(value = "운동량코드", notes = "a작음, b보통, c큼")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_momentum", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindMomentum;

    //@ApiModelProperty(value = "탈모코드", notes = "a작음, b보통, c큼")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_alopecia", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindAlopecia;

    //@ApiModelProperty(value = "털길이", notes = "a장모, b단모")
    @Convert(converter = SetCharacteristicConverter.class)
    @Column(name = "kind_fur", columnDefinition = "set('a','b','c','d','e','f','g','h')")
    private Set<Characteristic> kindFur;

    public String getKindCd() {
        return this.id;
    }
}
