// VisualizeCalories.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function VisualizeCalories() {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const navigate = useNavigate();

  const handleBack = () => {
    navigate('/options');
  };

  // Add more logic for form submission and validation as needed
  const handleSubmit = (event) => {
    event.preventDefault();
    // Logic to handle visualization of calorie intake
    console.log('Start Date:', startDate, 'End Date:', endDate);
  };

  return (
    <div className="container mt-5">
      <div className="row mb-3">
        <div className="col">
          <button onClick={handleBack} className="btn btn-outline-primary">
            Back
          </button>
        </div>
      </div>
      <div className="row justify-content-center">
        <div className="col-md-6">
          <form onSubmit={handleSubmit}>
            <h2 className="text-center mb-4">Visualize Calorie Intake</h2>
            <div className="mb-3">
              <label htmlFor="startDate" className="form-label">Start Date:</label>
              <input
                type="date"
                className="form-control"
                id="startDate"
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="endDate" className="form-label">End Date:</label>
              <input
                type="date"
                className="form-control"
                id="endDate"
                value={endDate}
                onChange={(e) => setEndDate(e.target.value)}
                required
              />
            </div>
            <div className="d-grid">
              <button type="submit" className="btn btn-primary">Visualize</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default VisualizeCalories;