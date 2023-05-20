package com.app.domain.program.dto;

import lombok.*;

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
    public static class Request{

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

    @ToString
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlRootElement(name = "wantedList")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Response {
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

        //
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
}
