import React from 'react';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; 

function Options() {
  const navigate = useNavigate();

  // Navigation functions
  const logDiet = () => navigate('/diet-log-page');
  const logExercise = () => navigate('/exercise-log-page');
  const visualizeCalories = () => navigate('/visualize-calories');
  const visualizeNutrients = () => navigate('/visualize-nutrients');
  const predictFatLoss = () => navigate('/predict-fat-loss');
  const checkCFGAlignment = () => navigate('/FoodGuideRecommendation');
  const editProfile = () => navigate('/edit'); 

  
  const buttonClass = "btn btn-outline-primary rounded-3 py-3 px-4 mb-2 w-100"; 

  return (
    <div className="container py-5">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h1>Select an Option</h1>
        <button onClick={editProfile} className="btn btn-outline-secondary">Edit Profile</button> 
      </div>
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
