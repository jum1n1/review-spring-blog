package com.sparta.reviewassignment.post.repository;

import com.sparta.reviewassignment.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderByCreateAtDesc();
}
