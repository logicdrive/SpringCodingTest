import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container, Paper, Button, TextField, Typography } from '@mui/material';
import axios from 'axios';
import APIConfig from "../../APIConfig";
import { AlertPopupContext } from "../../components/alertPopUp/AlertPopUpContext"
import { JwtTokenContext } from "../../components/jwtToken/JwtTokenContext";

const SignInPage = () => {
    const { addAlertPopUp } = useContext(AlertPopupContext)
    const { jwtTokenState, registerTokenValue } = useContext(JwtTokenContext)
    const navigate = useNavigate();
    const [signInData, setSignInData] = useState({
        email: "",
        password: ""
    });


    const handleSubmit = async (event) => {
        event.preventDefault();

        try {

            const jwtToken = (await axios.post(`${APIConfig.url}/users/signIn`, signInData)).headers.authorization;
            registerTokenValue(jwtToken);
            addAlertPopUp("로그인이 성공적으로 완료되었습니다", "success");

            console.log(jwtTokenState);
            // navigate("/");

        } catch (error) {
            addAlertPopUp("로그인 도중 에러가 발생했습니다!", "error");
            console.error("로그인 도중 에러가 발생했습니다!", error);
        }
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setSignInData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    
    return (
        <>
            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", paddingTop:2}}>로그인</Typography>
            <hr/>

            <Container sx={{width:"500px", paddingTop:2}}>
                <Paper elevation={3} style={{ padding: '20px' }}>
                    <Typography variant="h5" align="center" gutterBottom sx={{color: "black", fontFamily: "BMDfont", paddingBottom:0.5}}>
                        로그인
                    </Typography>
                    <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>

                        <form onSubmit={handleSubmit}>
                            <TextField
                                label="이메일"
                                name="email"

                                value={signInData.email}
                                onChange={handleInputChange}

                                variant="standard"
                                margin="normal"
                                fullWidth
                                required
                            />
                            <TextField
                                label="비밀번호"
                                name="password"
                                type="password"

                                value={signInData.password}
                                onChange={handleInputChange}

                                variant="standard"
                                margin="normal"
                                fullWidth
                                required
                            />

                            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                            <Button type="submit" variant="contained" color="primary" fullWidth sx={{marginTop: 1, fontFamily: "BMDfont"}}>
                                로그인
                            </Button>
                        </form>
                </Paper>
            </Container>
        </>
    )
}

export default SignInPage;