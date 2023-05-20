package com.app.domain.program.entity;

import com.app.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program extends BaseEntity {

    //서비스 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int servId;

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

    //시도명
    @Column(length = 20)
    private String ctpvNm;

    //시군구명
    @Column(length = 20)
    private String sggNm;

    @Builder
    public Program(int servId, String servNm, String servDgst, String sprtCycnm
            , String srvPvsnNm, String aplyMtdNm, String ctpvNm, String sggNm ){
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
