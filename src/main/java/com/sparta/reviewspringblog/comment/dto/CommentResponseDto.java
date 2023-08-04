package com.sparta.reviewassignment.comment.dto;

import com.sparta.reviewassignment.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment){
        super(); // 부모를 가르킴
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createAt = comment.getCreateAt();
        this.modifiedAt = comment.getModifiedAt();
    }

}
