package com.app.domain.program.dto;

import com.app.domain.program.entity.Program;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


public class ProgramDto {
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListRequest {
        //서비스 인증키
        private String serviceKey;

        //페이지 시작 위치
        private String pageNo;

        //출력건수
        private String numOfRows;

        //생애주기
        private String lifeArray;

        //가구상황
        private String trgterIndvdlArray;

        //관심주제
        private String intrsThem;

        //나이
        private String age;

        //시도명
        private String ctpvNm;

        //시군구명
        private String sggNM;

        //검색분류
        private String srchKeyCode;

        //검색어
        private String searchWrd;

        //정렬순서
        private String arrgOrd;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailRequest {
        //서비스 인증키
        private String serviceKey;
        //서비스 Id
        private String servId;
    }
    @ToString
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement(name = "wantedList")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ListResponse {
        //전체 결과 수
        @XmlElement(name = "totalCount")
        private String totalCount;

        //페이지번호
        @XmlElement(name = "pageNo")
        private String pageNo;

        //한 페이지 결과 수
        @XmlElement(name = "numOfRows")
        private String numOfRows;

        //결과 코드
        @XmlElement(name = "resultCode")
        private String resultCode;

        //결과 메시지
        @XmlElement(name = "resultMessage")
        private String resultMessage;

        //데이터 목록
        @XmlElement(name = "servList")
        private List<ServList> servList;
    }

    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class ServList {

        //사업담당부서명
        @XmlElement(name = "bizChrDeptNm")
        private String bizChrDeptNm;

        //시도명
        @XmlElement(name = "ctpvNm")
        private String ctpvNm;

        // 시군구명
        @XmlElement(name = "sggNm")
        private String sggNm;

        //서비스요약
        @XmlElement(name = "servDgst")
        private String servDgst;

        //서비스상세링크
        @XmlElement(name = "servDtlLink")
        private String servDtlLink;

        //생애주기명
        @XmlElement(name = "lifeNmArray")
        private String lifeNmArray;

        //관심주제명
        @XmlElement(name = "intrsThemaNmArray")
        private String intrsThemaNmArray;

        //지원주기명
        @XmlElement(name = "sprtCycNm")
        private String sprtCycNm;

        //제공유형명
        @XmlElement(name = "srvPvsnNm")
        private String srvPvsnNm;

        //신청방법명
        @XmlElement(name = "aplyMtdNm")
        private String aplyMtdNm;

        //조회수
        @XmlElement(name = "inqNum")
        private String inqNum;

        //최종수정일자
        @XmlElement(name = "lastModYmd")
        private String lastModYmd;

        //서비스 Id
        @XmlElement(name = "servId")
        private String servId;

        //서비스명
        @XmlElement(name = "servNm")
        private String servNm;

        // 가구 상황명
        @XmlElement(name = "trgterIndvdlNmArray")
        private String trgterIndvdlNmArray;
    }

    @XmlRootElement(name = "wantedDtl")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DetailedResponse{
        //결과코드
        @XmlElement private String resultCode;
        //결과 메시지
        @XmlElement private String resultMessage;
        //서비스Id
        @XmlElement private String servId;

        //서비스명
        @XmlElement private String servNm;

        //시행시작일자
        @XmlElement private String enfcBgngYmd;

        //시행종료일자
        @XmlElement private String enfcEndYmd;

        //사업담당부서명
        @XmlElement private String bizChrDeptNm;

        //시도명
        @XmlElement private String ctpvNm;

        //시군구명
        @XmlElement private String sggNm;

        //서비스 요약
        @XmlElement private String servDgst;

        //생애주기명
        @XmlElement private String lifeNmArray;

        //가구상황명
        @XmlElement private String trgterIndvdlNmArray;

        //관심주제명
        @XmlElement private String intrsThemaNmArray;

        //지원주기명
        @XmlElement private String sprtCycNm;

        //제공유형명
        @XmlElement private String srvPvsnNm;

        //신청방법명
        @XmlElement private String aplyMtdNm;

        //지원대상 내용
        @XmlElement private String sprtTrgtCn;

        //선정기준 내용
        @XmlElement private String slctCritCn;

        //급여서비스 내용
        @XmlElement private String alwServCn;

        //신청방법 내용
        @XmlElement private String aplyMtdCn;

        //조회수
        @XmlElement private String inqNum;

        //최종 수정일자
        @XmlElement private String lastModYmd;

        // 문의처 목록
        @XmlElement(name = "inqplCtadrList")
        private List<InqplCtadr> inqplCtadrList;

        //관련웹사이트 목록
        @XmlElement(name = "inqplHmpgReldList")
        private List<InqplHmpgReld> inqplHmpgReldList;

        //근거법령 목록
        @XmlElement(name = "baslawList")
        private List<Baslaw> baslawList;

        //서식, 자료 목록
        @XmlElement(name = "basfrmList")
        private List<Basfrm> basfrmList;
    }

    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class InqplCtadr {
        //복지정보관련명
        @XmlElement private String wlfareInfoReldNm;

        //복지정보관련내용
        @XmlElement private String wlfareInfoReldCn;

        //복지정보상세코드
        @XmlElement private String wlfareInfoDtlCd;
    }

    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class InqplHmpgReld {

        //복지정보관련명
        @XmlElement private String wlfareInfoReldNm;

        //복지정보관련내용
        @XmlElement private String wlfareInfoReldCn;

        //복지정보상세코드
        @XmlElement private String wlfareInfoDtlCd;
    }

    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class Baslaw {
        //복지정보상세코드
        @XmlElement private String wlfareInfoDtlCd;

        //복지정보관련명
        @XmlElement private String wlfareInfoReldNm;

        //복지정보관련내용
        @XmlElement private String wlfareInfoReldCn;
    }

    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @Getter
    public static class Basfrm {
        //복지정보상세코드
        @XmlElement private String wlfareInfoDtlCd;

        //복지정보관련명
        @XmlElement private String wlfareInfoReldNm;

        //복지정보관련내용
        @XmlElement private String wlfareInfoReldCn;
    }

    @Getter
    public static class SaveRequest {

        //서비스 Id
        @NotBlank
        private String servId;

        //서비스명
        private String servNm;

        //서비스 요약
        private String servDgst;

        //지원주기
        private String sprtCycnm;

        //제공유형
        private String srvPvsnNm;

        //신청방법
        private String aplyMtdNm;

        //지자체 정보
        private String ctpvNm;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SaveResponse {
        //프로그램 pk(자체 db 저장)
        private Long programId;
        //서비스 Id
        @NotBlank
        private String servId;

        //회원 Id
        private Long memberId;

        //서비스명
        private String servNm;

        //서비스 요약
        private String servDgst;

        //지원주기
        private String sprtCycnm;

        //제공유형
        private String srvPvsnNm;

        //신청방법
        private String aplyMtdNm;

        //지자체 정보
        private String ctpvNm;

        public static SaveResponse ofProgram(Program program){
            return SaveResponse.builder()
                    .programId(program.getProgramId())
                    .servId(program.getServId())
                    .memberId(program.getMember().getMemberId())
                    .servNm(program.getServNm())
                    .servDgst(program.getServDgst())
                    .sprtCycnm(program.getSprtCycnm())
                    .srvPvsnNm(program.getSrvPvsnNm())
                    .aplyMtdNm(program.getAplyMtdNm())
                    .ctpvNm(program.getCtpvNm()).build();
        }
    }
}
