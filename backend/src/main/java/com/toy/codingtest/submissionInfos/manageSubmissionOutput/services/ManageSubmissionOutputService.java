package com.toy.codingtest.submissionInfos.manageSubmissionOutput.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionEntity;
import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;
import com.toy.codingtest.submissionInfos.components.exceptions.SubmissionNotFoundException;
import com.toy.codingtest.submissionInfos.components.exceptions.SubmissionOutputNotFoundException;
import com.toy.codingtest.submissionInfos.components.repositories.SubmissionOutputRepository;
import com.toy.codingtest.submissionInfos.components.repositories.SubmissionRepository;
import com.toy.codingtest.submissionInfos.manageSubmissionOutput.reqDtos.FindAllSubmissionOutputReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageSubmissionOutputService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionOutputRepository submissionOutputRepository;

    public List<SubmissionOutputEntity> findAll(FindAllSubmissionOutputReqDto findAllSubmissionOutputReqDto) {
        SubmissionEntity submissionToQuery = submissionRepository.findById(findAllSubmissionOutputReqDto.getSubmissionId())
                                                .orElseThrow(() -> new SubmissionNotFoundException());

        return this.submissionOutputRepository.findAllBySubmission(
            submissionToQuery,
            PageRequest.of(
                findAllSubmissionOutputReqDto.getPageNumber()-1,
                findAllSubmissionOutputReqDto.getPageSize(),
                Sort.by("priority").ascending()
            )
        );
    }

    public SubmissionOutputEntity findOne(Long id) {
        return this.submissionOutputRepository.findById(id)
            .orElseThrow(() -> new SubmissionOutputNotFoundException());
    }
}
