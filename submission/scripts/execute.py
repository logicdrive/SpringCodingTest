import argparse
import requests
import os
import shutil
import time
import psutil
import subprocess


def main() :
    args = getArgs()
    submitCodeFilePath = (args["submitDirPath"] + "code.py")
    inputsDirPath = (args["submitDirPath"] + "inputs")

    resultsToSend = getResults(submitCodeFilePath, inputsDirPath)

    url = "http://localhost:8080/submissionInfos/submissions/verdict/{}".format(args["submissionId"])
    requests.post(url, json={"results":resultsToSend}, headers={'Content-type': 'application/json'})
    shutil.rmtree(args["submitDirPath"], ignore_errors=True)


# 사용자가 입력할 파라미터 값들을 생성하고, 받기 위해서
def getArgs() -> dict :
    parser = argparse.ArgumentParser(description="execute.py")
    parser.add_argument('-sid','--submissionId', help="submissionId", required=True)
    parser.add_argument('-lg','--language', help="language", required=True)
    parser.add_argument('-tl','--timeLimitSecond', help="timeLimitSecond", required=True)
    parser.add_argument('-ml','--memoryLimitMb', help="memoryLimitMb", required=True)
    parser.add_argument('-sdp','--submitDirPath', help="submitDirPath", required=True)
    return vars(parser.parse_args())

# 최종적으로 생성된 결과값들을 만들기 위해서
def getResults(submitCodeFilePath:str, inputsDirPath:str) -> list :
    results = []

    for inputFileName in os.listdir(inputsDirPath) :
        result = {}

        inputFilePath = os.path.join(inputsDirPath, inputFileName)
        testcaseId = inputFileName.split('_')[1].split(".")[0]
    
        execution_time_milisec, maxMemoryUsage_kb, output_str = codeExecutionInfoWithDelayConsideration(
            submitCodeFilePath, inputFilePath,
            "scripts/delayTimeCheck/check.py", "scripts/delayTimeCheck/check.txt"
        )

        result["timeMilisecond"] = execution_time_milisec
        result["memoryKb"] = maxMemoryUsage_kb
        result["output"] = output_str
        result["testcaseId"] = testcaseId
        results.append(result)
    
    return results


# 특정 입력값을 특정 프로세스에 전달시키고, 관련된 실행시간, 최대메모리, 결과값을 얻기 위해서
def codeExecutionInfo(codePath:str, inputPath:str) :
    start_time_milisec = int(time.time() * 1000)
    
    commands = ["powershell.exe", f"cat {inputPath} | python3 {codePath}"] if os.name == "nt" else [f"cat {inputPath} | python3 {codePath}"]
    process = subprocess.Popen(commands, stdout=subprocess.PIPE, shell=True)
    psutilProcess = psutil.Process(process.pid)
    
    maxMemoryUsage = psutilProcess.memory_info().rss
    while process.poll() == None :
        try :
            maxMemoryUsage = max(maxMemoryUsage, psutilProcess.memory_info().rss)
        except psutil.NoSuchProcess :
            break

    end_time_milisec = int(time.time() * 1000)


    execution_time_milisec = end_time_milisec - start_time_milisec
    maxMemoryUsage_kb = int(maxMemoryUsage / 1024)

    output, error = process.communicate()
    output_str = output.decode("utf-8").replace("\r\n", "\n")
    

    return execution_time_milisec, maxMemoryUsage_kb, output_str

# 메모리 체크등에서 생기는 추가 시간을 고려해서 실제 실행시간, 메모리 최대 사용량, 결과값을 반환하기 위해서
def codeExecutionInfoWithDelayConsideration(codePath:str, inputPath:str, delayCheckCodePath:str, delayCheckCodeInputPath:str) :
    delay_time_milisec, _, _ = codeExecutionInfo(delayCheckCodePath, delayCheckCodeInputPath)
    execution_time_milisec, maxMemoryUsage_kb, output_str = codeExecutionInfo(codePath, inputPath)
    return max(0, execution_time_milisec-delay_time_milisec), maxMemoryUsage_kb, output_str


main()