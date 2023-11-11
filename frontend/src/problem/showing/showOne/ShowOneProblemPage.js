import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { Toolbar, Typography, Grid,
    TableContainer, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material';
import axios from 'axios';
import APIConfig from "../../../APIConfig";
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';

const ShowOneProblemPage = () => {
    const [problemInfo, setProblemInfo] = useState({});
    const { problemId } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        (async () => {
            try {
              const response = await axios.get(`${APIConfig.url}/problemInfos/problems/${problemId}`);
              setProblemInfo(response.data);
            } catch (error) {
                console.error("문제 세부내용을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, [problemId]);

    // {
    //     "id": 1,
    //     "title": "Hello, World !",
    //     "timeLimitSecond": 1,
    //     "memoryLimitMb": 256,
    //     "problemExplain": "Print 'Hello, World !'.",
    //     "inputExplain": "Input is not exist.",
    //     "outputExplain": "Output 'Hello, World !'(No single quoto).",
    //     "note": "",
    //     "createrEmail": "testEmail1@gmail.com",
    //     "createrName": "testName1"
    // }


    return (
        <>
            <ProblemSubmissionNavigation/>
            <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                <Typography variant="h5" sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>{problemInfo.title}</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>



            <Grid container spacing={2}>
                <Grid item xs={3}>
                    <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>시간제한</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>{problemInfo.timeLimitSecond} 초</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>메모리제한</Typography>
                </Grid>
                <Grid item xs={3}>
                    <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>{problemInfo.memoryLimitMb} MB</Typography>
                </Grid>
            </Grid>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>



            <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>문제</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", whiteSpace: "pre-line"}}>{problemInfo.problemExplain}</Typography>


            <Toolbar sx={{paddingTop: 5}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>입력값설명</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", whiteSpace: "pre-line"}}>{problemInfo.inputExplain}</Typography>


            <Toolbar sx={{paddingTop: 5}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>출력값설명</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", whiteSpace: "pre-line"}}>{problemInfo.outputExplain}</Typography>
        </>
    );
}

export default ShowOneProblemPage;