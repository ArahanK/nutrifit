// Login.js
import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom'; // Use Link from react-router-dom for navigation
import 'bootstrap/dist/css/bootstrap.min.css'; // Make sure to import Bootstrap CSS

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); // Initialize the useNavigate hook

  const handleLogin = (event) => {
    event.preventDefault();
    if (email === "3311@gmail.com" && password === "password") {
      localStorage.setItem('isLoggedIn', 'true'); // For demonstration purposes only
      navigate('/options'); // Navigate to the options page
    } else {
      alert('Invalid credentials'); // Placeholder for error handling
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-4">
          <div className="card">
            <h3 className="card-header text-center">Login</h3>
            <div className="card-body">
              <form onSubmit={handleLogin}>
                <div className="mb-3">
                  <label htmlFor="email" className="form-label">Email:</label>
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="password" className="form-label">Password:</label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="d-grid gap-2">
                  <button type="submit" className="btn btn-primary">Login</button>
                </div>
              </form>
            </div>
            <div className="card-footer text-center">
              Don't have an account? <Link to="/signup" className="link-primary">Sign up here</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;