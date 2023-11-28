package com.kpaas.ctv.kpaas.domain.report.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReportResponseDto(
        Long id,
        String category,
        String title,
        String content,
        String reportUserName,
        String reportOrganization,
        String reportStatus,
        String location,
        LocalDateTime dateTime,
        String imgUrl) {
}