import styled from 'styled-components';

export const CardContainer = styled.div`
  width: 16.4375rem;
  height: 22.8125rem;
  border: 1px solid #7d7d7d;
  border-radius: 10px;
  position: relative;
  margin: 1rem 1rem 0 0 ;

  #program-detail-short {
    width: 13.75rem;
    height: 3.375rem;
    color: #7d7d7d;
    font-size: 15px;
    line-height: 1.125rem;
    position: absolute;
    top: 7.125rem;
    left: 50%;
    transform: translateX(-50%);
  }

  #program-detail-btn {
    width: 13.75rem;
    height: 2.5625rem;
    background-color: #CEE5D0;
    color: #000;
    border: 0;
    border-radius: 0.625rem;
    position: absolute;
    bottom: 1.3125rem;
    left: 50%;
    transform: translateX(-50%);
    cursor: pointer;
  }
`;

export const CardHeader = styled.div`
  width: 13.375rem;
  height: 1.9375rem;
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 2.75rem;
  left: 50%;
  transform: translateX(-50%);

  #program-title {
    width: 11rem;
    height: 1.9375rem;
    font-size: 25px;
    font-weight: 700;
  }

  #program-star {
    width: 1.5625rem;
    height: 1.5625rem;
    position: absolute;
    bottom: 0;
    right: 0;
    cursor: pointer;
  }
  
`;

export const CardInfoList = styled.div`
  width: 13.75rem;
  height: 6.5625rem;
  position: absolute;
  top: 11.0625rem;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  font-size: 14px;
`

export const CardInfo = styled.div`
  width: 13.75rem;
  height: 1.125rem;
  display: flex;

  .program-info-entry {
    width: 6.875rem;
    height: 1.125rem;
    font-weight: 600;
  }

  .program-info-content {
    width: 6.875rem;
    height: 1.125rem;
    color: #7d7d7d;
  }

`;