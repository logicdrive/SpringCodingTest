package com.toy.submission.submission.executeSubmission.services;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.toy.submission.submission.executeSubmission.reqDtos.ExecuteSubmissionReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExecuteSubmissionService {
    public void execute(ExecuteSubmissionReqDto executeSubmissionReqDto) {
        System.out.println("ExecuteSubmissionService.executed() : executeSubmissionReqDto => " + executeSubmissionReqDto.toString());
        System.out.println("Inputs Size :" + executeSubmissionReqDto.getInputs().size());
        System.out.println("Current working directory: " + System.getProperty("user.dir"));


        // 주어진 정보를 기반으로 평가 스크립트인 execute.py가 이용할 수 있도록 디렉토리 경로를 구성하는 작업
        // 주어진 코드들을 격리시켜서 실행시키기 위해서 submissions/[UUID] 와 같은 디렉토리를 생성함
        String submitDirPath = ("submissions/" + UUID.randomUUID().toString() + "/");
        File submitDir = new File(submitDirPath);
        submitDir.mkdir();

        // 해당 격리된 디렉토리 내부에서 관련 코드 및 입력값들을 디렉토리에 정리해서 넣기 위한 작업
        try {
            
            FileWriter codeFileWriter = new FileWriter(submitDirPath + "code.py");
            codeFileWriter.write(executeSubmissionReqDto.getCode());
            codeFileWriter.close();


            String inputsDirPath = (submitDirPath + "/inputs");
            File inputsDir = new File(inputsDirPath);
            inputsDir.mkdir();

            List<String> inputs = executeSubmissionReqDto.getInputs();
            for(int index=0; index<inputs.size(); index++)
            {
                FileWriter inputFileWriter = new FileWriter(inputsDirPath + "/" + index + ".txt");
                inputFileWriter.write(inputs.get(index));
                inputFileWriter.close();
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
