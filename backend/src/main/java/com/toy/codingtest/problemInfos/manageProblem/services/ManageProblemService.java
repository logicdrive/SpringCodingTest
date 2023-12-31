package com.toy.codingtest.problemInfos.manageProblem.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.toy.codingtest.components.security.JwtTokenService;
import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.problemInfos.components.exceptions.ProblemNotFoundException;
import com.toy.codingtest.problemInfos.components.repositories.ProblemRepository;
import com.toy.codingtest.problemInfos.manageProblem.reqDtos.CreateProblemReqDto;
import com.toy.codingtest.problemInfos.manageProblem.reqDtos.FindAllProblemReqDto;
import com.toy.codingtest.user.components.entities.UserEntity;
import com.toy.codingtest.user.components.exceptions.UserNotFoundException;
import com.toy.codingtest.user.components.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageProblemService {
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    public ProblemEntity create(CreateProblemReqDto createProblemReqDto) {
        return this.problemRepository.save(
            ProblemEntity.builder()
                .title(createProblemReqDto.getTitle())
                .timeLimitSecond(createProblemReqDto.getTimeLimitSecond())
                .memoryLimitMb(createProblemReqDto.getMemoryLimitMb())

                .problemExplain(createProblemReqDto.getProblemExplain())
                .inputExplain(createProblemReqDto.getInputExplain())
                .outputExplain(createProblemReqDto.getOutputExplain())
                .note(createProblemReqDto.getNote())

                .creator(this.jwtTokenService.userEntity())
                .build()
        );
    }

    public List<ProblemEntity> findAll(FindAllProblemReqDto findAllProblemReqDto) {
        if(findAllProblemReqDto.getType().equals("creater")) {

            UserEntity userToQuery = this.userRepository.findByName(findAllProblemReqDto.getQuery())
                .orElseThrow(() -> new UserNotFoundException());

            return this.problemRepository.findAllByCreator(
                userToQuery,
                PageRequest.of(
                    findAllProblemReqDto.getPageNumber()-1,
                    findAllProblemReqDto.getPageSize(), 
                    Sort.by(Sort.Direction.ASC, "id")
                )
            );

        } else {

            return this.problemRepository.findAll(
                PageRequest.of(
                    findAllProblemReqDto.getPageNumber()-1,
                    findAllProblemReqDto.getPageSize(), 
                    Sort.by(Sort.Direction.ASC, "id")
                )
            ).toList();

        }
    }

    public ProblemEntity findOne(Long id) {
        return this.problemRepository.findById(id)
            .orElseThrow(() -> new ProblemNotFoundException());
    }
}
