import AppBar from '@mui/material/AppBar';
import { Link as RouterLink } from 'react-router-dom';
import Link from '@mui/material/Link';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';

const Navigation = () => {
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
                </Toolbar>
            </Container>
        </AppBar>
    )
}

export default Navigation;