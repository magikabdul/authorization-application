import React, { useState } from 'react';
import GlobalStyles from '../theme/GlobalStyle';

import { ThemeProvider } from '@material-ui/styles';

import theme from '../theme/theme';
import { LoginForm, RegisterForm } from '../components/organisms';

function Root() {
  const [mode, setMode] = useState(1);

  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyles />
        {mode === 0 && <LoginForm setMode={setMode} />}
        {mode === 1 && <RegisterForm setMode={setMode} />}
      </ThemeProvider>
    </>
  );
}

export default Root;
