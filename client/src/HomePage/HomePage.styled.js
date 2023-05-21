import styled from "styled-components";

export const HomeView = styled.div`
  overflow: hidden;
  position: absolute;
  width: 100%;
  top: 70px;
`;
export const HomeContainer = styled.div`
  height: 400vh;
  transition: all 1s;
`;
export const HomeBox = styled.div`
  height: 100vh;
  float: top;
`;

export const PageButton = styled.button`
  width: 30px;
  height: 30px;
  border-radius: 100%;
`;

export const VideoBg = styled.video`
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
  background: #232a34;
`;
