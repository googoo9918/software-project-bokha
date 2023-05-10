import { createGlobalStyle } from 'styled-components';
import Pretendard from './Pretendard-Regular.woff2';

export default createGlobalStyle`
  @font-face {
    font-family: 'Pretendard';
    src: local('Pretendard'),
    url(${Pretendard}) format('woff2');
    font-style: normal;
  }
`;