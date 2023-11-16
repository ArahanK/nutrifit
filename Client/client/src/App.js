import React from 'react';
import * as ReactDOM from 'react-dom';

import { getChartData } from './Data';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
//import { labels, data } from './Data';

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
      text: 'Chart.js Bar Chart',
    },
  },
};

export let labels = [];
export let values = [];


export function temp(){
  var t =  prompt("Enter a food please");
  console.log(t);
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };
  //alert()
  fetch("http://localhost:8080/SpecificFood?food="+t, requestOptions)
    .then(response => response.text())
    .then(result => console.log(result))
    .catch(error => console.log('error', error));
}

export function calories(){
  var requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };
  //alert()
  fetch("http://localhost:8080/CaloriesBurnt", requestOptions)
  .then((response) => response.json())
  .then((data) => {
    let temp = data;
    labels = temp[0];
    values = temp[1];
   // values.add(temp[1]);
    console.log(labels);
    console.log(values);
    let data2= {
      labels,
      datasets: [
        {
          data: values
        },
      ],
    };
    const container = document.getElementById('root');
const root = ReactDOM.createRoot(container);
root.render(<Bar options={options} data={data2}/>);
  })    
  .catch(function(){
    console.log("ERROR")
  });
}

export function App() {

  return (
    <div>
      <h1>Charts</h1>
      <button onClick={temp}>GET FOOD NAME</button>
      <button onClick={calories}>GET CALORIES BURNT</button>
      <button onClick={getChartData}>Get Chart Data</button>
      <div id="root"></div>
    </div>
  );
}

export default App;
