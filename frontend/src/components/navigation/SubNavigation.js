import React from 'react';
import { Typography } from '@mui/material';

// 선택된 메뉴에 대해서 위치 및 상세한 이동에 대한 기능을 제공해주기 위해서
const SubNavigation = ({ children }) => {
    return (
        <>
            <Typography style={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", paddingTop: "15px"}}>{children}</Typography>
            <hr/>
        </>
    )
}

export default SubNavigation;