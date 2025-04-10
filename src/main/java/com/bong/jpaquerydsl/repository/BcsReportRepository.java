package com.bong.jpaquerydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bong.jpaquerydsl.model.BcsReport;

@Repository
public interface BcsReportRepository extends JpaRepository<BcsReport, Integer> {

}
