import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { deleteProjectTask } from '../../../action/backlogAction';

class ProjectTask extends Component {
  constructor(props) {
    super(props);
    this.handleDeleteClick = this.handleDeleteClick.bind(this);
  }

  handleDeleteClick() {
    const { projectIdentifier, projectSequence } = this.props.task;
    if (window.confirm('Are you sure you want to delete?')) {
      this.props.deleteProjectTask(projectIdentifier, projectSequence);
    }
  }

  render() {
    const { task } = this.props;
    let priorityString;
    let priorityClass;

    if (task.priority === 1) {
      priorityClass = 'bg-danger text-light';
      priorityString = 'HIGH';
    }

    if (task.priority === 2) {
      priorityClass = 'bg-warning text-light';
      priorityString = 'MEDIUM';
    }

    if (task.priority === 3) {
      priorityClass = 'bg-info text-light';
      priorityString = 'LOW';
    }
    return (
      <div className="card mb-1 bg-light">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {task.projectSequence} -- Priority: {priorityString}
        </div>
        <div className="card-body bg-light">
          <h5 className="card-title">{task.summary}</h5>
          <p className="card-text text-truncate ">{task.acceptanceCriteria}</p>
          <Link
            to={`/updateprojecttask/${task.projectIdentifier}/${task.projectSequence}`}
            className="btn btn-primary"
          >
            View / Update
          </Link>

          <button
            onClick={this.handleDeleteClick}
            className="btn btn-danger ml-4"
          >
            Delete
          </button>
        </div>
      </div>
    );
  }
}

ProjectTask.propTypes = {
  deleteProjectTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);
