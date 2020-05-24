import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { connect } from 'react-redux';

import { fetchLogin } from '../../actions';
import { Button, Link, TextField, Typography } from '@material-ui/core';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';

const Wrapper = styled.form`
  position: absolute;
  top: 35%;
  left: calc(50% - 20%);

  width: 40%;

  background-color: rgba(255, 255, 255, 0.9);

  border-radius: 5px;

  transition: all 2s;

  &:hover {
    box-shadow: 0px 0px 40px 30px rgba(0, 0, 0, 0.3);

    background-color: rgba(255, 255, 255, 0.9);

    h1 {
      color: gray;
    }
  }
`;

const Container = styled.div`
  width: 90%;
  margin: 0 auto;
  padding: 0 10px 20px;
`;

const Title = styled.h1`
  color: gray;
  text-transform: uppercase;
  text-align: center;

  padding: 10px 0 0;

  transition: all 2s;
`;

const InputWrapper = styled.div`
  padding: 10px 0;
`;

const ButtonWrapper = styled.div`
  display: flex;
  justify-content: flex-end;
  padding-top: 30px;
  padding-bottom: 20px;
`;

function LoginForm({ token, authenticate, setMode, authorizationError }) {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');

  const handleClick = (e) => {
    e.preventDefault();
    authenticate(login, password);
  };

  if (token) {
    setMode(2);
  }

  return (
    <Wrapper>
      <Title>login</Title>
      <Container>
        <InputWrapper>
          <TextField
            color='primary'
            variant='filled'
            label='login'
            fullWidth
            type='text'
            required
            value={login}
            onChange={(e) => setLogin(e.target.value)}
          />
        </InputWrapper>
        <InputWrapper>
          <TextField
            color='primary'
            variant='filled'
            label='password'
            fullWidth
            type='password'
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </InputWrapper>
        {authorizationError && (
          <Typography align='center' color='error'>
            {authorizationError}
          </Typography>
        )}
        <ButtonWrapper>
          <Button
            color='secondary'
            variant='contained'
            startIcon={<ExitToAppIcon />}
            size='large'
            type='submit'
            onClick={handleClick}
          >
            login
          </Button>
        </ButtonWrapper>
        <Typography variant='body2' align='center' color='textSecondary'>
          Don't have an account <Link onClick={() => setMode(1)}>register</Link>
        </Typography>
      </Container>
    </Wrapper>
  );
}

const mapStateToProps = ({ token = null, authorizationError = null }) => ({
  token,
  authorizationError,
});

const mapDispatchToProps = (dispatch) => ({
  authenticate: (login, password) => dispatch(fetchLogin(login, password)),
});

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);
