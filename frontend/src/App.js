import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container } from "@mui/material";

import Navigation from "./navigation/Navigation"

import ShowAllProblemPage from "./problem/showing/showAll/ShowAllProblemPage";
import ShowOneProblemPage from "./problem/showing/showOne/ShowOneProblemPage";

import SubmitPage from "./problem/submission/submit/SubmitPage";
import ShowAllSubmissionPage from "./problem/submission/showSubmitAll/ShowAllSubmissionPage";
import ShowOneSubmissionPage from "./problem/submission/showSubmitOne/ShowOneSubmissionPage";

import EditProblemPage from "./problem/editing/edit/EditProblemPage";
import ShowOnlyEditableProblemPage from "./problem/editing/showOnlyEditable/ShowOnlyEditableProblemPage";

import SignUpPage from './user/signUp/SignUpPage';
import SignInPage from './user/signIn/SignInPage';


function App() {
  return (
    <Container maxWidth="lg">
      <Router>
        <Navigation/>
        <Routes>
            <Route path="/" element={<ShowAllProblemPage/>} />
            <Route path="/problem/showing/showAll" element={<ShowAllProblemPage/>} />
            <Route path="/problem/showing/showOne/:problemId" element={<ShowOneProblemPage/>} />

            <Route path="/problem/submission/submit/:problemId" element={<SubmitPage/>} />
            <Route path="/problem/submission/showSubmissionAll/:problemId" element={<ShowAllSubmissionPage/>} />
            <Route path="/problem/submission/showSubmissionOne/:submissionId" element={<ShowOneSubmissionPage/>} />

            <Route path="/problem/editing/edit/:problemId" element={<EditProblemPage/>} />
            <Route path="/problem/editing/showOnlyEditable" element={<ShowOnlyEditableProblemPage/>} />     


            <Route path="/user/signup" element={<SignUpPage/>} />
            <Route path="/user/signin" element={<SignInPage/>} />
        </Routes>
      </Router>
    </Container>
  );
}

export default App;
