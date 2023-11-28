package com.kpaas.ctv.kpaas.domain.report.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String reportUserName;

    @Column
    private String reportOrganization;

    @Column
    private String reportStatus;

    @Column
    private String location;

    @Column
    private String imgUrl;

    @Column
    private LocalDateTime date;

    @Builder
    public ReportEntity(String category, String title, String content, String reportUserName, String reportOrganization, String location,String imgUrl) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.reportUserName = reportUserName;
        this.reportOrganization = reportOrganization;
        this.location = location;
        this.reportStatus = "접수됨";
        this.date = LocalDateTime.now();
        this.imgUrl = imgUrl;
    }

    public void reportFix(String reportOrganization, String reportStatus){
        this.reportOrganization = reportOrganization;
        this.reportStatus = reportStatus;
    }

    public void changeReportStatus(String reportStatus){
        this.reportStatus = reportStatus;
    }
}
