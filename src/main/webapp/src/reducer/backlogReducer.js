import { backlog } from '../action/actionType';

const initialState = {
  projectTasks: [],
};

const backlogReducer = (state = initialState, action) => {
  switch (action.type) {
    case backlog.GET_BACKLOG:
      return {
        ...state,
        projectTasks: action.payload,
      };
    case backlog.DELETE_PROJECT_TASK:
      return {
        ...state,
        projectTasks: state.projectTasks.filter(
          (pt) => pt.projectSequence !== action.payload
        ),
      };
    default:
      return state;
  }
};

export default backlogReducer;
