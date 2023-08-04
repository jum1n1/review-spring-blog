package com.sparta.reviewassignment.user.service;

import com.sparta.reviewassignment.user.dto.LoginRequestDto;
import com.sparta.reviewassignment.user.dto.SignupRequestDto;
import com.sparta.reviewassignment.user.entity.User;
import com.sparta.reviewassignment.user.jwt.JwtUtil;
import com.sparta.reviewassignment.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }


    public void signup(SignupRequestDto signupRequestDto) {
        User user = new User(signupRequestDto);

        // 닉네임과 같은 값이 포함된 경우
        if (user.getPassword().contains(user.getNickName())) {
            throw new IllegalArgumentException("비밀번호를 닉네임과 다르게 입력해주세요.");
        }

        // 비밀번호 확인은 비밀번호와 정확하게 일치
        if (!user.getPassword().equals(user.getCheckPassword())) {
            throw new IllegalArgumentException("확인용 비밀번호와 비밀번호가 다릅니다.");
        }

        // 닉네임 중복 확인
        if (userRepository.findByNickName(user.getNickName()).isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
        userRepository.save(user);
    }


    public void login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        // 받은 정보를 그냥 저장하는 회원가입과 다르게 받은 정보를 확인하고 저장한 정보를 가져오는 거임
        String nickName = loginRequestDto.getNickName();
        String passWord = loginRequestDto.getPassword();

        User user = userRepository.findByNickName(nickName).orElseThrow();

        if(!user.getPassword().matches(passWord)){
            throw new IllegalArgumentException("비밀번호가 상이합니다.");
        }

        String token = jwtUtil.createToken(user.getNickName());
        jwtUtil.addJwtToCookie(token,res);

    }
}

