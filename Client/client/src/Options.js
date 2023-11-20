// Options.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Ensure you have this import to include Bootstrap styles

function Options() {
  const navigate = useNavigate();

  // Navigation functions
  const logDiet = () => navigate('/diet-log-page');
  const logExercise = () => navigate('/exercise-log-page');
  const visualizeCalories = () => navigate('/visualize-calories');
  const visualizeNutrients = () => navigate('/visualize-nutrients');
  const predictFatLoss = () => navigate('/predict-fat-loss');
  const checkCFGAlignment = () => navigate('/check-cfg');

  // Custom styling for buttons
  const buttonClass = "btn btn-outline-primary rounded-3 py-3 px-4 mb-2 w-100"; // Increased y-padding for taller buttons

  return (
    <div className="container py-5">
      <h1 className="text-center mb-4">Select an Option</h1>
      <div className="row row-cols-2 g-2">
        <div className="col d-flex justify-content-center">
          <button onClick={logDiet} className={buttonClass}>
            🍽️ Log Diet
          </button>
        </div>
        <div className="col d-flex justify-content-center">
          <button onClick={logExercise} className={buttonClass}>
            🏃 Log Exercise
          </button>
        </div>
        <div className="col d-flex justify-content-center">
          <button onClick={visualizeCalories} className={buttonClass}>
            📊 Visualize Calorie Intake and Exercise
          </button>
        </div>
        <div className="col d-flex justify-content-center">
          <button onClick={visualizeNutrients} className={buttonClass}>
            💊 Visualize Nutrients
          </button>
        </div>
        <div className="col d-flex justify-content-center">
          <button onClick={predictFatLoss} className={buttonClass}>
            ⚖️ Predict Fat Loss
          </button>
        </div>
        <div className="col d-flex justify-content-center">
          <button onClick={checkCFGAlignment} className={buttonClass}>
            ✔️ Check Alignment with CFG
          </button>
        </div>
      </div>
    </div>
  );
}

export default Options;