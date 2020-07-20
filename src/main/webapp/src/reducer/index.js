import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import projectReducer from './projectReducer';
import backlogReducer from './backlogReducer';

const rootReducer = combineReducers({
  errors: errorReducer,
  projects: projectReducer,
  backlog: backlogReducer,
});

export default rootReducer;
