package com.toy.codingtest.components.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// TODO : JWT 인증을 위한 필터 생성시키기
public class JwtTokenFilter {

}
// @Component
// public class JwtTokenFilter extends OncePerRequestFilter  {

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
//     }
    
// }
