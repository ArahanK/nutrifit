import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function FoodGuideRecommendation() {
  const [age, setAge] = useState('');
  const [gender, setGender] = useState('');
  const [recommendations, setRecommendations] = useState(null);
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  const foodGuideData = {
    '2-3': { male: { veggies: 4, grains: 3, milk: 2, meat: 1 }, female: { veggies: 4, grains: 3, milk: 2, meat: 1 } },
    '4-8': { male: { veggies: 5, grains: 4, milk: 2, meat: 1 }, female: { veggies: 5, grains: 4, milk: 2, meat: 1 } },
    '9-13': { male: { veggies: 6, grains: 6, milk: 3, meat: 1 }, female: { veggies: 6, grains: 6, milk: 3, meat: 1 } },
    '14-18': { male: { veggies: 8, grains: 7, milk: 3, meat: 3 }, female: { veggies: 7, grains: 6, milk: 3, meat: 2 } },
    '19-50': { male: { veggies: 8, grains: 8, milk: 2, meat: 3 }, female: { veggies: 7, grains: 6, milk: 2, meat: 2 } },
    '51+': { male: { veggies: 7, grains: 7, milk: 3, meat: 3 }, female: { veggies: 7, grains: 6, milk: 3, meat: 3 } },
  };

  const findAgeRange = (age) => {
    if (age >= 2 && age <= 3) return '2-3';
    if (age >= 4 && age <= 8) return '4-8';
    if (age >= 9 && age <= 13) return '9-13';
    if (age >= 14 && age <= 18) return '14-18';
    if (age >= 19 && age <= 50) return '19-50';
    if (age > 50) return '51+';
    return null;
  };

  const handleAgeChange = (event) => {
    setAge(event.target.value);
  };

  const handleGenderChange = (event) => {
    setGender(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setErrorMessage('');

    if (!age || !gender) {
      setErrorMessage('Please enter both age and gender.');
      return;
    }

    const ageRange = findAgeRange(age);
    const userRecommendations = ageRange ? foodGuideData[ageRange][gender] : null;
    setRecommendations(userRecommendations);
  };

  return (
    <div className="container">
      <button onClick={() => navigate('/options')} className="btn btn-outline-primary mb-3">
        Back
      </button>
      <h2>Canada Health Food Guide Recommendations</h2>
      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="ageInput" className="form-label">Age</label>
          <input type="number" className="form-control" id="ageInput" value={age} onChange={handleAgeChange} />
        </div>
        <div className="mb-3">
          <label htmlFor="genderSelect" className="form-label">Gender</label>
          <select className="form-select" id="genderSelect" value={gender} onChange={handleGenderChange}>
            <option value="">Select Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>
        <button type="submit" className="btn btn-primary">Get Recommendations</button>
      </form>

      {recommendations && (
        <div className="recommendations">
          <h3>Your Food Guide Recommendations:</h3>
          <p>Veggies and Fruit: {recommendations.veggies} servings</p>
          <p>Grain Products: {recommendations.grains} servings</p>
          <p>Milk and Alternatives: {recommendations.milk} servings</p>
          <p>Meat and Alternatives: {recommendations.meat} servings</p>
        </div>
      )}
    </div>
  );
}

export default FoodGuideRecommendation;