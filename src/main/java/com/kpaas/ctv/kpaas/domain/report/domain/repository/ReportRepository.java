package com.kpaas.ctv.kpaas.domain.report.domain.repository;

import com.kpaas.ctv.kpaas.domain.report.domain.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity , Long> {
}
