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
    const { projectTasks } = this.props;
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
        <Backlog projectTasks={projectTasks} />
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
  };
};
export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
