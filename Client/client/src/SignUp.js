import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function SignUp() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [age, setAge] = useState('');
  const [sex, setSex] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const navigate = useNavigate();

  const handleSignUp = (event) => {
    event.preventDefault();
    
    // Add validation for confirmPassword and password to match
    if (password !== confirmPassword) {
      console.error('Passwords do not match');
      return;
    }

    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    };

    const queryParams = new URLSearchParams({
      email,
      password,
      First_name: firstName,
      last_name: lastName,
      Age: age,
      Sex: sex,
      weight,
      height
    }).toString();

    fetch(`http://localhost:8081/users?${queryParams}`, requestOptions)
      .then(response => response.text())
      .then(result => {
        console.log(result);
        navigate('/options'); // Navigate to login page after successful sign up
      })
      .catch(error => console.log('error', error));
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <h5 className="card-header">Sign Up</h5>
            <div className="card-body">
              <form onSubmit={handleSignUp}>
                <div className="mb-3">
                  <label htmlFor="first-name" className="form-label">First Name:</label>
                  <input
                    type="text"
                    className="form-control"
                    id="first-name"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="last-name" className="form-label">Last Name:</label>
                  <input
                    type="text"
                    className="form-control"
                    id="last-name"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="age" className="form-label">Age:</label>
                  <input
                    type="number"
                    className="form-control"
                    id="age"
                    value={age}
                    onChange={(e) => setAge(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="sex" className="form-label">Sex:</label>
                  <select
                    className="form-select"
                    id="sex"
                    value={sex}
                    onChange={(e) => setSex(e.target.value)}
                    required
                  >
                    <option value="">Select...</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="unspecified">Unspecified</option>
                  </select>
                </div>
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
                <div className="mb-3">
                  <label htmlFor="confirm-password" className="form-label">Confirm Password:</label>
                  <input
                    type="password"
                    className="form-control"
                    id="confirm-password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="weight" className="form-label">Weight:</label>
                  <input
                    type="number"
                    className="form-control"
                    id="weight"
                    value={weight}
                    onChange={(e) => setWeight(e.target.value)}
                    required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="height" className="form-label">Height:</label>
                  <input
                    type="number"
                    className="form-control"
                    id="height"
                    value={height}
                    onChange={(e) => setHeight(e.target.value)}
                    required
                  />
                </div>
                <button type="submit" className="btn btn-primary">Next</button>
              </form>
            </div>
            <div className="card-footer text-muted">
              Already have an account? <a href="/login" className="link-primary">Login here</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SignUp;