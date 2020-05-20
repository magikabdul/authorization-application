import React, { useState } from 'react';
import styled from 'styled-components';

import {
  Button,
  Link,
  SvgIcon,
  TextField,
  Typography,
} from '@material-ui/core';

import RegisterIcon from '../../assets/svg/sign-up.svg';

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

export function RegisterForm({ setMode }) {
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
              <SvgIcon viewBox='0 0 100 100'>
                <path d='M95.266,63H80V47.264C80,47.129,79.483,47,79.35,47H68.105C67.973,47,68,47.129,68,47.264V63H52.188  C52.056,63,52,63.049,52,63.182v11.243C52,74.558,52.056,75,52.188,75H68v15.343C68,90.476,67.973,91,68.105,91H79.35  c0.134,0,0.65-0.524,0.65-0.657V75h15.266C95.401,75,96,74.558,96,74.425V63.182C96,63.049,95.401,63,95.266,63z'></path>
                <path d='M52.188,77C50.953,77,50,75.66,50,74.425V63.182C50,61.946,50.953,61,52.188,61H66v-9.745  c-3.917-4.638-9.15-8.166-15.157-9.99c6.024-3.312,10.108-9.683,10.108-17.002c0-10.727-8.763-19.422-19.57-19.422  c-10.807,0-19.568,8.696-19.568,19.422c0,7.32,4.082,13.691,10.109,17.002C18.876,45.225,9.4,57.154,9.4,71.261  c0,17.339,14.318,20.323,31.98,20.323c9.903,0,18.753-0.939,24.619-4.819V77H52.188z'></path>
              </SvgIcon>
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
