import React from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate, useLocation  } from 'react-router-dom';
import { Toolbar, Typography} from '@mui/material';

const ProblemSubmissionNavigation = () => {
    const { problemId } = useParams();
    const navigate = useNavigate();
    const location = useLocation();

    return (
        <>
            <Toolbar sx={{paddingTop: 2}} variant="none" disableGutters>
                <Typography sx={{color: ((location.pathname.includes("/problem/showing/showOne") ? "cornflowerblue" : "black")), fontWeight: "bolder", fontFamily: "BMDfont", cursor: "pointer"}}
                    onClick={() => {navigate(`/problem/showing/showOne/${problemId}`);}}>문제</Typography>
                
                <Typography sx={{color: ((location.pathname.includes("/problem/submission/submit") ? "cornflowerblue" : "black")), fontWeight: "bolder", fontFamily: "BMDfont", marginLeft:2, cursor: "pointer"}}
                    onClick={() => {navigate(`/problem/submission/submit/${problemId}`);}}>제출</Typography>
                
                <Typography sx={{color: ((location.pathname.includes("/problem/submission/showSubmissionAll") ? "cornflowerblue" : "black")), fontWeight: "bolder", fontFamily: "BMDfont", marginLeft:2, cursor: "pointer"}}
                    onClick={() => {navigate(`/problem/submission/showSubmissionAll/${problemId}`);}}>채점내역</Typography>
            </Toolbar>
            <hr/>
        </>
    );
}

export default ProblemSubmissionNavigation;