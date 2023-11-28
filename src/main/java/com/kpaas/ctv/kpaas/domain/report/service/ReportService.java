package com.kpaas.ctv.kpaas.domain.report.service;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.report.domain.ReportEntity;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportDto;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportFixRequest;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportRequest;
import com.kpaas.ctv.kpaas.domain.report.dto.res.ReportResponseDto;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReportService {

    ResponseEntity<BaseResponse> reportCreate(ReportRequest request, MultipartFile multipartFile ,Authentication authentication) throws IOException;

    ResponseEntity<BaseResponse> reportFix(ReportFixRequest request, Authentication authentication);

    ResponseEntity<BaseResponse> myReport(Authentication authentication);

    ResponseEntity<BaseResponse> reportRead(Long id);

    ResponseEntity<BaseResponse> allReport();



    default ReportEntity dtoToEntity(ReportDto dto){
        return ReportEntity.builder()
                .category(dto.category())
                .title(dto.title())
                .content(dto.content())
                .reportUserName(dto.reportUserName())
                .reportOrganization(dto.reportOrganization())
                .location(dto.location())
                .imgUrl(dto.imgUrl())
                .build();
    }

    default ReportDto requestToDto(ReportRequest request, UserEntity user, String url) {
        return ReportDto.builder()
                .category(request.category())
                .title(request.title())
                .content(request.content())
                .reportUserName(user.getUserName())
                .reportOrganization("대기중") // 이거 수정 필요함
                .location(request.location())
                .imgUrl(url)
                .build();
    }

    default ReportResponseDto entityToDto(ReportEntity entity){
        return ReportResponseDto.builder()
                .id(entity.getId())
                .category(entity.getCategory())
                .title(entity.getTitle())
                .content(entity.getContent())
                .reportUserName(entity.getReportUserName())
                .dateTime(entity.getDate())
                .reportStatus(entity.getReportStatus())
                .imgUrl(entity.getImgUrl())
                .location(entity.getLocation())
                .reportOrganization(entity.getReportOrganization())
                .build();
    }


}
