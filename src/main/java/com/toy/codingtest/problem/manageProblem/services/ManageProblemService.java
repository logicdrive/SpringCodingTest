package com.toy.codingtest.problem.manageProblem.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.toy.codingtest.components.security.JwtTokenService;
import com.toy.codingtest.components.security.ServiceUserDetail;
import com.toy.codingtest.problem.components.entities.ProblemEntity;
import com.toy.codingtest.problem.components.repositories.ProblemRepository;
import com.toy.codingtest.problem.manageProblem.dtos.CreateProblemDto;
import com.toy.codingtest.user.components.entities.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageProblemService {
    private final ProblemRepository problemRepository;
    private final JwtTokenService jwtTokenService;

    public ProblemEntity create(CreateProblemDto createProblemDto) {
        UserEntity userEntity = this.jwtTokenService.userEntity();
        System.out.println("인증된 유저 정보 : " + userEntity.getEmail() + " / " + userEntity.getName());
        return ProblemEntity.builder()
            .id(99999L)
            .title("JWT Token Test...")
            .build();

        // TODO: 저장하는 로직 나머지 부분 구현하기
        // return problemRepository.save(
        //     ProblemEntity.builder()
        //         .title(createProblemDto.getTitle())
        //         .timeLimitSecond(createProblemDto.getTimeLimitSecond())
        //         .memoryLimitMb(createProblemDto.getMemoryLimitMb())

        //         .problemExplain(createProblemDto.getProblemExplain())
        //         .inputExplain(createProblemDto.getInputExplain())
        //         .outputExplain(createProblemDto.getOutputExplain())
        //         .note(createProblemDto.getNote())

        //         // .user(null) // TODO : 유저 추가시키기
        //         .build()
        // );
    }
}
