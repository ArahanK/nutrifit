// VisualizeCalories.js
import React, { useState } from 'react';
import * as ReactDOM from 'react-dom/client';
import { render } from 'react-dom';
import { useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

export const options = {
  responsive: true,
  plugins: {
    legend: {
      position: 'top',
    },
    title: {
      display: true,
      text: 'Calories Intake',
    },
  },
  scales: {
    y: {
      title: {
        display: true,
        text: 'Calories Consumed'
      }
    },
    x: {
      title: {
        display: true,
        text: 'Day'
      }
    }
  }
};

export let labels = [];
export let values = [];
export let values2 = [];
function VisualizeCalories() {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const navigate = useNavigate();

  const handleBack = () => {
    navigate('/options');
  };

  // Add more logic for form submission and validation as needed
  const handleSubmit = async (event) => {
    event.preventDefault();
    // Logic to handle visualization of calorie intake
    var end = new Date(endDate).toISOString().split('T')[0];
    var start = new Date(startDate).toISOString().split('T')[0];
    
    const email = window.emailGlobalVar; 

    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    };
    try {
      await fetch("http://localhost:8081/pull-diet-log?email="+email, requestOptions)
      .then(response => response.json())
      .then(result => {
        var d = 1;
        result.forEach(foodLog => {
        var tempDate = new Date(foodLog.date).toISOString().split('T')[0];
        console.log("Start Date: "+ startDate);
        console.log("Temp Date: "+ tempDate);
        console.log("End Date: "+ endDate);
          if(start <= tempDate && tempDate <= end){
            labels.push(d);
            values.push(foodLog.calories);
            d++;
          }
        })
      })
      .catch(error => console.log('error', error));
      await fetch("http://localhost:3005/CaloriesBurnt?email="+email+"&start="+startDate+"&end="+endDate, requestOptions)
        .then(response => response.json())
        .then(result => {
          values2 = result;
        })
        .catch(error => console.log('error', error));

      console.log("Values2: "+ values2);
      let data= {
        labels,
          datasets: [
            {
              label: "Calories Consumed",
              data: values,
              backgroundColor:'green',
            },
            {
              label: "Calories Burnt",
              data: values2,
              backgroundColor:'red',
            },
          ],
      };  
      /*
      const container = document.getElementById('root');
      const root = ReactDOM.createRoot(container);
      root.render(<Bar options={options} data={data}/>);
      */
      const chartContainer = document.createElement('div');
      chartContainer.id = 'chart-container';
      document.getElementById('root').appendChild(chartContainer);
      render(<Bar options={options} data={data} />, chartContainer);
    } catch (error){
      console.log("Oops")
    }
};

  return (
    <div id="root" className="container mt-5">
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