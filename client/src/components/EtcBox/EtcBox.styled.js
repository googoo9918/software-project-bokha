import styled from 'styled-components';
import { 
  CategoryBoxContainer,
  CategoryTitle,
  CategoryContent
} from '../CategoryBox/CategoryBox.styled';
import Select from '@mui/material/Select';

export const EtcBoxContainer = styled(CategoryBoxContainer)`
  height: 13rem;
`;

export const EtcTitle = styled(CategoryTitle)``;

export const EtcContent = styled(CategoryContent)`
  display: flex;
  flex-wrap: wrap;
  position: relative;

  .etc-label {
    width: 4rem;
    height: 2rem;
    font-weight: 700;
    font-size: 16px;
  }

`;

export const RegionSelect = styled(Select)`
  width: 8.5rem;
  height: 2.2rem;
`;

export const AgeBox = styled.div`
  width: 9rem;
  height: 1.625rem;
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 1rem;
  left: 1.5rem;

  #etc-age__box {
    width: 6rem;
    height: 1.4rem;
    display: flex;
    font-size: 15px;
    border: 1px solid #7D7D7D;
    border-radius: 0.625rem;
    padding: 5px;
  }

  .etc-age__text {
    width: 0.9375rem;
    height: 1.5625rem;
  }

  #etc-age__input {
    width: 3rem;
    height: 1.5625rem;
    border: none;
    text-align: right;
    outline: none;
    color: #057598;
  }
`;

export const RegionBox = styled.div`
  width: 22rem;
  height: 2.6rem;
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 1rem;
  left: 14.5rem;
`;

export const KeywordBox = styled.div`
  width: 35rem;
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 4rem;
  left: 50%;
  transform: translateX(-50%);

  #etc-keyword__input {
    width: 30rem;
    outline: none;
    border: 1px solid #7D7D7D;
    border-radius: 0.625rem;
    padding-left: 10px;
  }
`;

export const ButtonBox = styled.div`
  width: 23rem;
  height: 2.6rem;
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 7rem;
  left: 50%;
  transform: translateX(-50%);

  .etc-footer__btn {
    width: 11rem;
    height: 2.6rem;
    font-weight: 600;
    border-radius: 0.5rem;
  }

  #etc-footer__btn-reset {
    background-color: #d9d9d9;
    color: #fff;
  }

  #etc-footer__btn-search {
    background-color: rgba(5, 117, 152, 0.25);;
    color: #000;
  }
`