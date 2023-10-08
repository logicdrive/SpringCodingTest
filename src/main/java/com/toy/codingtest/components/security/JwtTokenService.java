package com.toy.codingtest.components.security;

import static java.util.stream.Collectors.joining;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.toy.codingtest.user.components.entities.UserEntity;
import com.toy.codingtest.user.manageUser.services.ManageUserService;
import com.toy.codingtest.user.signIn.dtos.SignInDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final AuthenticationManager authenticationManager; // Spring Security를 활용한 인증을 위해서
    private final JwtEncoder jwtEncoder; // JWT의 간편한 생성을 위한 외부라이브러리
    private final ManageUserService manageUserService; // JWT 토큰에서 UserEntity를 얻기 위해서
    private @Value("${jwt.issuer}") String jwtConfigIssuer;
    private @Value("${jwt.expire-after-seconds}") Long jwtConfigExpireAfterSeconds;

    public String tokenValue(SignInDto signInDto) {
        try {
            // Spring Security를 활용한 인증을 수행하기 위해서
            // 인증 실패시 자동으로 예외가 발생됨
            Authentication authentication =
                this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
            
            ServiceUserDetail serviceUserDetail = ((ServiceUserDetail) authentication.getPrincipal());
            JwtEncoderParameters jwtParameters = JwtEncoderParameters.from(
                JwtClaimsSet.builder()
                    .issuer(this.jwtConfigIssuer)
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(this.jwtConfigExpireAfterSeconds))
                    .subject(serviceUserDetail.getEmail())
                    .claim("name", serviceUserDetail.getName())
                    .claim("roles", authentication.getAuthorities().stream()
                                            .map(GrantedAuthority::getAuthority)
                                            .collect(joining(" ")))
                    .build()
            );
            return this.jwtEncoder.encode(jwtParameters).getTokenValue();

        } catch (AuthenticationException ex) {
            throw new AuthenticationFailedException(ex.getMessage());
        }
    }

    // 현재 유저가 전송한 JWT에 대한 정보가 담긴 객체를 얻기 위해서
    public JwtDto jwtDto() {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null) throw new Exception("JWT 토큰에서 권한 추출에 실패했습니다.");
    
            Object principalObject = authentication.getPrincipal();
            if(!(principalObject instanceof Jwt)) throw new Exception("Jwt 변환에 실패했습니다.");

            Jwt jwt = (Jwt)principalObject;
            return JwtDto.builder()
                .email(jwt.getSubject())
                .name(jwt.getClaim("name"))
                .build();

        } catch(Exception ex) {
            throw new JwtTokenProcessException(ex.getMessage());
        }
    }

    // 현재 유저가 전송한 JWT와 매칭되는 유저 테이블 정보를 얻기 위해서
    public UserEntity userEntity() {
        try {

            return this.manageUserService.findByEmail(this.jwtDto().getEmail());

        } catch(Exception ex) {
            throw new JwtTokenProcessException(ex.getMessage());
        }
    }
}
