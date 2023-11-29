import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';


function DietLogPage() {
  const email = localStorage.getItem('email');
  const [logs, setLogs] = useState([]);
  const [newLog, setNewLog] = useState({
    date: '',
    mealType: '',
    ingredients: [{ name: '', quantity: '' }]
  });

  const [searchDate, setSearchDate] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    fetchLogs();
  }, []);

  const goBack = () => {
    navigate(-1);
  };

  const handleInputChange = (e, index, field) => {
    if (index === -1) {
      setNewLog({ ...newLog, [field]: e.target.value });
    } else {
      const newIngredients = [...newLog.ingredients];
      newIngredients[index] = { ...newIngredients[index], [field]: e.target.value };
      setNewLog({ ...newLog, ingredients: newIngredients });
    }
  };

  const addIngredientField = () => {
    setNewLog({
      ...newLog,
      ingredients: [...newLog.ingredients, { name: '', quantity: '' }]
    });
  };

  const removeIngredientField = (index) => {
    const newIngredients = newLog.ingredients.filter((_, i) => i !== index);
    setNewLog({ ...newLog, ingredients: newIngredients });
  };

  const addLog = () => {
    
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
  
    t
    const fetchPromises = newLog.ingredients.slice(0, 5).map(ingredient => {
      if (ingredient.name && ingredient.quantity) {

        
        const url = `http://localhost:8081/get-nutrition?email=${encodeURIComponent(email)}&food=${encodeURIComponent(ingredient.name)}&quantity=${encodeURIComponent(ingredient.quantity)}&mealType=${encodeURIComponent(newLog.mealType)}&date=${encodeURIComponent(newLog.date)}`;
        
        return fetch(url)
          .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            console.log(response.json);
            return response.json();
          })
          .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
            return null; 
          });
      }
      return Promise.resolve(null);
    });
  
    
    Promise.all(fetchPromises).then(results => {
      
      const validResults = results.filter(result => result);
  
      
      var raw = JSON.stringify(validResults);
  
      var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
      };
  
      // Post the food logs to the server
      fetch("http://localhost:8081/user/AddEntry", requestOptions)
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok for POST request');
          }
          return response.text();
        })
        .then(result => {
          
          
          setNewLog({
            date: '',
            mealType: '',
            ingredients: [{ name: '', quantity: '' }]
          });
          fetchLogs();
        })
        .catch(error => {
          console.error('There was a problem with the POST operation:', error);
        });
    });
  };
  

  const deleteLog = (index) => {
    
  };

  const fetchLogs = () => {
    const requestOptions = {
      method: 'GET',
      redirect: 'follow'
    };

    fetch(`http://localhost:8081/pull-diet-log?email=${encodeURIComponent(email)}`, requestOptions)
      .then(response => response.json())
      .then(data => {
        setLogs(data);
        
      })
      .catch(error => {
        console.error('error', error);
      });
  };

  const getFilteredLogs = () => {
    return searchDate ? logs.filter(log => log.date === searchDate) : logs;
  };

  const ingredientsList = ["Apple", "Banana", "Strawberries", "Watermelon", "Avocado", "Spinach (raw)", "Cheese (Cheddar)", "WW Bread", "Cottage Cheese", "Salad Greens", "Almonds", "Oatmeal", "Egg (Boiled)", "Black Beans", "Broccoli (Steamed)", "Greek Yogurt", "Quinoa", "Chicken Breast", "Salmon (Baked)", "Sweet Potato", "Brown Rice"];


  const renderLogCards = () => {
    return logs.map((log, index) => (
      <div key={index} className="card mb-3">
        <div className="card-body">
          <h5 className="card-title">Log Date: {log.date}</h5>
          <h6 className="card-subtitle mb-2 text-muted">Meal Type: {log.mealType}</h6>
          <h6 className="card-subtitle mb-2 text-muted">Food Name: {log.foodName}</h6>
          <p className="card-text">Servings: {log.servings}g</p>
          <p className="card-text">Calories: {log.calories}</p>
          <p className="card-text">Protein: {log.protein}g</p>
          <p className="card-text">Carbs: {log.carbs}g</p>
          <p className="card-text">Fat: {log.fat}g</p>
        </div>
      </div>
    ));
  };



  

  return (
    <div style={{ display: 'flex', flexDirection: 'row', alignItems: 'flex-start', justifyContent: 'center', height: '100vh', padding: '20px' }}>
      <button onClick={goBack} style={{ position: 'absolute', top: '20px', left: '20px', padding: '10px 20px', backgroundColor: 'lightgray', borderRadius: '20px' }}>Back</button>
      
      {/* Log Entry Column */}
      <div style={{ width: '40%', marginRight: '20px', padding: '20px', borderRadius: '10px', boxShadow: '0px 0px 10px #ccc', textAlign: 'center' }}>
        <h2 style={{ color: '#333' }}>Add Diet Log</h2>
        <form onSubmit={(e) => e.preventDefault()} style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
          <input
            type="date"
            name="date"
            value={newLog.date}
            onChange={(e) => handleInputChange(e, -1, 'date')}
            style={{ padding: '10px', marginBottom: '10px', borderRadius: '10px', border: '1px solid #ccc', width: '100%' }}
          />
          <select
            name="mealType"
            value={newLog.mealType}
            onChange={(e) => handleInputChange(e, -1, 'mealType')}
            style={{ padding: '10px', marginBottom: '10px', borderRadius: '10px', border: '1px solid #ccc', width: '100%' }}
          >
            <option value="">Select Meal Type</option>
            <option value="Breakfast">Breakfast</option>
            <option value="Lunch">Lunch</option>
            <option value="Dinner">Dinner</option>
            <option value="Snack">Snack</option>
          </select>
          {/* Ingredients List */}
          {newLog.ingredients.map((ingredient, index) => (
            <div key={index} style={{ display: 'flex', gap: '5px', alignItems: 'center' }}>
              <select
                name="name"
                value={ingredient.name}
                onChange={(e) => handleInputChange(e, index, 'name')}
                style={{ padding: '5px', flexGrow: 1, borderRadius: '10px', border: '1px solid #ccc' }}
              >
                <option value="">Select Ingredient</option>
                {ingredientsList.map((ingredientName) => (
                  <option key={ingredientName} value={ingredientName}>{ingredientName}</option>
                ))}
              </select>
              <input
                type="number"
                name="quantity"
                value={ingredient.quantity}
                placeholder="Quantity (grams)"
                onChange={(e) => handleInputChange(e, index, 'quantity')}
                min="1"
                style={{ padding: '5px', flexGrow: 1, borderRadius: '10px', border: '1px solid #ccc' }}
              />
              <button onClick={() => removeIngredientField(index)} style={{ backgroundColor: 'red', color: 'white', borderRadius: '20px', padding: '5px 10px' }}>X</button>
            </div>
          ))}
          <button onClick={addIngredientField} style={{ backgroundColor: 'blue', color: 'white', borderRadius: '20px', padding: '10px 20px', border: 'none' }}>Add Ingredient</button>
          <button onClick={addLog} style={{ backgroundColor: 'green', color: 'white', borderRadius: '20px', padding: '10px 20px', border: 'none' }}>Log Diet</button>
        
          
          
          
        </form>
      </div>
      <div className="col-md-6" style={{ 
        maxHeight: '80vh', 
        overflowY: 'auto', 
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2)', // Adds shadow
        marginTop: '16px', // Add some margin at the top if needed
        borderRadius: '4px', // Adds rounded corners
        backgroundColor: '#fff' // Ensure the background is white
      }}>
          {/* Render log cards in a scrollable container */}
          {renderLogCards()}
        </div>

        
    </div>
  );
}

export default DietLogPage;