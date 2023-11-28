package com.kpaas.ctv.kpaas.domain.report.service;

import com.kpaas.ctv.kpaas.domain.auth.domain.UserEntity;
import com.kpaas.ctv.kpaas.domain.auth.domain.repository.UserRepository;
import com.kpaas.ctv.kpaas.domain.report.domain.ReportEntity;
import com.kpaas.ctv.kpaas.domain.report.domain.repository.ReportRepository;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportDto;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportFixRequest;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportRequest;
import com.kpaas.ctv.kpaas.domain.report.dto.res.ReportResponseDto;
import com.kpaas.ctv.kpaas.domain.report.exception.ReportException;
import com.kpaas.ctv.kpaas.global.common.S3.S3Uploader;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public ResponseEntity<BaseResponse> reportFix(ReportFixRequest request, Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();

        ReportEntity reportEntity = reportRepository.findById(request.id()).orElseThrow(ReportException::notFoundReport);
        reportEntity.reportFix(request.reportOrganization(), request.status());

        reportRepository.save(reportEntity);

        baseResponse.of(HttpStatus.OK, "신고 담당기괸 및 접수상태 설정 완료");

        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse> myReport(Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();

        String userName = userRepository.findByUserAccount(authentication.getName()).orElseThrow(ReportException::userNotFound).getUserName();
        List<ReportEntity> reportEntities = reportRepository.findByReportUserNameContaining(userName, (Sort.by(Sort.Direction.DESC, "id")));

        List<ReportResponseDto> reportResponseDtos = new ArrayList<>(reportEntities.stream()
                .map(this::entityToDto)
                .toList());

        baseResponse.of(HttpStatus.OK, "게시물 불러오기 성공" , reportResponseDtos);

        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse> reportRead(Long id) {
        BaseResponse baseResponse = new BaseResponse();

        ReportEntity reportEntity = reportRepository.findById(id).orElseThrow(ReportException::notFoundReport);

        baseResponse.of(HttpStatus.OK, "신고 조회 성공", entityToDto(reportEntity));
        return ResponseEntity.ok(baseResponse);
    }

    @Override
    public ResponseEntity<BaseResponse> allReport() {
        BaseResponse baseResponse = new BaseResponse();

        List<ReportEntity> reportEntities = reportRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<ReportResponseDto> dtos = reportEntities.stream()
                .map(this::entityToDto)
                .toList();

        baseResponse.of(HttpStatus.OK, "모든 신고 불러오기 성공", dtos);

        return ResponseEntity.ok(baseResponse);
    }
}
