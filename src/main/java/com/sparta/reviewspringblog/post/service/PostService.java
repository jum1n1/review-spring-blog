package com.sparta.reviewassignment.post.service;

import com.sparta.reviewassignment.post.dto.PostRequestDto;
import com.sparta.reviewassignment.post.dto.PostResponseDto;
import com.sparta.reviewassignment.post.entity.Post;
import com.sparta.reviewassignment.post.repository.PostRepository;
import com.sparta.reviewassignment.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public PostResponseDto create(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto);
        post.setUser(user); // user_id 저장

        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public List<PostResponseDto> read() {
        return postRepository.findAllByOrderByCreateAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto readId(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 게시물입니다."));

        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow();

        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("작성자만 수정 할 수 있습니다.");
        }

        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    public void delete(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow();

        if(!user.getId().equals(post.getUser().getId())){
            throw new IllegalArgumentException("작성자만 수정 할 수 있습니다.");
        }

        postRepository.delete(post);
    }


}
