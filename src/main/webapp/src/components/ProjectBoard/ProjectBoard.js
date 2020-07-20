import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Backlog from './Backlog';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getBacklog } from '../../action/backlogAction';

class ProjectBoard extends Component {
  componentDidMount() {
    const { projectIdentifier } = this.props.match.params;
    this.props.getBacklog(projectIdentifier);
  }
  render() {
    const { projectIdentifier } = this.props.match.params;
    const { projectTasks, errors } = this.props;

    let BoardContent;

    const boardAlgorithm = (errors, project_tasks) => {
      if (project_tasks.length < 1) {
        if (errors.projectNotFound) {
          return (
            <div className="alert alert-danger text-center" role="alert">
              {errors.projectNotFound}
            </div>
          );
        } else {
          return (
            <div className="alert alert-info text-center" role="alert">
              No Project Tasks on this board
            </div>
          );
        }
      } else {
        return <Backlog projectTasks={project_tasks} />;
      }
    };

    BoardContent = boardAlgorithm(errors, projectTasks);

    return (
      <div className="container">
        <Link
          to={`/addprojecttask/${projectIdentifier}`}
          className="btn btn-primary mb-3"
        >
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        {BoardContent}
      </div>
    );
  }
}

ProjectBoard.propTypes = {
  getBacklog: PropTypes.func.isRequired,
  projectTasks: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => {
  return {
    projectTasks: state.backlog.projectTasks,
    errors: state.errors,
  };
};
export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
