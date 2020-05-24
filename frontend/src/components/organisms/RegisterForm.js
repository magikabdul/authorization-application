import React, { useState } from 'react';
import styled from 'styled-components';

import { connect } from 'react-redux';

import {
  Button,
  Link,
  SvgIcon,
  TextField,
  Typography,
} from '@material-ui/core';

import { ReactComponent as RegisterIcon } from '../../assets/svg/sign-up.svg';

const Wrapper = styled.form`
  position: absolute;
  right: 0;

  display: flex;
  flex-direction: column;
  justify-content: center;

  width: 40%;
  height: 100vh;

  background-color: rgba(255, 255, 255, 0.9);

  transition: all 2s;

  &:hover {
    box-shadow: 0px 0px 40px 30px rgba(0, 0, 0, 0.3);
  }
`;

const Container = styled.div`
  width: 90%;
  margin: auto;
`;

const NameBox = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
`;

const InputBox = styled.div`
  width: 100%;
  padding: 10px;
`;

const ButtonBox = styled.div`
  display: flex;
  justify-content: flex-end;

  padding: 30px 10px;
`;

function RegisterForm({ setMode }) {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [userName, setUserName] = useState('');
  const [email, setEmail] = useState('');
  const [password1, setPassword1] = useState('');
  const [password2, setPassword2] = useState('');
  const [error, setError] = useState(false);

  const handleSubmit = () => {
    if (password1 !== password2) {
      setError(true);
    } else {
      setError(false);
    }
  };

  return (
    <Wrapper>
      <Container>
        <Typography variant='h4' align='center' color='textSecondary'>
          REGISTER
        </Typography>
        <NameBox>
          <InputBox>
            <TextField
              color='primary'
              label='First Name'
              size='small'
              fullWidth
              required
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
            />
          </InputBox>
          <InputBox>
            <TextField
              color='primary'
              label='Last Name'
              size='small'
              fullWidth
              required
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
            />
          </InputBox>
        </NameBox>
        <InputBox>
          <TextField
            color='primary'
            label='Username'
            size='small'
            fullWidth
            required
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
          />
        </InputBox>
        <InputBox>
          <TextField
            color='primary'
            label='Email'
            size='small'
            fullWidth
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </InputBox>
        <InputBox>
          <TextField
            color='primary'
            label='Password'
            size='small'
            fullWidth
            required
            type='password'
            value={password1}
            onChange={(e) => setPassword1(e.target.value)}
            error={error}
          />
        </InputBox>
        <InputBox>
          <TextField
            color='primary'
            label='Retype Password'
            size='small'
            fullWidth
            required
            type='password'
            value={password2}
            onChange={(e) => setPassword2(e.target.value)}
            error={error}
          />
        </InputBox>
        <ButtonBox>
          <Button
            variant='contained'
            color='secondary'
            startIcon={
              <SvgIcon viewBox='0 0 100 100'>{<RegisterIcon />}</SvgIcon>
            }
            onClick={handleSubmit}
          >
            REGISTER
          </Button>
        </ButtonBox>
        <Typography variant='body2' align='center' color='textSecondary'>
          Already have an account <Link onClick={() => setMode(0)}>login</Link>
        </Typography>
      </Container>
    </Wrapper>
  );
}

export default connect()(RegisterForm);
