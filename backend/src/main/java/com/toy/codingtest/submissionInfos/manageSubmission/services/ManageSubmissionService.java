package com.toy.codingtest.submissionInfos.manageSubmission.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.toy.codingtest.components.security.JwtTokenService;
import com.toy.codingtest.problemInfos.components.entities.ProblemEntity;
import com.toy.codingtest.problemInfos.components.exceptions.ProblemNotFoundException;
import com.toy.codingtest.problemInfos.components.repositories.ProblemRepository;
import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;
import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;
import com.toy.codingtest.submissionInfos.components.entities.TestcaseEntity;
import com.toy.codingtest.submissionInfos.components.exceptions.SubmissionNotFoundException;
import com.toy.codingtest.submissionInfos.components.repositories.SubmissionOutputRepository;
import com.toy.codingtest.submissionInfos.components.repositories.SubmissionRepository;
import com.toy.codingtest.submissionInfos.components.repositories.TestcaseRepository;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.CreateSubmissionReqDto;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.FindAllSubmissionReqDto;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.ResultDto;
import com.toy.codingtest.submissionInfos.manageSubmission.reqDtos.VerdictSubmissionReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageSubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionOutputRepository submissionOutputRepository;
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

        
        // 제출 코드 실행 서버에 제출된 코드 및 입력값들을 전달시키기 위해서
        Map<String, Object> request = new HashMap<>();
        request.put("submissionId", createdSubmission.getId());
        request.put("code", createdSubmission.getCode());
        request.put("language", createdSubmission.getLanguage());
        request.put("timeLimitSecond", problemToSave.getTimeLimitSecond());
        request.put("memoryLimitMb", problemToSave.getMemoryLimitMb());
        request.put("inputCases", this.testcaseRepository.findAllByProblemOrderByPriorityAsc(problemToSave).stream()
            .map(testcase -> {
                Map<String, Object> inputCase = new HashMap<>();
                inputCase.put("testCaseId", testcase.getId());
                inputCase.put("input", testcase.getInputValue());
                return inputCase;
            })
            .collect(Collectors.toList()));
        
        WebClient.create("http://localhost:48081/submissions/execute")
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(request))
            .retrieve()
            .bodyToMono(String.class)
            .subscribe();


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

    public SubmissionEntity verdict(Long submissionId, VerdictSubmissionReqDto verdictSubmissionReqDto) {
        SubmissionEntity submissionToUpdate = this.submissionRepository.getReferenceById(submissionId);
        ProblemEntity problemToCheck = submissionToUpdate.getProblem();


        String updatedVerdict = "Accepted";
        int maximeMilisecondToSave = 0;
        int maxMemoryKbToSave = 0;
        for(ResultDto result : verdictSubmissionReqDto.getResults()) {
            String outputVerdict = "Accepted";

            if(result.getTimeMilisecond() > maximeMilisecondToSave) maximeMilisecondToSave = result.getTimeMilisecond();
            if(result.getTimeMilisecond() > problemToCheck.getTimeLimitSecond()*1000) {
                outputVerdict = "TimeLimitExceeded";
                updatedVerdict = String.format("TimeLimitExceeded(testcaseId:%d)", result.getTestcaseId());
            }

            if(result.getMemoryKb() > maxMemoryKbToSave) maxMemoryKbToSave = result.getMemoryKb();
            if(result.getMemoryKb() > problemToCheck.getMemoryLimitMb()*1024) {
                outputVerdict = "MemoryLimitExceeded";
                updatedVerdict = String.format("MemoryLimitExceeded(testcaseId:%d)", result.getTestcaseId());
            }
            
            TestcaseEntity testcaseToCheck = this.testcaseRepository.getReferenceById(result.getTestcaseId());
            if(!result.getOutput().equals(testcaseToCheck.getOutputValue())) {
                outputVerdict = "WrongAnswer";
                updatedVerdict = String.format("WrongAnswer(testcaseId:%d)", result.getTestcaseId());
            }


            this.submissionOutputRepository.save(
                SubmissionOutputEntity.builder()
                    .timeMilisecond(result.getTimeMilisecond())
                    .memoryKb(result.getMemoryKb())
                    .verdict(outputVerdict)
                    .outputValue(result.getOutput())
                    .priority(testcaseToCheck.getPriority())
                    .testcase(testcaseToCheck)
                    .submission(submissionToUpdate)
                    .build()
            );
            

            if(!updatedVerdict.equals("Accepted")) 
                break;
        }


        submissionToUpdate.setVerdict(updatedVerdict);
        submissionToUpdate.setJudgedAt(new java.util.Date());
        submissionToUpdate.setTimeMilisecond(maximeMilisecondToSave);
        submissionToUpdate.setMemoryKb(maxMemoryKbToSave);
        this.submissionRepository.save(submissionToUpdate);
        return submissionToUpdate;
    }
}
