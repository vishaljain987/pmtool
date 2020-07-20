import axios from 'axios';
import { errors } from './actionType';

export const createProjectTask = (projectIdentifier, projectTask, history) => {
  return async (dispatch, getState) => {
    try {
      await axios.post(`/api/v1/backlog/${projectIdentifier}`, projectTask);
      dispatch({ type: errors.CLEAR_ERROR });
      history.push(`/projectboard/${projectIdentifier}`);
    } catch (e) {
      dispatch({ type: errors.GET_ERROR, payload: e.response.data });
    }
  };
};
