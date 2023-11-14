import React from 'react';
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

export let labels = ['temp', 'putting', 'random', 'stuff', 'here'];
export let values = [21, 42, 133, 33, 1];

export let data = {
  labels,
  datasets: [
    {
      data: values
    },
  ],
};

export function App() {
  return (
    <div>
      <h1>Charts</h1>
      <button onClick={getChartData}>Get Chart Data</button>

      <Bar options={options} data={data} />
    </div>
  );
}

export default App;
