import React, { Component } from 'react';
import ProjectItem from './Project/ProjectItem';
import PropTypes from 'prop-types';
import CreateProjectButton from './Project/CreateProjectButton';
import { connect } from 'react-redux';
import { getProjects } from './../action/projectAction';

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }
  render() {
    const { projects } = this.props.project;
    const projectItemList = projects.map((project) => (
      <ProjectItem key={project.id} project={project} />
    ));
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              {projectItemList}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  getProjects: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => {
  return {
    project: state.projects,
  };
};

export default connect(mapStateToProps, { getProjects })(Dashboard);
