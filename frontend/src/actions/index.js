export const LOGIN = {
  REQUEST: 'LOGIN_REQUEST',
  SUCCESS: 'LOGIN_SUCCESS',
  FAILURE: 'LOGIN_FAILURE',
  ERROR: 'LOGIN_ERROR',
};

export const fetchLogin = (login, password) => (dispatch) => {
  dispatch({ type: LOGIN.REQUEST });
  let error = false;

  return fetch('https://localhost:8443/login', {
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
