import styled from 'styled-components';

export const CategoryBoxContainer = styled.div`
  font-size: 18px;
  width: 46rem;
  height: 8.1rem;
  border: 1px solid #bdbdbd;
`;

export const CategoryTitle = styled.div`
  width: inherit;
  height: 2.5rem;
  background-color: #CEE5D0;
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

  .active {
    background-color: #61A167;
    color: #fff;
  }
`

export const CategoryBtn = styled.button`
  height: 1.9444rem;
  background-color: #ececec;
  border-radius: 0.8rem;
  font-weight: 600;
  color: #7d7d7d;
  padding: 0 0.6rem;
  margin: 0.5556rem 0.2222rem 0 0.3333rem;
  border: 0;
  transition: all ease-in-out 0.3s;
  cursor: pointer;
`;