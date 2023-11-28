package com.kpaas.ctv.kpaas.domain.report.dto.req;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReportDto(
        String category,
        String title,
        String content,
        String reportUserName,
        String reportOrganization,
        String reportStatus,
        LocalDateTime dateTime,
        String imgUrl) {
}
