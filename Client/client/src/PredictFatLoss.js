import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

function PredictFatLoss() {
  const email = localStorage.getItem('email');
  const [selectedDate, setSelectedDate] = useState('');
  const [weight, setWeight] = useState('');
  const [weightUnit, setWeightUnit] = useState('kg'); // 'kg' or 'lbs'
  const [predictionResult, setPredictionResult] = useState(null);
  const navigate = useNavigate();

  // Get today's date in YYYY-MM-DD format
  const today = new Date().toISOString().split('T')[0];

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

    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    };
    var second = new Date(selectedDate).toISOString().split('T')[0];
    var diff = new Date(second) - new Date(today);

    console.log(selectedDate);
    console.log(today);
    var days = diff / (1000 * 60 * 60 * 24);
    console.log(days)

    fetch("http://localhost:8081/user/weightLoss/"+email+"/"+days, requestOptions)
      .then(response => response.text())
      .then(result =>{
        if(result == -1){
          setPredictionResult("You will not lose any weight as your consuming more calories then you are burning");
        }else{
          setPredictionResult((result/3500) + "LBS");
        }
      })
      .catch(error => {
        console.log('error', error);
        setPredictionResult("Error in prediction!");
      });

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
                  style={{ maxWidth: '80px' }} // Make the dropdown smaller
                >
                  <option value="kg">kg</option>
                  <option value="lbs">lbs</option>
                </select>
              </div>
            </div>
            <button type="submit" className="btn btn-primary mt-3">
              Predict
            </button>
            {predictionResult !== null && <div className="mt-3">Prediction: {predictionResult}</div>}
          </form>
        </div>
      </div>
    </div>
  );
}

export default PredictFatLoss;