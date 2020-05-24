export const LOGIN = {
  REQUEST: 'LOGIN_REQUEST',
  SUCCESS: 'LOGIN_SUCCESS',
  FAILURE: 'LOGIN_FAILURE',
  ERROR: 'LOGIN_ERROR',
};

export const REGISTER = {
  REQUEST: 'REGISTER_REQUEST',
  SUCCESS: 'REGISTER_SUCCESS',
  FAILURE: 'REGISTER_FAILURE',
  ERROR: 'REGISTER_ERROR',
};

export const fetchLogin = (login, password) => (dispatch) => {
  dispatch({ type: LOGIN.REQUEST });
  let error = false;

  return fetch('https://192.168.1.104:8443/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ login, password }),
  })
    .then((response) => {
      if (response.status !== 200) {
        error = true;
      }
      return response.json();
    })
    .then((data) => {
      if (error) {
        dispatch({
          type: LOGIN.ERROR,
          payload: data.message,
        });
      } else {
        dispatch({
          type: LOGIN.SUCCESS,
          payload: data,
        });
      }
    })
    .catch((err) => {
      dispatch({ type: LOGIN.FAILURE, payload: err });
    });
};

export const doLogout = () => (dispatch) => {
  dispatch({ type: 'LOGOUT' });
};

export const fetchRegister = (
  firstName,
  lastName,
  username,
  email,
  password
) => (dispatch) => {
  dispatch({ type: REGISTER.REQUEST });
  let error;

  return fetch('https://localhost:8443/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      firstName,
      lastName,
      username,
      email,
      password,
    }),
  })
    .then((response) => {
      if (response.status !== 201) {
        error = true;
      }
      return response.json();
    })
    .then((data) => {
      if (error) {
        dispatch({
          type: REGISTER.ERROR,
          payload: data.message,
        });
      } else {
        dispatch({
          type: REGISTER.SUCCESS,
          payload: data,
        });
      }
    })
    .catch((err) => {
      dispatch({
        type: REGISTER.FAILURE,
        payload: err,
      });
    });
};
