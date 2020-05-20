import React from 'react';
import styled from 'styled-components';

import { Button, Typography, SvgIcon } from '@material-ui/core';

import { ReactComponent as LogoutIcon } from '../../assets/svg/logout.svg';

const Wrapper = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;

  width: 100%;
  height: 100vh;

  background-color: rgba(255, 255, 255, 0.9);

  transition: all 2s;

  &:hover {
    box-shadow: 0px 0px 40px 30px rgba(0, 0, 0, 0.3);
  }
`;

const ButtonWrapper = styled.div`
  margin: 50px auto 0;
`;

export const UserDetails = ({ user, setMode }) => {
  return (
    <Wrapper>
      <Typography variant='h2' color='textSecondary' align='center'>
        USER DETAILS
      </Typography>
      <Typography variant='h6' color='textSecondary' align='center'>
        NAME
      </Typography>
      <Typography variant='body1' paragraph color='secondary' align='center'>
        {user.firstName} {user.lastName}
      </Typography>
      <Typography variant='h6' color='textSecondary' align='center'>
        USERNAME
      </Typography>
      <Typography variant='body1' paragraph color='secondary' align='center'>
        {user.username}
      </Typography>

      <Typography variant='h6' color='textSecondary' align='center'>
        EMAIL
      </Typography>
      <Typography variant='body1' paragraph color='secondary' align='center'>
        {user.email}
      </Typography>

      <ButtonWrapper>
        <Button
          variant='contained'
          color='secondary'
          onClick={() => setMode(0)}
          size='large'
          startIcon={<SvgIcon viewBox='0 0 512 512'>{<LogoutIcon />}</SvgIcon>}
        >
          LOGOUT
        </Button>
      </ButtonWrapper>
    </Wrapper>
  );
};
