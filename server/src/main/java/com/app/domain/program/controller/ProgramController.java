package com.app.domain.program.controller;

import com.app.domain.common.MultiResponseDto;
import com.app.domain.common.SingleResponseDto;
import com.app.domain.member.entity.Member;
import com.app.domain.member.repository.MemberRepository;
import com.app.domain.member.service.MemberService;
import com.app.domain.program.client.ProgramRetrieveClient;
import com.app.domain.program.dto.ProgramDto;
import com.app.domain.program.entity.Program;
import com.app.domain.program.mapper.ProgramMapper;
import com.app.domain.program.repository.ProgramRepository;
import com.app.domain.program.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/programs")
@Validated
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramRetrieveClient programRetrieveClient;

    private final ProgramService programService;

    private final ProgramMapper programMapper;

    private final MemberService memberService;


    /**
     * 프로그램 목록 조회
     * @param listRequest
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity getPrograms(@ModelAttribute ProgramDto.ListRequest listRequest,HttpServletRequest httpServletRequest){
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

        if(memberService.checkLogin(httpServletRequest)){
            Member member = memberService.getLoginMember(httpServletRequest);
            ProgramDto.ListResponse programListResponse = programRetrieveClient.getList(programListRequestDto);
            // 추가 메서드
            List<String> serviceIdList = programService.getServiceIdList(member.getMemberId());

            for (ProgramDto.ServList p : programListResponse.getServList()) {
                p.checkLike(serviceIdList.contains(p.getServId()));
            }
            return new ResponseEntity<>(
                    new SingleResponseDto<>(programListResponse), HttpStatus.OK
            );
        }
        ProgramDto.ListResponse programListResponse = programRetrieveClient.getList(programListRequestDto);

        return new ResponseEntity<>(
                new SingleResponseDto<>(programListResponse), HttpStatus.OK
        );
    }

    /**
     * 회원 별 프로그램 추천 목록 조회
     */
    @GetMapping("/recommendlist")
    public ResponseEntity getPrograms(@ModelAttribute ProgramDto.RecommendListRequest recommendListRequest, HttpServletRequest httpServletRequest){
        Member member = memberService.getLoginMember(httpServletRequest);
        ProgramDto.RecommendListRequest programRecommendListRequestDto = ProgramDto.RecommendListRequest.builder()
                .serviceKey(recommendListRequest.getServiceKey())
                .pageNo(recommendListRequest.getPageNo())
                .numOfRows(recommendListRequest.getNumOfRows())
                .age(member.getAge())
                .ctpvNm(member.getRegion())
                .lifeArray(recommendListRequest.getLifeArray())
                .arrgOrd(recommendListRequest.getArrgOrd())
                .build();

        ProgramDto.ListResponse programListResponse = programRetrieveClient.getRecommendList(programRecommendListRequestDto);

        List<String> serviceIdList = programService.getServiceIdList(member.getMemberId());

        for (ProgramDto.ServList p : programListResponse.getServList()) {
            p.checkLike(serviceIdList.contains(p.getServId()));
        }

        return new ResponseEntity<>(
                new SingleResponseDto<>(programListResponse), HttpStatus.OK
        );
    }

    /**
     * 프로그램 상세 조회
     * @param detailRequest
     * @return
     */
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


    /**
     * 프로그램 즐겨찾기 기능
     * @param saveRequest
     * @param request
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity saveProgram(@RequestBody ProgramDto.SaveRequest saveRequest, HttpServletRequest request){
        Member member = memberService.getLoginMember(request);
        Program program = programService.saveProgram(programMapper.SaveRequestToProgram(saveRequest), member);
        ProgramDto.SaveResponse saveResponse = programMapper.programToSaveResponse(program);

        return new ResponseEntity<>(
                new SingleResponseDto<>(saveResponse), HttpStatus.OK
        );
    }


    /**
     * 즐겨찾기한 프로그램 조회 기능
     * @param request
     * @param page
     * @param size
     * @return
     */
    @GetMapping("savelist")
    public ResponseEntity getSavePrograms(HttpServletRequest request,
                                          @Positive @RequestParam(defaultValue = "1") int page,
                                          @Positive @RequestParam(defaultValue = "10") int size){
        Member member = memberService.getLoginMember(request);
        Long memberId = member.getMemberId();
        System.out.println(memberId);
        Page<Program> programPage = programService.searchSavePrograms(memberId, page-1, size);
        List<Program> programList =programPage.getContent();
        List<ProgramDto.SaveResponse> response = programMapper.programsToSaveResponses(programList);

        return new ResponseEntity<>(new MultiResponseDto<>(response, programPage), HttpStatus.OK);

    }
}
