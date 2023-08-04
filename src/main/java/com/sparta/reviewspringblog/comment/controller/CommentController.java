package com.sparta.reviewassignment.comment.controller;

import com.sparta.reviewassignment.comment.dto.CommentRequestDto;
import com.sparta.reviewassignment.comment.service.CommentService;
import com.sparta.reviewassignment.user.dto.MsgResponseDto;
import com.sparta.reviewassignment.user.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<MsgResponseDto> creatComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal  UserDetailsImpl userDetails){
        try{
            commentService.creatComment(id,commentRequestDto, userDetails.getUser());
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().body(new MsgResponseDto("로그인 후 시도해주세요"));
        }
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 달기 완료!"));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<MsgResponseDto> updateComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto commentRequestDto){
        try {
            commentService.updateComment(id,commentRequestDto,userDetails.getUser());
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().body(new MsgResponseDto("로그인 후 시도해주세요"));
        }
        return ResponseEntity.ok().body(new MsgResponseDto("댓글 수정 완료!"));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<MsgResponseDto> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){

        try{
            commentService.deleteComment(id,userDetails.getUser());
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().body(new MsgResponseDto("로그인 후 시도해주세요"));
        }
        return ResponseEntity.ok().body(new MsgResponseDto("삭제 완료!"));

    }

}
