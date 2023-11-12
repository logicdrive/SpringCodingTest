package com.toy.submission.submission.executeSubmission.services;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.toy.submission.components.services.StringService;
import com.toy.submission.submission.executeSubmission.reqDtos.ExecuteSubmissionReqDto;
import com.toy.submission.submission.executeSubmission.reqDtos.InputCaseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExecuteSubmissionService {
    private final StringService stringService;

    @Value("${backend.ip}")
    private String backendServerIp;

    @Value("${backend.port}")
    private String backendServerPort;
    

    public void execute(ExecuteSubmissionReqDto executeSubmissionReqDto) {
        // submissions 와 관련된 폴더가 없을 경우 구성시키기 위해서
        String submissionsPath = "submissions/";
        File submissionDir = new File(submissionsPath);
        if(!submissionDir.exists()) submissionDir.mkdir();
        

        // 주어진 정보를 기반으로 평가 스크립트인 execute.py가 이용할 수 있도록 디렉토리 경로를 구성하는 작업
        // 주어진 코드들을 격리시켜서 실행시키기 위해서 submissions/[UUID] 와 같은 디렉토리를 생성함
        String submitDirPath = (submissionsPath + UUID.randomUUID().toString() + "/");
        String submitCodeFilePath = (submitDirPath + "code.py");
        String inputsDirPath = (submitDirPath + "inputs");
        File submitDir = new File(submitDirPath);
        submitDir.mkdir();

        // 해당 격리된 디렉토리 내부에서 관련 코드 및 입력값들을 디렉토리에 정리해서 넣기 위한 작업
        try {
            
            FileWriter codeFileWriter = new FileWriter(submitCodeFilePath);
            codeFileWriter.write(executeSubmissionReqDto.getCode());
            codeFileWriter.close();

            File inputsDir = new File(inputsDirPath);
            inputsDir.mkdir();

            List<InputCaseDto> inputCases = executeSubmissionReqDto.getInputCases();
            for(int index=0; index<inputCases.size(); index++)
            {
                InputCaseDto inputCase = inputCases.get(index);
                String inputFilePath = (
                    inputsDirPath + "/" + stringService.padLeft(String.valueOf(index), 10, '0') +
                    "_" + inputCase.getTestCaseId() + ".txt"
                );
                FileWriter inputFileWriter = new FileWriter(inputFilePath);
                inputFileWriter.write(inputCase.getInput());
                inputFileWriter.close();
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        // 평가 스크립트인 execute.py를 실행시켜서 주어진 Submission에 대한 처리를 하도록 만들기 위해서
        try {

            Runtime.getRuntime().exec(String.join(" ", Arrays.asList(
                "python3", "scripts/execute.py",
                    "--submissionId", executeSubmissionReqDto.getSubmissionId().toString(),
                    "--language", executeSubmissionReqDto.getLanguage(),
                    "--timeLimitSecond", String.valueOf(executeSubmissionReqDto.getTimeLimitSecond()),
                    "--memoryLimitMb", String.valueOf(executeSubmissionReqDto.getMemoryLimitMb()),
                    "--submitDirPath", submitDirPath,
                    "--backendIP", this.backendServerIp,
                    "--backendPort", this.backendServerPort
            )));

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
