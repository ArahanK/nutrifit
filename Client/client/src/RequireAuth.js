// RequireAuth.js
import React from 'react';
import { Navigate } from 'react-router-dom';

const RequireAuth = ({ children }) => {
  
  const isAuthenticated = localStorage.getItem('isLoggedIn') === 'true';

  if (!isAuthenticated) {
    
    return <Navigate to="/login" />;
  }

  return children;
};

export default RequireAuth;