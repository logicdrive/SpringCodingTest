import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { Toolbar, Typography, Grid, TextField, Button, 
    Dialog, DialogTitle, DialogContent, DialogActions} from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import axios from 'axios';
import APIConfig from "../../../APIConfig";
import { AlertPopupContext } from "../../../components/alertPopUp/AlertPopUpContext"
import { JwtTokenContext } from "../../../components/jwtToken/JwtTokenContext";

const CreateProblemPage = () => {
    const { addAlertPopUp } = useContext(AlertPopupContext);
    const { jwtTokenState } = useContext(JwtTokenContext);
    const navigate = useNavigate();


    const [problemInfo, setProblemInfo] = useState({
        title: "",

        timeLimitSecond: 1,
        memoryLimitMb: 256,
    
        problemExplain: "",
        inputExplain: "",
        outputExplain: "",
        note: "",

        examples: [],
        testcases: []
    });

    const [exampleInfo, setExampleInfo] = useState({
        id:0,
        input: "",
        output: ""
    })

    const [testcaseInfo, setTestcaseInfo] = useState({
        id:0,
        input: "",
        output: ""
    })


    const [dialogStatus, setDialogStatus] = useState({
        isExampleCreationDialogOpend:false,
        isTestcaseCreationDialogOpend:false
    });


    const exampleColumns = [
        { field: "input", headerName: "입력값", width:565},
        { field: "output", headerName: "출력값", width:565}
    ]

    const testcaseColumns = [
        { field: "input", headerName: "입력값", width:565},
        { field: "output", headerName: "출력값", width:565}
    ]


    const handleSubmit = async (event) => {
        event.preventDefault();

        try {

            const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
            const problemInfoToSend = {
                title: problemInfo.title,

                timeLimitSecond: problemInfo.timeLimitSecond,
                memoryLimitMb: problemInfo.memoryLimitMb,
                
                problemExplain: problemInfo.problemExplain,
                inputExplain: problemInfo.inputExplain,
                outputExplain: problemInfo.outputExplain,
                note: problemInfo.note
            }
            const problemCreationRes = await axios.post(`${APIConfig.url}/problemInfos/problems`, problemInfoToSend, requestHeader);
            const problemId = problemCreationRes.data.id;

            for(let exampleIndex=0; exampleIndex<problemInfo.examples.length; exampleIndex++)
            {
                const problemExample = problemInfo.examples[exampleIndex]
                const exampleToSend = {
                    inputValue: problemExample.input,
                    outputValue: problemExample.output,
                    problemId: problemId
                }
                await axios.post(`${APIConfig.url}/problemInfos/examples`, exampleToSend, requestHeader);
            }

            for(let testcaseIndex=0; testcaseIndex<problemInfo.testcases.length; testcaseIndex++)
            {
                const testcaseExample = problemInfo.testcases[testcaseIndex]
                const testcaseToSend = {
                    inputValue: testcaseExample.input,
                    outputValue: testcaseExample.output,
                    problemId: problemId
                }
                await axios.post(`${APIConfig.url}/submissionInfos/testcases`, testcaseToSend, requestHeader);
            }

            addAlertPopUp("문제 생성이 성공적으로 완료되었습니다.", "success");
            navigate("/problem/editing/showOnlyEditable");

        } catch (error) {
            addAlertPopUp("문제 생성 도중 에러가 발생했습니다!", "error");
            console.error("문제 생성 도중 에러가 발생했습니다!", error);
        }
    };

    const handleExampleSubmit = () => {
        setProblemInfo((prevData) => ({
            ...prevData,
            examples: [...prevData.examples, {id:Date.now(), input:exampleInfo.input, output:exampleInfo.output}],
        }));

        setExampleInfo((prevData) => ({
            ...prevData,
            input: "",
            output: ""
        }));
    }

    const handleTestcaseSubmit = () => {
        setProblemInfo((prevData) => ({
            ...prevData,
            testcases: [...prevData.testcases, {id:Date.now(), input:testcaseInfo.input, output:testcaseInfo.output}],
        }));

        setTestcaseInfo((prevData) => ({
            ...prevData,
            input: "",
            output: ""
        }));
    }


    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setProblemInfo((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleExampleChange = (event) => {
        const { name, value } = event.target;
        setExampleInfo((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    }

    const handleTestcaseChange = (event) => {
        const { name, value } = event.target;
        setTestcaseInfo((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    }


    const changeDialogStatus = (dialogName, value) => {
        setDialogStatus((prevData) => ({
            ...prevData,
            [`is${dialogName}DialogOpend`]: value,
        }));
    };


    return (
        <>
            <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>문제 추가</Typography>
            </Toolbar>
            <hr/>

            <form onSubmit={handleSubmit}>            
                <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                    <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>기본 정보</Typography>
                </Toolbar>
                <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>

                <Grid container spacing={2}>
                    <Grid item xs={4}>
                        <TextField
                            label="제목"
                            name="title"

                            value={problemInfo.title}
                            onChange={handleInputChange}

                            variant="standard"
                            margin="normal"
                            fullWidth
                            required
                        />
                    </Grid>
                    <Grid item xs={4}>
                        <TextField
                            label="시간제한(초)"
                            name="timeLimitSecond"

                            value={problemInfo.timeLimitSecond}
                            onChange={handleInputChange}

                            variant="standard"
                            margin="normal"
                            fullWidth
                            required
                        />
                    </Grid>
                    <Grid item xs={4}>
                        <TextField
                            label="메모리제한(MB)"
                            name="memoryLimitMb"

                            value={problemInfo.memoryLimitMb}
                            onChange={handleInputChange}

                            variant="standard"
                            margin="normal"
                            fullWidth
                            required
                        />
                    </Grid>

                    <Grid item xs={6}>
                        <TextField
                            label="문제설명"
                            name="problemExplain"

                            value={problemInfo.problemExplain}
                            onChange={handleInputChange}

                            margin="normal"
                            fullWidth
                            required

                            rows={4}
                            multiline
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            label="노트"
                            name="note"

                            value={problemInfo.note}
                            onChange={handleInputChange}

                            margin="normal"
                            fullWidth

                            rows={4}
                            multiline
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            label="입력값설명"
                            name="inputExplain"

                            value={problemInfo.inputExplain}
                            onChange={handleInputChange}

                            margin="normal"
                            fullWidth
                            required

                            rows={4}
                            multiline
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <TextField
                            label="출력값설명"
                            name="outputExplain"

                            value={problemInfo.outputExplain}
                            onChange={handleInputChange}

                            margin="normal"
                            fullWidth
                            required

                            rows={4}
                            multiline
                        />
                    </Grid>
                </Grid>


                <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                    <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>예제 입출력값</Typography>

                    <Button sx={{fontFamily: "BMDfont"}} onClick={()=>{changeDialogStatus("ExampleCreation", true)}}>
                        <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>+ 추가</Typography>
                    </Button>
                </Toolbar>
                <Dialog open={dialogStatus.isExampleCreationDialogOpend} onClose={()=>{changeDialogStatus("ExampleCreation", false)}}>
                    <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>예제 추가</DialogTitle>
                    <DialogContent>
                        <TextField
                            label="입력값"
                            name="input"

                            value={exampleInfo.input}
                            onChange={handleExampleChange}

                            margin="normal"
                            fullWidth

                            rows={4}
                            multiline
                        />
                        <TextField
                            label="출력값"
                            name="output"

                            value={exampleInfo.output}
                            onChange={handleExampleChange}

                            margin="normal"
                            fullWidth
                            required

                            rows={4}
                            multiline
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={() => {
                            handleExampleSubmit();
                            changeDialogStatus("ExampleCreation", false);
                        }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>추가</Button>
                    </DialogActions>
                </Dialog>
                <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                <DataGrid
                    rows={problemInfo.examples}
                    columns={exampleColumns}
                    initialState={{
                        pagination: {
                            paginationModel: { page: 0, pageSize: 5 },
                        },
                    }}
                    pageSizeOptions={[5, 10]}
                />


                <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                    <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>테스트케이스 입출력값</Typography>

                    <Button sx={{fontFamily: "BMDfont"}} onClick={()=>{changeDialogStatus("TestcaseCreation", true)}}>
                        <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>+ 추가</Typography>
                    </Button>
                </Toolbar>
                <Dialog open={dialogStatus.isTestcaseCreationDialogOpend} onClose={()=>{changeDialogStatus("TestcaseCreation", false)}}>
                    <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>테스트케이스 추가</DialogTitle>
                    <DialogContent>
                        <TextField
                            label="입력값"
                            name="input"

                            value={testcaseInfo.input}
                            onChange={handleTestcaseChange}

                            margin="normal"
                            fullWidth

                            rows={4}
                            multiline
                        />
                        <TextField
                            label="출력값"
                            name="output"

                            value={testcaseInfo.output}
                            onChange={handleTestcaseChange}

                            margin="normal"
                            fullWidth
                            required

                            rows={4}
                            multiline
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={() => {
                            handleTestcaseSubmit();
                            changeDialogStatus("TestcaseCreation", false);
                        }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>추가</Button>
                    </DialogActions>
                </Dialog>
                <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                <DataGrid
                    rows={problemInfo.testcases}
                    columns={testcaseColumns}
                    initialState={{
                        pagination: {
                            paginationModel: { page: 0, pageSize: 5 },
                        },
                    }}
                    pageSizeOptions={[5, 10]}
                />


                <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                <Button type="submit" variant="contained" color="primary" fullWidth sx={{marginTop: 1, fontFamily: "BMDfont", marginBottom:1}}>
                    문제 생성
                </Button>
            </form>
        </>
    );
}

export default CreateProblemPage;