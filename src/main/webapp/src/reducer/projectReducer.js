import { projects } from '../action/actionType';
const initailState = {
  projects: [],
  project: {},
};
const projectReducer = (state = initailState, action) => {
  switch (action.type) {
    case projects.GET_PROJECTS:
      return { ...state, projects: action.payload };
    case projects.GET_PROJECT:
      return { ...state, project: action.payload };
    default:
      return state;
  }
};

export default projectReducer;
