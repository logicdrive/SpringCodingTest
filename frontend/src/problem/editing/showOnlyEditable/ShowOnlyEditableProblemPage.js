import React, { useState, useEffect } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import { Button, Link, Toolbar, Typography,
         TableContainer, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material';
import axios from 'axios';
import APIConfig from "../../../APIConfig";

const ShowOnlyEditableProblemPage = () => {
    const [problems, setProblems] = useState([]);


    useEffect(() => {
        (async () => {
            try {
              const response = await axios.get(`${APIConfig.url}/problemInfos/problems?pageNumber=1&pageSize=50`);
              console.log('response : ', response.data.problems)
              setProblems(response.data.problems);
            } catch (error) {
                console.error("편집가능한 문제 목록을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, []);


    return (
        <>
            <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", flexGrow: 1}}>내역</Typography>
            
                <Link component={RouterLink} to="/problem/editing/create">
                    <Button variant="contained" color="inherit">
                        <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>추가</Typography>
                    </Button>
                </Link>
            </Toolbar>
            <hr/>

            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont", width: "80px"}}>ID</TableCell>
                            <TableCell align="center" padding="none" sx={{paddingBottom: 1, fontFamily: "BMDfont"}}>제목</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            problems.map((post) => (
                                <TableRow key={post.id}>
                                    <TableCell  align="center" padding="none" sx={{padding: 1}}>{post.id}</TableCell>
                                    <TableCell  align="left" padding="none" sx={{padding: 1}}>{post.title}</TableCell>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
}

export default ShowOnlyEditableProblemPage;