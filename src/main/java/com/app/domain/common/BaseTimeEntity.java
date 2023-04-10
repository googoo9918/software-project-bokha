package com.app.domain.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
// 부모클래스는 테이블과 매핑하지 않고 부모클래스를 상속받는 자식클래스의 매핑 정보를 제공할 때 활용
@Getter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;
    //엔티티 생성 시간 저장

    @LastModifiedDate
    private LocalDateTime updateTime;
    //엔티티 수정 시간 저장
}
