import React, { useState, useEffect } from 'react';
import { Toolbar, Typography,
         TableContainer, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material';
import axios from 'axios';
import APIConfig from "../../../APIConfig";

const ShowAllProblemPage = () => {
    const [problems, setProblems] = useState([]);


    useEffect(() => {
        (async () => {
            try {
              const response = await axios.get(`${APIConfig.url}/problemInfos/problems?pageNumber=1&pageSize=50`);
              setProblems(response.data.problems);
            } catch (error) {
                console.error("문제 목록을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, []);


    return (
        <>
            <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>내역</Typography>
            </Toolbar>
            <hr/>

            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont", width: "80px"}}>ID</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>제목</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont", width: "160px"}}>작성자</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            problems.map((problem) => (
                                <TableRow key={problem.id}>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{problem.id}</TableCell>
                                    <TableCell  align="left" padding="none" sx={{padding: 1}}>{problem.title}</TableCell>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{problem.createrName}</TableCell>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
}

export default ShowAllProblemPage;