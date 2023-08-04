package com.sparta.reviewassignment.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스를 상속 받을 경우 만든 필드의 값을 컬럼으로 인식해줌(추상 아니어도됨)
// => Entity class의 상속
@EntityListeners(AuditingEntityListener.class) // Auditing 기능 추가
// =>
public abstract class TimeStamped {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP) // 날짜 데이터를 매핑 할 때 사용
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

}
