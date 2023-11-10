package com.toy.codingtest.submissionInfos.manageSubmissionOutput.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.toy.codingtest.submissionInfos.components.entities.SubmissionOutputEntity;
import com.toy.codingtest.submissionInfos.components.exceptions.SubmissionOutputNotFoundException;
import com.toy.codingtest.submissionInfos.components.repositories.SubmissionOutputRepository;
import com.toy.codingtest.submissionInfos.manageSubmissionOutput.reqDtos.FindAllSubmissionOutputReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManageSubmissionOutputService {
    private final SubmissionOutputRepository submissionOutputRepository;

    public List<SubmissionOutputEntity> findAll(FindAllSubmissionOutputReqDto findAllSubmissionOutputReqDto) {
        return this.submissionOutputRepository.findAll(
            PageRequest.of(
                findAllSubmissionOutputReqDto.getPageNumber()-1,
                findAllSubmissionOutputReqDto.getPageSize(), 
                Sort.by(Sort.Direction.ASC, "id")
            )
        ).toList();
    }

    public SubmissionOutputEntity findOne(Long id) {
        return this.submissionOutputRepository.findById(id)
            .orElseThrow(() -> new SubmissionOutputNotFoundException());
    }
}
