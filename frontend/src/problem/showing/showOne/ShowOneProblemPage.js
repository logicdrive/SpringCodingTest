import React from 'react';
import { useParams } from 'react-router-dom';

const ShowOneProblemPage = () => {
    const { problemId } = useParams();
    console.log("ID :" + problemId);


    return <h3>ShowOneProblemPage Page</h3>
}

export default ShowOneProblemPage;