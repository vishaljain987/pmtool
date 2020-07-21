import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import AddProject from './components/Project/AddProject';
import UpdateProject from './components/Project/UpdateProject';
import ProjectBoard from './components/ProjectBoard/ProjectBoard';
import AddProjectTask from './components/ProjectBoard/ProjectTasks/AddProjectTask';
import UpdateProjectTask from './components/ProjectBoard/ProjectTasks/UpdateProjectTask';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Route exact path="/dashboard" component={Dashboard} />
        <Route exact path="/addProject" component={AddProject} />
        <Route
          exact
          path="/projectboard/:projectIdentifier"
          component={ProjectBoard}
        />
        <Route
          exact
          path="/updateprojecttask/:projectIdentifier/:projectSequence"
          component={UpdateProjectTask}
        />
        <Route
          exact
          path="/addprojecttask/:projectIdentifier"
          component={AddProjectTask}
        />
        <Route
          exact
          path="/updateProject/:projectIdentifier"
          component={UpdateProject}
        />
      </Router>
    </div>
  );
}

export default App;
