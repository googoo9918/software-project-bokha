package com.app.domain.program.controller;

import com.app.domain.common.SingleResponseDto;
import com.app.domain.program.client.ProgramRetrieveClient;
import com.app.domain.program.dto.ProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programs")
@Validated
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramRetrieveClient programRetrieveClient;

    @GetMapping("/list")
    public ResponseEntity getPrograms(@ModelAttribute ProgramDto.ListRequest listRequest){
        ProgramDto.ListRequest programListRequestDto = ProgramDto.ListRequest.builder()
                .serviceKey(listRequest.getServiceKey())
                .pageNo(listRequest.getPageNo())
                .numOfRows(listRequest.getNumOfRows())
                .lifeArray(listRequest.getLifeArray())
                .trgterIndvdlArray(listRequest.getTrgterIndvdlArray())
                .intrsThem(listRequest.getIntrsThem())
                .age(listRequest.getAge())
                .ctpvNm(listRequest.getCtpvNm())
                .sggNM(listRequest.getSggNM())
                .srchKeyCode(listRequest.getSrchKeyCode())
                .searchWrd(listRequest.getSearchWrd())
                .arrgOrd(listRequest.getArrgOrd())
                .build();

        System.out.println("=========================================");
        System.out.println(programListRequestDto.getServiceKey());
        System.out.println("=========================================");
        ProgramDto.ListResponse programListResponse = programRetrieveClient.getList(programListRequestDto);
        return new ResponseEntity<>(
                new SingleResponseDto<>(programListResponse), HttpStatus.OK
        );
    }

    @GetMapping("/detail")
    public ResponseEntity getProgramDetails(@ModelAttribute ProgramDto.DetailRequest detailRequest){
        ProgramDto.DetailRequest programdEtailRequestDto = ProgramDto.DetailRequest.builder()
                .serviceKey(detailRequest.getServiceKey())
                .servId(detailRequest.getServId())
                .build();

        ProgramDto.DetailedResponse programDetailResponse = programRetrieveClient.getDetailed(programdEtailRequestDto);

        return new ResponseEntity<>(
                new SingleResponseDto<>(programDetailResponse), HttpStatus.OK
        );
    }
}
