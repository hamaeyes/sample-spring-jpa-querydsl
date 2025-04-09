package com.bong.jpaquerydsl.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;

@Getter
@Entity
@Table(name = "user_addition")
public class UserAddition {
    /* 회원시퀀스 */
    @Id
    @Column(name = "usn")
    private Integer usn;

    @MapsId(value = "usn")
    @OneToOne
    @JoinColumn(name = "usn")
    private User user;

    /* 이메일수신여부 */
    @Transient
    private String emailReceiveYn="Y";

    /* SMS수신여부 */
    @Transient
    private String smsReceiveYn="Y";

    /* 이메일수신동의일 */
    @Column(name = "email_receive_agree_dt")
    private LocalDate emailReceiveAgreeDt;

    /* SMS수신동의일 */
    @Column(name = "sms_receive_agree_dt")
    private LocalDate smsReceiveAgreeDt;

    /* 마케팅수신고지일 */
    @Column(name = "marketing_customer_noti_dt")
    private LocalDate marketingCustomerNotiDt;

    /* 최종로그인시간 */
    @Column(name = "lastlogin_dt")
    private LocalDateTime lastLoginDt;

    /* 알림확인시간 */
    @Column(name = "allim_read_dt")
    private LocalDateTime allimReadDt;

    /* 프로필메세지 */
    @Column(name = "profile_msg")
    private String profileMsg;

    /* 프로필이미지경로 */
    @Column(name = "profile_img_path")
    private String profileImgPath;

    /* 시도코드 */
    @Column(name = "sido_cd")
    private String sidoCd;
    /* 시군구코드 */
    @Column(name = "sigungu_cd")
    private String sigunguCd;

    /* 강아지 수 */
    @Column(name = "dog_cnt")
    private Integer dogCnt;

    /* 고양이 수 */
    @Column(name = "cat_cnt")
    private Integer catCnt;

    /* 닉네임변경시간 */
    @Column(name = "nicknm_update_dt")
    private LocalDateTime nicknmUpdateDt;

    /* 피드구독설정(팔로우만) */
    @Column(name = "feed_subscribe_conf_follow", columnDefinition = "char")
    private String feedSubscribeConfFollow;

    /* 피드 존재 유무 */
    @Column(name = "feed_exist", columnDefinition = "char")
    private String feedExist;

    /* 피드 카운트 */
    @Column(name = "feed_cnt")
    private Integer feedCnt;

    /* 유기동물 맞춤조건 알림 동의 여부 */
    @Column(name = "abandon_pet_receive_yn", columnDefinition = "char")
    private String abandonPetReceiveYn;

    /* 유기동물 맞춤조건 알림 횟수 (n: n회 발송) */
    @Column(name = "abandon_pet_receive_cnt")
    private Integer abandonPetReceiveCnt;

    public void setAbandonPetReceiveYn(String receiveYn) {
        Optional.ofNullable(receiveYn)
                .filter(yn -> yn.equals("Y") || yn.equals("N"))
                .map(yn -> yn.equals("Y") ? allowAbandonPetReceive() : disAllowAbandonPetReceive())
                .orElseThrow(() -> new IllegalArgumentException("Y or N"));
    }

    private boolean allowAbandonPetReceive() {
        this.abandonPetReceiveYn = "Y";
        return true;
    }

    private boolean disAllowAbandonPetReceive() {
        this.abandonPetReceiveYn = "N";
        return true;
    }
}
