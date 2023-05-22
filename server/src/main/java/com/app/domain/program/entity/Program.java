package com.app.domain.program.entity;

import com.app.domain.common.BaseEntity;
import com.app.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Program extends BaseEntity {

    //pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long programId;

    //서비스 id

    private String servId;

    //즐겨찾기 시 저장되는 회원 Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    //서비스 명
    @Column(nullable = false, length = 100)
    private String servNm;

    //서비스 요약
    @Column(length = 200)
    private String servDgst;

    //지원주기
    @Column(length = 20)
    private String sprtCycnm;

    //제공유형
    @Column(length = 20)
    private String srvPvsnNm;

    //신청방법
    @Column(length = 50)
    private String aplyMtdNm;

    //시도명(지자체정보)
    @Column(length = 20)
    private String ctpvNm;

    //시군구명
    @Column(length = 20)
    private String sggNm;

    @Builder
    public Program(long programId, String servId, String servNm, String servDgst, String sprtCycnm
            , String srvPvsnNm, String aplyMtdNm, String ctpvNm, String sggNm ){
        this.programId = programId;
        this.servId = servId;
        this.servNm = servNm;
        this.servDgst = servDgst;
        this.sprtCycnm = sprtCycnm;
        this.srvPvsnNm = srvPvsnNm;
        this.aplyMtdNm = aplyMtdNm;
        this.ctpvNm = ctpvNm;
        this.sggNm = sggNm;
    }
}
