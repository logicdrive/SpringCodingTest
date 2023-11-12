import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Toolbar, Typography, Grid, TextField, 
         TableContainer, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material';
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';
import axios from 'axios';
import APIConfig from "../../../APIConfig";

const ShowOneSubmissionPage = () => {
    const { submissionId } = useParams();
    const [submissionInfo, setSubmissionInfo] = useState([]);


    useEffect(() => {
        (async () => {
            try {

              const response = await axios.get(`${APIConfig.url}/submissionInfos/submissions/${submissionId}`);
              setSubmissionInfo(response.data);

            } catch (error) {
                console.error("제출 세부내용을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, [submissionId]);


    const translateVerdict = (verdict) => {
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
                        <TableRow>
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
                    label="코드"
                    name="code"
                    type="code"

                    value={submissionInfo.code}

                    variant="standard"
                    margin="normal"
                    fullWidth

                    rows={10}
                    multiline
                    unedit

                    InputProps={{
                        readOnly: true,
                    }}
                />

        </>
    );
}

export default ShowOneSubmissionPage;