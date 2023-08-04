package com.sparta.reviewassignment.comment.repository;

import com.sparta.reviewassignment.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
