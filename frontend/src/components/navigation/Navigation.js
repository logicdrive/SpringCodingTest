import React, { useContext } from 'react';
import AppBar from '@mui/material/AppBar';
import { Link as RouterLink } from 'react-router-dom';
import { Container, Toolbar, Link, Button, Typography } from '@mui/material';
import { AlertPopupContext } from "../alertPopUp/AlertPopUpContext"
import { JwtTokenContext } from "../jwtToken/JwtTokenContext";

const Navigation = () => {
    const { addAlertPopUp } = useContext(AlertPopupContext);
    const { jwtTokenState, deleteTokenValue } = useContext(JwtTokenContext);

    return (
        <AppBar position="static">
            <Container maxWidth="lg">
                <Toolbar disableGutters>
                    <Link component={RouterLink} to="/" variant="h5" underline="none" sx={{color: "white", fontWeight: "bolder", fontFamily: "BMDfont"}}>
                        스프링&gt;코딩테스트
                    </Link>

                    <Link component={RouterLink} to="/problem/showing/showAll">
                        <Button>
                            <Typography sx={{color: "white", fontWeight: "bolder", fontFamily: "BMDfont", paddingLeft:2}}>문제풀기</Typography>
                        </Button>
                    </Link>
                    <Link component={RouterLink} to="/problem/editing/showOnlyEditable" sx={{flexGrow:1}}>
                        <Button>
                            <Typography sx={{color: "white", fontWeight: "bolder", fontFamily: "BMDfont"}}>문제관리</Typography>
                        </Button>
                    </Link>
   
            { 
                (jwtTokenState.jwtToken == null) ? (
                    <>
                    <Link component={RouterLink} to="/user/signin">
                        <Button>
                            <Typography sx={{color: "white", fontWeight: "bolder", fontFamily: "BMDfont"}}>로그인</Typography>
                        </Button>
                    </Link>
                    <Link component={RouterLink} to="/user/signup">
                        <Button>
                            <Typography sx={{color: "white", fontWeight: "bolder", fontFamily: "BMDfont"}}>회원가입</Typography>
                        </Button>
                    </Link>
                    </>
                ) : (                            
                    <>
                    <Typography sx={{color: "lightblue", fontWeight: "bolder", fontFamily: "BMDfont"}}>
                        {jwtTokenState.jwtToken.name} 님 환영합니다!
                    </Typography>
                    <Button onClick={() => {
                        deleteTokenValue();
                        addAlertPopUp("로그아웃이 정상적으로 완료되었습니다.", "success");
                    }}>
                            <Typography sx={{color: "white", fontWeight: "bolder", fontFamily: "BMDfont"}}>로그아웃</Typography>
                    </Button>
                    </>
                )
            }
                </Toolbar>
            </Container>
        </AppBar>
    );
}

export default Navigation;