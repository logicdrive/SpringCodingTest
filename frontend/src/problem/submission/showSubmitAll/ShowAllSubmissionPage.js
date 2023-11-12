import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { TableContainer, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material';
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';
import axios from 'axios';
import APIConfig from "../../../APIConfig";

const ShowAllSubmissionPage = () => {
    const { problemId } = useParams();
    const [submissions, setSubmissions] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        (async () => {
            try {

              const response = await axios.get(`${APIConfig.url}/submissionInfos/submissions?pageNumber=1&pageSize=50&type=problem&query=${problemId}`);
              setSubmissions(response.data.submissions);

            } catch (error) {
                console.error("제출 목록을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, [problemId]);

    
    const translateVerdict = (verdict) => {
        if(verdict === undefined) return ""

        if(verdict.includes("Accepted")) return "맞았습니다!!"
        if(verdict.includes("WrongAnswer")) return "틀렸습니다"
        if(verdict.includes("TimeLimitExceeded")) return "시간 초과"
        if(verdict.includes("MemoryLimitExceeded")) return "메모리 초과"

        return verdict;
    }

    return (
        <>
            <ProblemSubmissionNavigation/>

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
                        {
                            submissions.map((submission) => (
                                <TableRow key={submission.id} sx={{cursor: "pointer"}} onClick={() => {navigate(`/problem/submission/showSubmissionOne/${problemId}/${submission.id}`);}}>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.id}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.creatorName}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.problemId}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{translateVerdict(submission.verdict)}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.memoryKb} KB</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.timeMilisecond} ms</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.language}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.sentAt}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{submission.judgedAt}</TableCell>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
}

export default ShowAllSubmissionPage;