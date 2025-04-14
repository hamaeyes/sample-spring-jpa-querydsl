package com.bong.jpaquerydsl.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usn")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_type", columnDefinition = "tinyint")
    private Integer userType;

    private String passwd;

    private String nicknm;

    @Column(name = "mobile_num")
    private String mobileNum;

    @Column(name = "sns_key")
    private String snsKey;

    private String role;

    @Column(name = "create_dt")
    private LocalDateTime createDt;

//    @ApiModelProperty(value = "사용자 추가정보")
//    @OneToOne(mappedBy = "user", fetch = LAZY)
//    private UserAddition userAddition;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = LAZY)
    private Set<UserPet> userPets = Sets.newHashSet();

    @ToString.Exclude
    @Builder.Default
    @Transient
    private List<Integer> followings = Lists.newArrayList();

    @ToString.Exclude
    @Builder.Default
    @Transient
    private List<Integer> followers = Lists.newArrayList();

    public void addPet(UserPet userPet) {
        this.userPets = Optional.ofNullable(this.userPets).orElse(Sets.newHashSet());
        userPet.setUser(this);
        this.userPets.add(userPet);
    }

    public Integer getUsn() {
        return this.id;
    }

    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }
}