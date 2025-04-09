package com.bong.jpaquerydsl.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "user_pet")
public class UserPet {

    /* 반려동물ID */
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pet_id")
    private Integer id;

    /* 회원시퀀스 */
    @ToString.Exclude
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "usn")
    private User user;

    /* 반려동물이름 */
    @Column(name = "pet_nm")
    private String petNm;

    /* 생년월일 */
    @Column(name = "birth_dt")
    private LocalDate birthDt;

    /* 입양일 */
    @Column(name = "adopt_dt")
    private LocalDate adoptDt;

    /* 축종코드 */
    @Column(name = "upkind_cd", columnDefinition = "tinyint")
    private Integer upkindCd;

    /* 품종코드 */
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "kind_cd")
    private PetKind petKind;

    /* 성별(0모름,1남,2여)") */
    @Column(name = "gender_cd", columnDefinition = "tinyint")
    private Integer genderCd;

    /* 중성화(0모름,1예,2아니오) */
    @Column(name = "neuter_cd", columnDefinition = "tinyint")
    private Integer neuterCd;

    /* 반려동물 이미지경로 */
    @Column(name = "pet_img_path")
    private String petImgPath;

    /* 등록일 */
    @Column(name = "create_dt")
    private LocalDateTime createDt;

    /* 무게 */
    private BigDecimal weight;

    /* 비만지수 */
    @Column(name = "body_score", columnDefinition = "tinyint")
    private Integer bodyScore;

    /* 사료 */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne()
    @JoinColumn(name = "food_item_id")
    private CurationItem foodItem;

    /* 직접입력 사료명 */
    @Column(name = "custom_food_item_nm")
    private String customFoodItemNm;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "userPet", orphanRemoval = true, cascade = ALL)
    private Set<UserPetSickness> userPetSicknesses;

    public void setUser(User user) {
        this.user = user;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
}