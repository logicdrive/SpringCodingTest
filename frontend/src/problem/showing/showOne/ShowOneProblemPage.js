import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { Toolbar, Typography, Grid } from '@mui/material';
import axios from 'axios';
import APIConfig from "../../../APIConfig";
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';

const ShowOneProblemPage = () => {
    const { problemId } = useParams();
    const navigate = useNavigate();

    const [problemInfo, setProblemInfo] = useState({});
    const [examples, setExamples] = useState([]);


    useEffect(() => {
        (async () => {
            try {
                const problemInfoRes = await axios.get(`${APIConfig.url}/problemInfos/problems/${problemId}`);
                setProblemInfo(problemInfoRes.data);

                const examplesRes = await axios.get(`${APIConfig.url}/problemInfos/examples?problemId=${problemId}`);
                setExamples(examplesRes.data.examples)
            } catch (error) {
                console.error("문제 세부내용을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, [problemId]);

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
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.75"}}/>



            <Toolbar sx={{paddingTop: 5}} variant="none" disableGutters>
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
        

            <hr style={{border: "solid 0.1px lightgray", opacity: "0.75"}}/>
            {
                examples.map((example, index) => {
                    return (
                        <>
                            <Toolbar sx={{paddingTop: 5}} variant="none" disableGutters>
                                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>예제 {index+1}</Typography>
                            </Toolbar>
                            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>

                            <Typography sx={{color: "black", fontFamily: "BMDfont"}}>입력값</Typography>
                            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", whiteSpace: "pre-line"}}>{example.inputValue}</Typography>
                            
                            <Typography sx={{color: "black", fontFamily: "BMDfont", marginTop:3}}>출력값</Typography>
                            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", whiteSpace: "pre-line"}}>{example.outputValue}</Typography>
                        </>
                    )
                })
            }
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.75"}}/>


            <Toolbar sx={{paddingTop: 5}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>노트</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", whiteSpace: "pre-line"}}>{problemInfo.note}</Typography>


            <Toolbar sx={{paddingTop: 5}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>정보</Typography>
            </Toolbar>
            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", whiteSpace: "pre-line", marginBottom: 10}}>제작자: {problemInfo.createrName}({problemInfo.createrEmail})</Typography>
        </>
    );
}

export default ShowOneProblemPage;