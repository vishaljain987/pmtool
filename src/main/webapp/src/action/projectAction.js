import axios from 'axios';
import { errors, projects } from './actionType';

export const createProject = (project, history) => {
  return async (dispatch, getState) => {
    try {
      console.log(project);
      const res = await axios.post('/api/v1/project', project);
    } catch (e) {
      console.log('inside catch');
      console.log(e);
      dispatch({ type: errors.GET_ERROR, payload: e.response.data });
    }
  };
};

export const getProjects = () => {
  return async (dispatch, getState) => {
    const result = await axios.get('/api/v1/project');

    dispatch({ type: projects.GET_PROJECTS, payload: result.data });
  };
};
