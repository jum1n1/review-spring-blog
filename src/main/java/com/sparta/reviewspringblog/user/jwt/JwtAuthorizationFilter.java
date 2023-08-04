package com.sparta.reviewassignment.user.jwt;

import com.sparta.reviewassignment.user.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl uerDetailsServiceImpl;

    // 메인 Filter
    @Override
    protected void doFilterInternal (HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String tokenValue = jwtUtil.getTokenFromRequest(req);

        if(StringUtils.hasText(tokenValue)){
            tokenValue = jwtUtil.substringToken(tokenValue);

            if(!jwtUtil.validateToken(tokenValue)){
                logger.error("Token Error");
                return;
            }
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);

            try{
                setAuthentication(info.getSubject());
            } catch (Exception e){
                logger.error(e.getMessage());
                return;
            }
        }
        filterChain.doFilter(req,res);
    }

    // 인증 처리 => 완료되면 uerDetailsImpl을 쓸수 있음
    private void setAuthentication(String nickName) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(nickName);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    //인증객체생성
    private Authentication createAuthentication(String nickName) {
        UserDetails userDetails = uerDetailsServiceImpl.loadUserByUsername(nickName);
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
