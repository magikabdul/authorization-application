import React, { useState } from 'react';
import styled from 'styled-components';

import { Button, Link, TextField, Typography } from '@material-ui/core';

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
  console.log(setMode);
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
            />
          </InputBox>
          <InputBox>
            <TextField
              color='primary'
              label='Last Name'
              size='small'
              fullWidth
              required
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
          />
        </InputBox>
        <InputBox>
          <TextField
            color='primary'
            label='Email'
            size='small'
            fullWidth
            required
          />
        </InputBox>
        <InputBox>
          <TextField
            color='primary'
            label='Password'
            size='small'
            fullWidth
            required
          />
        </InputBox>
        <InputBox>
          <TextField
            color='primary'
            label='Retype Password'
            size='small'
            fullWidth
            required
          />
        </InputBox>
        <ButtonBox>
          <Button variant='contained' color='secondary'>
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
