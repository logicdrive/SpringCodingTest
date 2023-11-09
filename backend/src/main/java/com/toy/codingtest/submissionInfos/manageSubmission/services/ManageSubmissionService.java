package com.toy.codingtest.submissionInfos.manageSubmission.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.toy.codingtest.components.security.JwtTokenService;
import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.problemInfos.components.exceptions.ProblemNotFoundException;
import com.toy.codingtest.problemInfos.components.repositories.ProblemRepository;
import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;
import com.toy.codingtest.submissionInfos.components.exceptions.SubmissionNotFoundException;
import com.toy.codingtest.submissionInfos.components.repositories.SubmissionRepository;
import com.toy.codingtest.submissionInfos.components.repositories.TestcaseRepository;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.CreateSubmissionReqDto;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.ExecuteSubmissionReqDto;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.FindAllSubmissionReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageSubmissionService {
    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final TestcaseRepository testcaseRepository;
    private final JwtTokenService jwtTokenService;


    public SubmissionEntity create(CreateSubmissionReqDto createSubmissionReqDto) {
        ProblemEntity problemToSave = problemRepository.findById(createSubmissionReqDto.getProblemId())
            .orElseThrow(() -> new ProblemNotFoundException());
        SubmissionEntity createdSubmission = this.submissionRepository.save(
            SubmissionEntity.builder()
                .verdict("Marking")
                .language(createSubmissionReqDto.getLanguage())
                .code(createSubmissionReqDto.getCode())
                .problem(problemToSave)
                .creator(this.jwtTokenService.userEntity())
                .build()
        );

        WebClient.create("http://localhost:48081/submissions/execute")
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(
                ExecuteSubmissionReqDto.builder()
                    .submissionId(createdSubmission.getId())
                    .code(createdSubmission.getCode())
                    .language(createdSubmission.getLanguage())
                    .timeLimitSecond(createdSubmission.getTimeMilisecond())
                    .memoryKb(createdSubmission.getMemoryKb())
                    .inputs(this.testcaseRepository.findAllByProblemOrderByPriorityAsc(problemToSave).stream()
                        .map(testcase -> testcase.getInputValue())
                        .collect(Collectors.toList()))
            )
            .retrieve();

        return createdSubmission;
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
