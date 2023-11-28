package com.kpaas.ctv.kpaas.domain.report.service;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.auth.domain.repository.UserRepository;
import com.kpaas.ctv.kpaas.domain.report.domain.ReportEntity;
import com.kpaas.ctv.kpaas.domain.report.domain.repository.ReportRepository;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportDto;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportRequest;
import com.kpaas.ctv.kpaas.domain.report.exception.ReportException;
import com.kpaas.ctv.kpaas.global.common.S3.S3Uploader;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final S3Uploader s3Uploader;

    @Override
    public ResponseEntity<BaseResponse> reportCreate(ReportRequest request, MultipartFile multipartFile, Authentication authentication) throws IOException {
        BaseResponse baseResponse = new BaseResponse();
        if (multipartFile.isEmpty()) throw ReportException.notImageInFile();

        String imgUrl = s3Uploader.upload(multipartFile, "images");

        String userAccount = authentication.getName();
        UserEntity user = userRepository.findByUserAccount(userAccount).orElseThrow(ReportException::userNotFound);

        ReportDto dto = requestToDto(request,user,imgUrl);
        ReportEntity entity = dtoToEntity(dto);

        reportRepository.save(entity);

        baseResponse.of(HttpStatus.OK, "신고 성공");

        return ResponseEntity.ok(baseResponse);
    }
}
