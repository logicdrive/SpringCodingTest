import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';
import axios from 'axios';
import APIConfig from "../../../APIConfig";

const ShowAllSubmissionPage = () => {
    const { problemId } = useParams();
    const [submissions, setSubmissions] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        (async () => {
            try {

              const response = await axios.get(`${APIConfig.url}/submissionInfos/submissions?pageNumber=1&pageSize=50&type=problem&query=${problemId}`);
              setSubmissions(response.data.submissions);

            } catch (error) {
                console.error("제출 목록을 로드하는 도중 에러가 발생했습니다!", error);
            }
        })()
    }, [problemId]);
    console.log(submissions);

    return (
        <>
            <ProblemSubmissionNavigation/>
        </>
    );
}

export default ShowAllSubmissionPage;