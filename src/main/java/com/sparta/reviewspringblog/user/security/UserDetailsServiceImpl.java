package com.sparta.reviewassignment.user.security;

import com.sparta.reviewassignment.user.entity.User;
import com.sparta.reviewassignment.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        User user = userRepository.findByNickName(nickName).orElseThrow(()
                -> new UsernameNotFoundException("Not Found" + nickName ));
        return new UserDetailsImpl(user);

    }

}
