package com.app.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity {
    //추상 글래스인 이유는 객체로 생성을 하지 못하게 설정하기 위해서임
    // 앞서 JpaAudit기능을 통해 생성자와 수정자를 Api request uri가 등록되도록 설정했음
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    //Entity의 생성자 저장

    @LastModifiedBy
    private String modifiedBy;
    //Entity의 수정자 저장
}
