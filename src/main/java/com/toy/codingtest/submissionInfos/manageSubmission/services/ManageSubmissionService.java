package com.toy.codingtest.submissionInfos.manageSubmission.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.toy.codingtest.components.security.JwtTokenService;
import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.problemInfos.components.exceptions.ProblemNotFoundException;
import com.toy.codingtest.problemInfos.components.repositories.ProblemRepository;
import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;
import com.toy.codingtest.submissionInfos.components.exceptions.SubmissionNotFoundException;
import com.toy.codingtest.submissionInfos.components.repositories.SubmissionRepository;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.CreateSubmissionReqDto;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.FindAllSubmissionReqDto;
import com.toy.codingtest.user.components.entities.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageSubmissionService {
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final JwtTokenService jwtTokenService;


    public SubmissionEntity create(CreateSubmissionReqDto createSubmissionReqDto) {
        ProblemEntity problemToSave = problemRepository.findById(createSubmissionReqDto.getProblemId())
                                    .orElseThrow(() -> new ProblemNotFoundException());
        UserEntity createrToSave = this.jwtTokenService.userEntity();

        return this.submissionRepository.save(
            SubmissionEntity.builder()
                .verdict("Marking")
                .language(createSubmissionReqDto.getLanguage())
                .code(createSubmissionReqDto.getCode())
                .problem(problemToSave)
                .creator(createrToSave)
                .build()
        );
    }

    public List<SubmissionEntity> findAll(FindAllSubmissionReqDto findAllSubmissionReqDto) {
        return this.submissionRepository.findAll(
            PageRequest.of(
                findAllSubmissionReqDto.getPageNumber()-1,
                findAllSubmissionReqDto.getPageSize(), 
                Sort.by(Sort.Direction.ASC, "id")
            )
        ).toList();
    }

    public SubmissionEntity findOne(Long id) {
        return this.submissionRepository.findById(id)
            .orElseThrow(() -> new SubmissionNotFoundException());
    }
}
