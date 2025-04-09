package com.arifng.jpaquerydsl.model;

import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Entity
@Table(name = "user_pet_sickness")
public class UserPetSickness implements Serializable {

    /* 반려동물ID */
    @Id
    @Column(name = "pet_id", nullable = false)
    private Integer petId;

    /* 질병ID */
    @Id
    @Column(name = "sickness_id", nullable = false)
    private String sicknessId;

    @MapsId(value = "petId")
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "pet_id")
    private UserPet userPet;

    @MapsId(value = "sicknessId")
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "sickness_id")
    private HcSickness sickness;

    /* USN */
    @Column(name = "usn", nullable = false)
    private Integer usn;

}