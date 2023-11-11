import React, { useState, useContext } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { TextField, Button, MenuItem } from '@mui/material';
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';
import { JwtTokenContext } from "../../../components/jwtToken/JwtTokenContext";
import { AlertPopupContext } from "../../../components/alertPopUp/AlertPopUpContext"

const SubmitPage = () => {
    const { problemId } = useParams();
    const { jwtTokenState } = useContext(JwtTokenContext);
    const { addAlertPopUp } = useContext(AlertPopupContext);
    const navigate = useNavigate();


    const [submissionInfo, setSubmissionInfo] = useState({
        code: "",
        language: ""
    });

    const languages = [
        { value:"python", label:"python" }
    ]


    const handleSubmit = async (event) => {
        event.preventDefault();

        try {

            console.log(submissionInfo)
            // await axios.post(`${APIConfig.url}/users`, signUpData);
            // addAlertPopUp("회원가입이 성공적으로 완료되었습니다.", "success");
            // navigate("/user/signin");

        } catch (error) {
            addAlertPopUp("제출 에러가 발생했습니다!", "error");
            console.error("제출 도중 에러가 발생했습니다!", error);
        }
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setSubmissionInfo((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };


    return (
        <>
            <ProblemSubmissionNavigation/>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="언어"
                    name="language"

                    value={submissionInfo.language}
                    onChange={handleInputChange}

                    variant="standard"
                    margin="normal"
                    fullWidth
                    required

                    defaultValue="python"
                    select
                >
                    {
                        languages.map((language) => (
                            <MenuItem key={language.value} value={language.value}>
                                {language.label}
                            </MenuItem>
                        ))
                    }
                </TextField>

                <TextField
                    label="코드"
                    name="code"
                    type="code"

                    value={submissionInfo.code}
                    onChange={handleInputChange}

                    variant="standard"
                    margin="normal"
                    fullWidth
                    required

                    rows={10}
                    multiline
                />

                <hr style={{border: "solid 0.1px lightgray", opacity: "0.25"}}/>
                <Button type="submit" variant="contained" color="primary" fullWidth sx={{marginTop: 1, fontFamily: "BMDfont"}}>
                    제출
                </Button>
            </form>
        </>
    );
}

export default SubmitPage;