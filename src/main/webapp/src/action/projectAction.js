import axios from 'axios';
import { errors, projects } from './actionType';

export const createProject = (project, history) => {
  return async (dispatch, getState) => {
    try {
      const res = await axios.post('/api/v1/project', project);
      history.push('/dashboard');
    } catch (e) {
      dispatch({ type: errors.GET_ERROR, payload: e.response.data });
    }
  };
};

export const updateProject = (project, history) => {
  return async (dispatch, getState) => {
    try {
      const res = await axios.put('/api/v1/project', project);
      dispatch({ type: errors.CLEAR_ERROR });
      history.push('/dashboard');
    } catch (e) {
      dispatch({ type: errors.GET_ERROR, payload: e.response.data });
    }
  };
};

export const getProjects = () => {
  return async (dispatch, getState) => {
    const result = await axios.get('/api/v1/project');
    console.log('inside getProjects');
    console.log(result);
    dispatch({ type: projects.GET_PROJECTS, payload: result.data });
  };
};

export const getProject = (projectIdentifier, history) => {
  return async (dispatch, getState) => {
    const result = await axios.get(`/api/v1/project/${projectIdentifier}`);

    dispatch({ type: projects.GET_PROJECT, payload: result.data });
  };
};

export const deleteProject = (projectIdentifier) => {
  return async (dispatch, getState) => {
    if (window.confirm('Sure you want to delete?')) {
      const result = await axios.delete(`/api/v1/project/${projectIdentifier}`);
      dispatch({
        type: projects.DELETE_PROJECT,
        payload: { projectIdentifier },
      });
    }
  };
};
