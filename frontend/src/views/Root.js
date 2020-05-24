import React, { useState } from 'react';
import GlobalStyles from '../theme/GlobalStyle';
import { Provider } from 'react-redux';

import { ThemeProvider } from '@material-ui/styles';

import theme from '../theme/theme';
import store from '../store';
import { LoginForm, RegisterForm, UserDetails } from '../components/organisms';

function Root() {
  const [mode, setMode] = useState(0);

  const user = {
    firstName: 'Liwia',
    lastName: 'Cholewa',
    email: 'liwia.cholewa@gmail.com',
    username: 'liffcia',
  };

  return (
    <>
      <Provider store={store}>
        <ThemeProvider theme={theme}>
          <GlobalStyles />
          {mode === 0 && <LoginForm setMode={setMode} />}
          {mode === 1 && <RegisterForm setMode={setMode} />}
          {mode === 2 && <UserDetails user={user} setMode={setMode} />}
        </ThemeProvider>
      </Provider>
    </>
  );
}

export default Root;
