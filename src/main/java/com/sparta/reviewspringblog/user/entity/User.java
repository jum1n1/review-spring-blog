package com.sparta.reviewassignment.user.entity;

import com.sparta.reviewassignment.user.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String checkPassword;

    public User(SignupRequestDto signupRequestDto){
        this.nickName = signupRequestDto.getNickName();
        this.password = signupRequestDto.getPassword();
        this.checkPassword = signupRequestDto.getCheckPassword();
    }

}
