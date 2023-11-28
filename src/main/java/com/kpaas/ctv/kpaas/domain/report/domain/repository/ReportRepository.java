package com.kpaas.ctv.kpaas.domain.report.domain.repository;

import com.kpaas.ctv.kpaas.domain.report.domain.ReportEntity;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity , Long> {
    List<ReportEntity> findByReportUserNameContaining(String reportUserName, Sort id);
}
