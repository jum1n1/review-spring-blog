package com.sparta.reviewassignment.comment.service;

import com.sparta.reviewassignment.comment.dto.CommentRequestDto;
import com.sparta.reviewassignment.comment.entity.Comment;
import com.sparta.reviewassignment.comment.repository.CommentRepository;
import com.sparta.reviewassignment.post.entity.Post;
import com.sparta.reviewassignment.post.repository.PostRepository;
import com.sparta.reviewassignment.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public CommentService(CommentRepository commentRepository,PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public void creatComment(Long id,CommentRequestDto commentRequestDto, User user) {
        Post post  = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시글이 없습니다"));
        Comment comment = new Comment(commentRequestDto);
        comment.setPost(post);
        comment.setUser(user);

        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if(!user.getId().equals(comment.getUser().getId())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다");
        }

        comment.update(commentRequestDto);
    }
    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("없는 댓글입니다."));

        if(!user.getId().equals(comment.getUser().getId())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다");
        }
        commentRepository.delete(comment);
    }


}
