// VisualizeCalories.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { render } from 'react-dom';
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
export let labels = [];
export let values = [];
export let values2 = [];
export const options = {
  responsive: true,
  plugins: {
    legend: {
      position: 'top',
    },
    title: {
      display: true,
      text: 'Units in g',
    },
  },
  scales: {
    y: {
      title: {
        display: true,
        text: 'Nutrients consumed'
      }
    },
    x: {
      title: {
        display: true,
        text: 'Nutrients'
      }
    }
  }
};

function VisualizeNutrients() {
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const navigate = useNavigate();
  const chartContainer = document.createElement('div');
  chartContainer.id = 'chart-container';
  var count = 0;
  const handleBack = () => {
    if(count >= 1){
      document.getElementById('root').removeChild(chartContainer)
    }
    navigate('/options');
  };

  // Add more logic for form submission and validation as needed
  const handleSubmit = async (event) => {
    event.preventDefault();
    count++;
    const email = localStorage.getItem('email');
    // Logic to handle visualization of calorie intake
    var end = new Date(endDate).toISOString().split('T')[0];
    var start = new Date(startDate).toISOString().split('T')[0];
    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    };
    try{
      //Need to change this to remove hard coded string
      await fetch("http://localhost:8081/visualize-top-5?email="+email+"&startDate="+start+"&endDate="+end, requestOptions)
        .then(response => response.json())
        .then(result => {
          values = Object.values(result[0]);
          labels = Object.keys(result[0]);
          //console.log(result[0])
          console.log("labels: " + labels);
          console.log("values: " + values);
        })
        .catch(error => console.log('error', error));
        let data = {
          labels,
            datasets: [
              {
                label: "Nutrients",
                data: values,
                backgroundColor:'green',
              },
            ],
        };
        document.getElementById('root').appendChild(chartContainer);
        render(<Bar options={options} data={data} />, chartContainer);
    } catch {
      console.log("oops")
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
            <h2 className="text-center mb-4">Visualize Top 5 Nutrients</h2>
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

export default VisualizeNutrients;