import React, { useContext } from 'react';
import { Stack, Alert } from "@mui/material";
import { AlertPopupContext } from './AlertPopUpContext';


const AlertPopUpList = () => {
    const { alertPopupState } = useContext(AlertPopupContext)

    return (
        <Stack style={{position: "fixed", right: "10px", bottom: "10px"}}>
            {
                alertPopupState.alertPopUps.map((alertPopup) => (
                    <Alert key={alertPopup.id} severity={alertPopup.type} onClose={() => {}}>
                        {alertPopup.message}
                    </Alert>
                ))
            }
        </Stack>
    )
}

export default AlertPopUpList;