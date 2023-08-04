package com.sparta.reviewassignment.post.entity;

import com.sparta.reviewassignment.comment.entity.Comment;
import com.sparta.reviewassignment.post.dto.PostRequestDto;
import com.sparta.reviewassignment.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor // 생성자를 만들면 기본 생성자가 사라지기 떄문에 추가해줘야함
public class Post extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String nickName;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public void setUser(User user){
        this.user = user;
    }

    public Post(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.nickName = postRequestDto.getNickName();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.nickName = postRequestDto.getNickName();
    }
}
