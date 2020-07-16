import { errors } from '../action/actionType';

const errorReducer = (state = {}, action) => {
  switch (action.type) {
    case errors.GET_ERROR:
      return action.payload;
    case errors.CLEAR_ERROR:
      return {};
    default:
      return state;
  }
};

export default errorReducer;
