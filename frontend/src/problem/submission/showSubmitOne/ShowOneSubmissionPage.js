import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Toolbar, Typography, TextField, 
         TableContainer, Table, TableHead, TableRow, TableCell, TableBody,
         Dialog, DialogTitle, DialogContent } from '@mui/material';
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';
import axios from 'axios';
import APIConfig from "../../../APIConfig";

const ShowOneSubmissionPage = () => {
    const { submissionId } = useParams();
    const [submissionInfo, setSubmissionInfo] = useState([]);
    const [submissionOutputs, setSubmissionOutputs] = useState([]);
    const [isSubmissionOutputDialogOpend, setIsSubmissionOutputDialogOpend] = useState(false);
    const [submissionOutputInfo, setSubmissionOutputInfo] = useState({});


    useEffect(() => {
        (async () => {
            try {

                const submissonInfoRes = await axios.get(`${APIConfig.url}/submissionInfos/submissions/${submissionId}`);
                setSubmissionInfo(submissonInfoRes.data);

                const submissionOutputRes = await axios.get(`${APIConfig.url}/submissionInfos/submissionOutputs?pageNumber=1&pageSize=50&submissionId=${submissionId}`);
                setSubmissionOutputs(submissionOutputRes.data.submissionOutputs)
            
            } catch (error) {
                console.error("제출 세부내용을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, [submissionId]);


    const translateVerdict = (verdict) => {
        if(verdict === undefined) return ""

        if(verdict.includes("Marking")) return "채점중..."
        if(verdict.includes("Accepted")) return "맞았습니다!!"
        if(verdict.includes("WrongAnswer")) return "틀렸습니다"
        if(verdict.includes("TimeLimitExceeded")) return "시간 초과"
        if(verdict.includes("MemoryLimitExceeded")) return "메모리 초과"
        
        return verdict;
    }

    return (
        <>
            <ProblemSubmissionNavigation/>

            <Toolbar sx={{paddingTop: 0}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>요약</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>제출번호</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>아이디</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>문제번호</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>결과</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>메모리</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>실행시간</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>언어</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>제출시간</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>평가시간</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow key="0">
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.id}</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.creatorName}</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.problemId}</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{translateVerdict(submissionInfo.verdict)}</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.memoryKb} KB</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.timeMilisecond} ms</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.language}</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.sentAt}</TableCell>
                            <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionInfo.judgedAt}</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>

            <Toolbar sx={{paddingTop: 0}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", paddingTop: 5}}>제출된 코드</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <TextField
                    name="code"
                    type="code"

                    value={submissionInfo.code}

                    variant="standard"
                    margin="normal"
                    fullWidth

                    rows={10}
                    multiline

                    InputProps={{
                        readOnly: true,
                    }}
                />

            <Toolbar sx={{paddingTop: 0}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", paddingTop: 5}}>세부 채점 내역</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>ID</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>실행시간</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>메모리</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>결과</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>우선순위</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            submissionOutputs.map((submissionOutput) => (
                                <TableRow key={submissionOutput.id} sx={{cursor: "pointer"}} onClick={async () => {
                                    const response = await axios.get(`${APIConfig.url}/submissionInfos/submissionOutputs/${submissionOutput.id}`);
                                    setSubmissionOutputInfo(response.data);
                                    setIsSubmissionOutputDialogOpend(true);
                                }}>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionOutput.id}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionOutput.timeMilisecond} ms</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionOutput.memoryKb} KB</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{translateVerdict(submissionOutput.verdict)}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submissionOutput.priority}</TableCell>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </TableContainer>
            <Dialog open={isSubmissionOutputDialogOpend} onClose={()=>{setIsSubmissionOutputDialogOpend(false)}}>
                    <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>테스트케이스 입출력값 내역</DialogTitle>
                    <DialogContent>
                        <TextField
                            label="입력값"
                            name="inputValue"

                            value={submissionOutputInfo.inputValue}

                            margin="normal"
                            fullWidth

                            rows={4}
                            multiline

                            InputProps={{
                                readOnly: true,
                            }}
                        />
                        <TextField
                            label="출력값"
                            name="outputValue"

                            value={submissionOutputInfo.outputValue}

                            margin="normal"
                            fullWidth

                            rows={4}
                            multiline

                            InputProps={{
                                readOnly: true,
                            }}
                        />
                        <TextField
                            label="기대값"
                            name="expectedValue"

                            value={submissionOutputInfo.expectedValue}

                            margin="normal"
                            fullWidth

                            rows={4}
                            multiline

                            InputProps={{
                                readOnly: true,
                            }}
                        />
                    </DialogContent>
                </Dialog>
        </>
    );
}

export default ShowOneSubmissionPage;