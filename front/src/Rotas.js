import HomePage from './pages/HomePage'

import React from "react";
import { Routes, Route } from 'react-router-dom';

export default () => {
    return(
    <Routes>
         <Route path="/" element={<HomePage />} />
    </Routes>
    );
}