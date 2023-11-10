import argparse
import requests
import os
import shutil

def main() :
    parser = argparse.ArgumentParser(description="execute.py")
    parser.add_argument('-sid','--submissionId', help="submissionId", required=True)
    parser.add_argument('-lg','--language', help="language", required=True)
    parser.add_argument('-tl','--timeLimitSecond', help="timeLimitSecond", required=True)
    parser.add_argument('-ml','--memoryLimitMb', help="memoryLimitMb", required=True)
    parser.add_argument('-sdp','--submitDirPath', help="submitDirPath", required=True)
    args = vars(parser.parse_args())

    submitCodeFilePath = (args["submitDirPath"] + "code.py")
    inputsDirPath = (args["submitDirPath"] + "inputs")


    resultsToSend = []
    for filename in os.listdir(inputsDirPath) :
        result = {}

        filepath = os.path.join(inputsDirPath, filename)
        testcaseId = filename.split('_')[1].split(".")[0]
        
        result["timeMilisecond"] = 100
        result["memoryKb"] = 1000
        result["output"] = "9"
        result["testcaseId"] = testcaseId
        resultsToSend.append(result)


    url = "http://localhost:8080/submissionInfos/submissions/verdict/{}".format(args["submissionId"])
    requests.post(url, json={"results":resultsToSend}, headers={'Content-type': 'application/json'})
    shutil.rmtree(args["submitDirPath"], ignore_errors=True)

main()