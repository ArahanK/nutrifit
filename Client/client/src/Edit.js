import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function EditProfile() {
  const [userData, setUserData] = useState({
    userId: 0,
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    age: 0,
    sex: '',
    weight: 0,
    height: 0
  });

  const Navigate = useNavigate();
  const handleBack = () => {
    Navigate('/options')
  }

  useEffect(() => {
    const requestOptions = {
      method: 'GET',
      redirect: 'follow'
    };

    fetch("http://localhost:8081/getUserEmail?email=test5@nutrifit.com", requestOptions)
      .then(response => response.json())
      .then(data => {
        setUserData(data);
      })
      .catch(error => console.log('error', error));
  }, []);

  const handleChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value });
  };

  return (
    <div className="container mt-5">
      <h2>Edit Profile</h2>
      <button className="btn btn-outline-primary" onClick={handleBack}>
            Back
          </button>
      <form>
        <div className="mb-3">
        <label htmlFor="email" className="form-label">Email</label>
          <input type="email" className="form-control" id="email" name="email" value={userData.email} onChange={handleChange} readOnly /></div>

        <div className="mb-3">
          <label htmlFor="password" className="form-label">Password</label>
          <input type="password" className="form-control" id="password" name="password" value={userData.password} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label htmlFor="firstName" className="form-label">First Name</label>
          <input type="text" className="form-control" id="firstName" name="firstName" value={userData.firstName} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label htmlFor="lastName" className="form-label">Last Name</label>
          <input type="text" className="form-control" id="lastName" name="lastName" value={userData.lastName} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label htmlFor="age" className="form-label">Age</label>
          <input type="number" className="form-control" id="age" name="age" value={userData.age} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label htmlFor="sex" className="form-label">Sex</label>
          <input type="text" className="form-control" id="sex" name="sex" value={userData.sex} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label htmlFor="weight" className="form-label">Weight</label>
          <input type="number" className="form-control" id="weight" name="weight" value={userData.weight} onChange={handleChange} />
        </div>

        <div className="mb-3">
          <label htmlFor="height" className="form-label">Height</label>
          <input type="number" className="form-control" id="height" name="height" value={userData.height} onChange={handleChange} />
        </div>

        <button type="submit" className="btn btn-primary">Update Profile</button>
      </form>
    </div>
  );
}

export default EditProfile;
