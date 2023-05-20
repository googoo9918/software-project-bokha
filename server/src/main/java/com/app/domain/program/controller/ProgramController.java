package com.app.domain.program.controller;

import com.app.domain.common.SingleResponseDto;
import com.app.domain.program.client.ProgramRetrieveClient;
import com.app.domain.program.dto.ProgramDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.SpringQueryMap;
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
    public ResponseEntity getPrograms(@ModelAttribute ProgramDto.Request request){
        ProgramDto.Request programRequestDto = ProgramDto.Request.builder()
                .pageNo(request.getPageNo())
                .numOfRows(request.getNumOfRows())
                .lifeArray(request.getLifeArray())
                .trgterIndvdlArray(request.getTrgterIndvdlArray())
                .intrsThem(request.getIntrsThem())
                .age(request.getAge())
                .ctpvNm(request.getCtpvNm())
                .sggNM(request.getSggNM())
                .srchKeyCode(request.getSrchKeyCode())
                .searchWrd(request.getSearchWrd())
                .arrgOrd(request.getArrgOrd())
                .build();

        System.out.println("=========================================");
        System.out.println(programRequestDto.getNumOfRows());
        System.out.println("=========================================");
        ProgramDto.Response programResponse = programRetrieveClient.getList(programRequestDto);
        return new ResponseEntity<>(
                new SingleResponseDto<>(programResponse), HttpStatus.OK
        );
    }
}
