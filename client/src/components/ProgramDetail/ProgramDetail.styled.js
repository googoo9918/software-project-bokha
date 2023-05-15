import styled from "styled-components";

export const ProgramDetailContainer = styled.div`
  width: 60.25rem;
  height: max-content;
  position: absolute;
  top: 12rem;
  left: 24rem;
`;

export const DetailHeader = styled.div`
  width: 60.25rem;
  height: 16.9375rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  #program-title {
    font-size: 40px;
    font-weight: 600;
  }
`

export const DetailInfoBox = styled.div`
  width: 60.25rem;
  height: 12rem;
  background-color: #e6e6e6;
  border-radius: 15px;
  position: relative;
  font-size: 25px;

  #program-info__inquiries-line {
    margin-right: 200px;
  }

  #program-info__content {
    width: 53.625rem;
    height: 6.5rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-wrap: wrap;
  }
`

export const DetailInfoLine = styled.div`
  width: max-content;
  height: 2.125rem;
  display: flex;
  margin-right: 64px;

  .program-info__content-title {
    font-weight: 600;
    margin-right: 20px;
  }
  
  #program-info__inquiries {
    margin-right: 45px;
  }
`;

export const DetailContent = styled.div`
  width: 60.25rem;
  height: max-content;
  font-size: 25px;
  margin-top: 40px;
`