import { errors } from '../action/actionType';

const errorReducer = (state = {}, action) => {
  switch (action.type) {
    case errors.GET_ERROR:
      return action.payload;
    default:
      return state;
  }
};

export default errorReducer;
