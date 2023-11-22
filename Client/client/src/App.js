// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './Login';
import SignUp from './SignUp';
import Options from './Options';
import RequireAuth from './RequireAuth';

import PredictFatLoss from './PredictFatLoss'
import VisualizeCalories from './VisualizeCalories'
import VisualizeNutrients from './VisualizeNutrients'
import DietLogPage from './DietLogPage'
import ExerciseLogPage from './ExerciseLogPage'

window.emailGlobalVar = null;

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route
          path="/options"
          element={
            <RequireAuth>
              <Options />
            </RequireAuth>
          }
        />

        <Route path="/" element={<Navigate replace to="/login" />} />
        <Route
            path="/predict-fat-loss"
                element={
                    <RequireAuth>
                        <PredictFatLoss /> {/* Protected LogExercise Route */}
                    </RequireAuth>
            }
        />
        <Route
                  path="/visualize-calories"
                  element={
                    <RequireAuth>
                      <VisualizeCalories/> {/* Protected LogExercise Route */}
                    </RequireAuth>
                  }
                />
        <Route
                          path="/visualize-nutrients"
                          element={
                            <RequireAuth>
                              <VisualizeNutrients/> {/* Protected LogExercise Route */}
                            </RequireAuth>
                          }
                        />
        <Route
                          path="/diet-log-page"
                          element={
                            <RequireAuth>
                              <DietLogPage/> {/* Protected LogExercise Route */}
                            </RequireAuth>
                          }
                        />
        <Route
                          path="/exercise-log-page"
                          element={
                            <RequireAuth>
                              <ExerciseLogPage/> {/* Protected LogExercise Route */}
                            </RequireAuth>
                          }
                        />


        {/* ...other routes */}
      </Routes>
    </Router>
  );
}

export default App;