package com.kpaas.ctv.kpaas.domain.report.service;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.report.domain.ReportEntity;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportDto;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportRequest;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReportService {

    ResponseEntity<BaseResponse> reportCreate(ReportRequest request, MultipartFile multipartFile ,Authentication authentication) throws IOException;

    default ReportEntity dtoToEntity(ReportDto dto){
        return ReportEntity.builder()
                .category(dto.category())
                .title(dto.title())
                .content(dto.content())
                .reportUserName(dto.reportUserName())
                .reportOrganization(dto.reportOrganization())
                .imgUrl(dto.imgUrl())
                .build();
    }

    default ReportDto requestToDto(ReportRequest request, UserEntity user, String url){
        return ReportDto.builder()
                .category(request.category())
                .title(request.title())
                .content(request.content())
                .reportUserName(user.getUserName())
                .reportOrganization("산림청") // 이거 수정 필요함
                .imgUrl(url)
                .build();
    }


}
