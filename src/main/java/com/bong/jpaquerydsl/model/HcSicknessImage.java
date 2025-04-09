package com.bong.jpaquerydsl.model;

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
@Table(name = "hc_sickness_img")
public class HcSicknessImage implements Serializable {
    @Id @Column(name = "sickness_id", nullable = false)
    private String sicknessId;

    @MapsId(value = "sicknessId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sickness_id")
    private HcSickness hcSickness;

    @Id
    @Column(name = "img_sort_num", nullable = false)
    private Integer imgSortNum;

    @Column(length = 200, name = "img_path", nullable = false)
    private String imgPath;

    public void setHcSickness(HcSickness hcSickness) {
        this.hcSickness = hcSickness;
    }
}