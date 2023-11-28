package com.kpaas.ctv.kpaas.domain.report.dto.req;

public record ReportRequest(
        String category,
        String title,
        String content) {
}
