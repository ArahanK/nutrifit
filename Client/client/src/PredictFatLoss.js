import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

const today = new Date().toISOString().split('T')[0];

function PredictFatLoss() {
  const email = localStorage.getItem('email');
  const [selectedDate, setSelectedDate] = useState('');
  const [weight, setWeight] = useState('');
  const [weightUnit, setWeightUnit] = useState('kg'); // 'kg' or 'lbs'
  const [predictionResult, setPredictionResult] = useState(null);
  const navigate = useNavigate();

  const handleDateChange = (event) => {
    setSelectedDate(event.target.value);
  };

  const handleWeightChange = (event) => {
    setWeight(event.target.value);
  };

  const handleWeightUnitChange = (event) => {
    setWeightUnit(event.target.value);
  };

  const handlePredict = (event) => {
    event.preventDefault();

    var requestOptionsGet = {
      method: 'GET',
      redirect: 'follow'
    };

    var requestOptionsPost = {
      method: 'POST',
      redirect: 'follow'
    };

    
    const startDate = new Date(new Date().setDate(new Date().getDate() - 21)).toISOString().split('T')[0];

    const urlAverageCalories = `http://localhost:8081/AverageCaloriesConsumed?email=${email}&startDate=${startDate}&endDate=${today}`;
    const urlActivityFactor = `http://localhost:8081/activityfactor?email=${email}`;
    const urlCalculateBMR = `http://localhost:8081/CalculateBMR?email=${email}`;

    let averageCalorieIntake, averageCalsBurnt, BMR;

    fetch(urlAverageCalories, requestOptionsGet)
      .then(response => response.text())
      .then(result => {
        averageCalorieIntake = result;
        return fetch(urlActivityFactor, requestOptionsGet);
      })
      .then(response => response.text())
      .then(result => {
        averageCalsBurnt = result;
        return fetch(urlCalculateBMR, requestOptionsGet);
      })
      .then(response => response.text())
      .then(result => {
        BMR = result;
        // Round the values to integers
        const roundedAverageCalorieIntake = Math.round(averageCalorieIntake);
        const roundedBMR = Math.round(BMR);
        const roundedAverageCalsBurnt = Math.round(averageCalsBurnt);
      
        const urlWeightLossFunction = `http://localhost:8081/WeightLossFunction?email=${email}&endDate=${selectedDate}&currentWeight=${weight}&averageCalorieIntake=${roundedAverageCalorieIntake}&BMR=${roundedBMR}&averageCalsBurnt=${roundedAverageCalsBurnt}`;
        return fetch(urlWeightLossFunction, requestOptionsPost);
      })
            
      .then(response => response.text())
.then(result => {
  // Round the result to the nearest integer before displaying
  const roundedResult = Math.round(result);
  setPredictionResult(`Estimated Weight: ${roundedResult}`);
})
      .catch(error => console.log('error', error));
  };

  const handleBack = () => {
    navigate('/options'); // Navigate back in history
  };

  return (
    <div className="container h-100 pt-4">
      <div className="row mb-4">
        <div className="col-12">
          <button className="btn btn-outline-primary" onClick={handleBack}>
            Back
          </button>
        </div>
      </div>
      <div className="row h-100 justify-content-center align-items-center">
        <div className="col-6 col-md-4">
          <form className="text-center" onSubmit={handlePredict}>
            <div className="mb-3">
              <label htmlFor="dateInput" className="form-label">
                Select a date to predict fat loss:
              </label>
              <input
                type="date"
                id="dateInput"
                className="form-control"
                value={selectedDate}
                onChange={handleDateChange}
                min={today}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="weightInput" className="form-label">Current weight:</label>
              <div className="input-group">
                <input
                  type="number"
                  id="weightInput"
                  className="form-control"
                  placeholder="Enter weight"
                  value={weight}
                  onChange={handleWeightChange}
                  required
                />
                <select
                  className="form-select"
                  value={weightUnit}
                  onChange={handleWeightUnitChange}
                  style={{ maxWidth: '80px' }}
                >
                  <option value="kg">kg</option>
                  <option value="lbs">lbs</option>
                </select>
              </div>
            </div>
            <button type="submit" className="btn btn-primary mt-3">
              Predict
            </button>
            {predictionResult !== null && <div className="mt-3">{predictionResult}</div>}
          </form>
        </div>
      </div>
    </div>
  );
}

export default PredictFatLoss;
