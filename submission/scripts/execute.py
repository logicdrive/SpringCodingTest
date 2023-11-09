import argparse
import os

def main() :
    parser = argparse.ArgumentParser(description="execute.py")
    parser.add_argument('-sid','--submissionId', help="submissionId", required=True)
    parser.add_argument('-lg','--language', help="language", required=True)
    parser.add_argument('-tl','--timeLimitSecond', help="timeLimitSecond", required=True)
    parser.add_argument('-ml','--memoryLimitMb', help="memoryLimitMb", required=True)
    parser.add_argument('-cp','--codePath', help="codePath", required=True)
    parser.add_argument('-ip','--inputsPath', help="inputsPath", required=True)
    args = vars(parser.parse_args())

    with open('logs.txt', 'w') as f:
        f.write(str(args) + " / " + os.getcwd())
        
main()