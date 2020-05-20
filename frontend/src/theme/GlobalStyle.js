import { createGlobalStyle } from 'styled-components';
import background from '../assets/images/background.jpg';

const GlobalStyles = createGlobalStyle`
  *, *::before, *::after {
    box-sizing: border-box;
  }

  html {
    /* font-size: 62.5%; */
    height: 100vh;
  }

  body {
    margin: 0;
    padding: 0;
    /* font-size: 1.6rem; */
    font-family: 'Roboto', sans-serif;

    background-image: url(${background});
    background-position: center;
    background-size: cover;
    background-repeat: no-repeat;
  }
`;

export default GlobalStyles;
