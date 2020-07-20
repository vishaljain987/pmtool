import React, { Component } from 'react';
import ProjectTask from './ProjectTasks/ProjectTask';

class Backlog extends Component {
  render() {
    const { projectTasks } = this.props;

    const todo_tasks = projectTasks
      .filter((task) => task.status === 'TO_DO')
      .map((task) => <ProjectTask key={task.id} task={{ ...task }} />);

    const inprogress_tasks = projectTasks
      .filter((task) => task.status === 'IN_PROGRESS')
      .map((task) => <ProjectTask key={task.id} task={{ ...task }} />);

    const done_tasks = projectTasks
      .filter((task) => task.status === 'DONE')
      .map((task) => <ProjectTask key={task.id} task={{ ...task }} />);
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>

            {todo_tasks}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inprogress_tasks}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {done_tasks}
          </div>
        </div>
      </div>
    );
  }
}

export default Backlog;
