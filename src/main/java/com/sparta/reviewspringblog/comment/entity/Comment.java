package com.sparta.reviewassignment.comment.entity;

import com.sparta.reviewassignment.comment.dto.CommentRequestDto;
import com.sparta.reviewassignment.post.entity.Post;
import com.sparta.reviewassignment.post.entity.TimeStamped;
import com.sparta.reviewassignment.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="comments")
@NoArgsConstructor
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void setPost(Post post){
        this.post = post;
    }

    public Comment(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getContent();
    }

    public void update(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getContent();
    }
}
