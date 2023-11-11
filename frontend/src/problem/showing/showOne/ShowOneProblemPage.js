import React from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { Toolbar, Typography,
    TableContainer, Table, TableHead, TableRow, TableCell, TableBody } from '@mui/material';
import axios from 'axios';
import APIConfig from "../../../APIConfig";
import ProblemSubmissionNavigation from '../../navigation/ProblemSubmissionNavigation';

const ShowOneProblemPage = () => {
    const { problemId } = useParams();
    const navigate = useNavigate();

    return (
        <>
            <ProblemSubmissionNavigation/>
        </>
    );
}

export default ShowOneProblemPage;