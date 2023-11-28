package com.kpaas.ctv.kpaas.domain.report.presentation;

import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportRequest;
import com.kpaas.ctv.kpaas.domain.report.service.ReportService;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @PostMapping("/report")
    public ResponseEntity<BaseResponse> reportCreate(@RequestPart("data") ReportRequest request, @RequestPart("image")MultipartFile multipartFile, Authentication authentication) throws IOException {
        return reportService.reportCreate(request, multipartFile, authentication);
    }



}
