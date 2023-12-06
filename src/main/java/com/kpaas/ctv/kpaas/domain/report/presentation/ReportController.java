package com.kpaas.ctv.kpaas.domain.report.presentation;

import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportFixRequest;
import com.kpaas.ctv.kpaas.domain.report.dto.req.ReportRequest;
import com.kpaas.ctv.kpaas.domain.report.service.ReportService;
import com.kpaas.ctv.kpaas.global.common.dto.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "신고 요청 api", description = "신고 요청 관련 api 모음")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ReportController {
    private final ReportService reportService;
    @PostMapping("/report")
    public ResponseEntity<BaseResponse> reportCreate(@RequestPart("data") ReportRequest request, @RequestPart("image")MultipartFile multipartFile, Authentication authentication) throws IOException {
        return reportService.reportCreate(request, multipartFile, authentication);
    }

    @GetMapping("/report/my")
    public ResponseEntity<BaseResponse> myReport(Authentication authentication){
        return reportService.myReport(authentication);
    }


    @GetMapping("/report/{id}")
    public ResponseEntity<BaseResponse> ReportRead(@PathVariable("id") Long id){
        return reportService.reportRead(id);
    }

    @GetMapping("/report/all")
    public ResponseEntity<BaseResponse> allReport(){
        return reportService.allReport();
    }




    @PatchMapping("/report/fix")
    public ResponseEntity<BaseResponse> reportFix(@RequestBody ReportFixRequest request, Authentication authentication){
        return reportService.reportFix(request, authentication);
    }
}

