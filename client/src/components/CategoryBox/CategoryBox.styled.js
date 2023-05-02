import styled from 'styled-components';
import Button from '@mui/material/Button';

export const CategoryBoxContainer = styled.div`
  font-size: 18px;
  width: 38rem;
  height: 7.5em;
  border: 1px solid #bdbdbd;
`;

export const CategoryTitle = styled.div`
  width: inherit;
  height: 2.4em;
  background-color: rgba(5, 117, 152, 0.25);
  position: relative;

  #category-box__title {
    font-size: 16px;
    font-weight: 600;
    position: absolute;
    top: 50%;
    left: 1em;
    transform: translateY(-50%);
  }
`;

export const CategoryContent = styled.div`
  width: inherit;
`

export const CategoryBtn = styled(Button)`
  background-color: #ececec;
  border-radius: 15px;
  font-weight: 600;
  color: #7d7d7d;
  padding: 4px 10px;
  margin: 10px 4px;
`;