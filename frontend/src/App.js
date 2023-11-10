import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SignUpPage from './user/signUp/SignUpPage';
import SignInPage from './user/signIn/SignInPage';


function App() {
  return (
    <Router>
      <Routes>
          <Route path="/user/signup" element={<SignUpPage/>} />
          <Route path="/user/signin" element={<SignInPage/>} />
      </Routes>
    </Router>
  );
}

export default App;
