import { LOGIN, REGISTER } from '../actions';

const rootReducer = (state = {}, action) => {
  switch (action.type) {
    case LOGIN.REQUEST:
      return {
        ...state,
        isLoading: true,
      };

    case LOGIN.SUCCESS:
      delete state.authorizationError;
      return {
        ...state,
        token: action.payload.token,
        isLoading: false,
      };

    case LOGIN.FAILURE:
      delete state.authorizationError;

      return {
        ...state,
        isLoading: false,
      };

    case LOGIN.ERROR:
      return {
        ...state,
        authorizationError: action.payload,
        isLoading: false,
      };

    case 'LOGOUT':
      delete state.token;
      return {
        ...state,
        isLoading: false,
      };

    case REGISTER.REQUEST:
      return {
        isLoading: true,
        ...state,
      };

    case REGISTER.SUCCESS:
      return {
        ...state,
        isLoading: false,
        registrationError: null,
      };

    case REGISTER.ERROR:
      return {
        ...state,
        registrationError: action.payload,
        isLoading: false,
      };

    case REGISTER.FAILURE:
      return {
        ...state,
        registrationError: 'Something went wrong',
        isLoading: false,
      };

    default:
      return state;
  }
};

export default rootReducer;
