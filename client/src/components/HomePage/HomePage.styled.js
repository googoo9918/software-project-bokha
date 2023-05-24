import styled from "styled-components";

export const HomeView = styled.div`
  overflow: hidden;
  position: absolute;
  width: 100%;
  // top: 70px;
`;
export const HomeContainer = styled.div`
  height: 400vh;
  transition: all 1s;
`;
export const HomeBox = styled.div`
  position: relative;
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

export const MainPageDescription = styled.div`
  position: absolute;
  top: 120px;
  left: 110px;
`;

export const ButtonContainer = styled.div`
  position: absolute;
  top: 400px;
  left: 110px;
  display: flex;
`;

export const ButtonFlipCircle = styled.div`
  width: 300px;
  height: 300px;
  border-radius: 100%;
  perspective: 1000px;
  margin: 20px;
`;

export const ButtonCircle = styled.div`
  cursor: pointer;
  width: 100%;
  height: 100%;
  border-radius: 100%;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  transform-style: preserve-3d;
  transform: rotateY(0deg);
  transition: 0.5s;

  &:hover {
    transform: rotateY(180deg);
  }
`;

export const IconImg = styled.img`
  width: 80%;
  height: 80%;
  position: absolute;
  backface-visibility: hidden;
`;
export const ButtonTitle = styled.div`
  width: 80%;
  height: 80%;
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 40px;
  font-weight: bold;
  backface-visibility: hidden;
  transform: rotateY(180deg);
`;

export const Page2Description = styled.div`
  position: absolute;
  top: 220px;
  left: 550px;
`;

export const Page2Title = styled.div`
  font-size: 80px;
  font-weight: bold;
  margin-bottom: 52px;
`;

export const Page2Content = styled.div`
  font-size: 20px;
`;

export const Page2ImageSection = styled.div`
  position: absolute;
  top: 250px;
  left: 120px;
  height: 350px;
`;

export const ShortCutButton = styled.button`
  width: 250px;
  height: 80px;
  border-radius: 50px;
  font-size: 30px;
  cursor: pointer;
  border: none;
`;

export const Page3Description = styled.div`
  position: absolute;
  top: 150px;
  left: 150px;
`;
export const Page3Title = styled.div`
  font-size: 80px;
  font-weight: bold;
  margin-bottom: 52px;
  color: white;
`;
export const Page3Content = styled.div`
  font-size: 25px;
  color: #f0f0f0;
`;
export const Page4ImageSection = styled.div`
  position: absolute;
  top: 250px;
  left: 700px;
  background-color: white;
  border-radius: 100%;
  width: 400px;
  height: 400px;
`;
