import { Container, Paper, Button, TextField, Typography } from '@mui/material';

const SignUpPage = () => {
    return (
        <>
            <Typography sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", paddingTop:2}}>회원가입</Typography>
            <hr/>

            <Container sx={{width:"500px", paddingTop:2}}>
                <Paper elevation={3} style={{ padding: '20px' }}>
                    <Typography variant="h5" align="center" gutterBottom sx={{color: "black", fontFamily: "BMDfont", paddingBottom:0.5}}>
                        회원가입
                    </Typography>
                    <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>

                        <form>
                            <TextField
                                label="이메일"
                                variant="standard"
                                margin="normal"
                                fullWidth
                                required
                            />
                            <TextField
                                label="비밀번호"
                                variant="standard"
                                margin="normal"
                                fullWidth
                                required
                            />
                            <TextField
                                label="닉네임"
                                variant="standard"
                                margin="normal"
                                fullWidth
                                required
                            />

                            <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                            <Button type="submit" variant="contained" color="primary" fullWidth sx={{marginTop: 1, fontFamily: "BMDfont"}}>
                                회원가입
                            </Button>
                        </form>
                </Paper>
            </Container>
        </>
    )
}

export default SignUpPage;