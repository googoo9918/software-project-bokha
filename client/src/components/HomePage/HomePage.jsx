import React, { useState, useEffect } from "react";
import {
  HomeView,
  HomeBox,
  HomeContainer,
  VideoBg,
  MainPageDescription,
  ButtonContainer,
  ButtonCircle,
  ButtonFlipCircle,
  IconImg,
  ButtonTitle,
  Page2Description,
  Page2Title,
  Page2Content,
  Page2ImageSection,
  ShortCutButton,
  Page3Description,
  Page3Title,
  Page3Content,
  Page4ImageSection,
} from "./HomePage.styled";
// import Navbar from "../Navbar";
import { Link } from "react-scroll";
import Video from "../../video/video01.mp4";
import Side from "./Side";
import SearchIcon from "../../assets/검색 아이콘.png";
import LocationIcon from "../../assets/위치 아이콘.png";
import VoiceIcon from "../../assets/음성 아이콘.png";
import Page2Image01 from "../../assets/Page2Image01.png";
import Page2Image02 from "../../assets/Page2Image02.png";
import Page4Image01 from "../../assets/Page4Image01.png";
import { useNavigate } from "react-router-dom";

export default function HomePage() {
  const navigate = useNavigate();
  return (
    <>
      <HomeView>
        <HomeContainer>
          <HomeBox id="1" style={{ backgroundColor: "#CEE5D0" }}>
            <MainPageDescription>
              <span style={{ fontSize: "60px", fontWeight: "bold" }}>
                복지 정보!
              </span>
              <br />
              <span
                style={{ color: "#fff", fontSize: "50px", fontWeight: "bold" }}
              >
                찾기 힘드시죠?
              </span>
              <br />
              <span
                style={{ color: "#fff", fontSize: "60px", fontWeight: "bold" }}
              >
                저희가 대신
              </span>
              <span style={{ fontSize: "60px", fontWeight: "bold" }}>
                {" "}
                찾아드리겠습니다!
              </span>
            </MainPageDescription>
            <ButtonContainer>
              <Link to="2" spy={true} smooth={true}>
                <ButtonFlipCircle>
                  <ButtonCircle
                    className="flip"
                    style={{ backgroundColor: "#FFBF86" }}
                  >
                    <IconImg src={SearchIcon} />
                    <ButtonTitle className="back">복지 검색</ButtonTitle>
                  </ButtonCircle>
                </ButtonFlipCircle>
              </Link>
              <Link to="3" spy={true} smooth={true}>
                <ButtonFlipCircle>
                  <ButtonCircle
                    className="flip"
                    style={{ backgroundColor: "#F3F0D7" }}
                  >
                    <IconImg src={LocationIcon} />
                    <ButtonTitle className="back">복지 지도</ButtonTitle>
                  </ButtonCircle>
                </ButtonFlipCircle>
              </Link>
              <Link to="4" spy={true} smooth={true}>
                <ButtonFlipCircle>
                  <ButtonCircle
                    className="flip"
                    style={{ backgroundColor: "#FED2AA" }}
                  >
                    <IconImg src={VoiceIcon} />
                    <ButtonTitle className="back">음성 검색</ButtonTitle>
                  </ButtonCircle>
                </ButtonFlipCircle>
              </Link>
            </ButtonContainer>
          </HomeBox>
          <HomeBox id="2" style={{ backgroundColor: "#F3F0D7" }}>
            <Page2Description>
              <Page2Title>
                <span>
                  복지 제도 <span style={{ color: "#FFBF86" }}>검색</span>을
                </span>
                <br />
                <span
                  style={{
                    color: "#FFBF86",
                    display: "flex",
                    justifyContent: "flex-end",
                  }}
                >
                  간편하게!
                </span>
              </Page2Title>
              <Page2Content>
                <span
                  style={{
                    color: "#7D7D7D",
                    display: "flex",
                    justifyContent: "flex-end",
                  }}
                >
                  복Z세대에서는 가구상황, 관심분야,
                </span>
                <span
                  style={{
                    color: "#7D7D7D",
                    display: "flex",
                    justifyContent: "flex-end",
                  }}
                >
                  나이, 지역, 키워드에 따른 검색을 제공합니다.
                </span>
              </Page2Content>
            </Page2Description>
            <Page2ImageSection>
              <img src={Page2Image01} style={{ width: "300px" }}></img>
              <img
                src={Page2Image02}
                style={{
                  width: "300px",
                  position: "absolute",
                  top: "140px",
                  left: "180px",
                }}
              />
            </Page2ImageSection>
            <div
              className="shortcut"
              style={{ position: "absolute", top: "700px", left: "830px" }}
            >
              <ShortCutButton
                style={{ backgroundColor: "#FFBF86", color: "white" }}
                onClick={() => {
                  navigate(`/search`);
                }}
              >
                바로가기
              </ShortCutButton>
            </div>
          </HomeBox>
          <HomeBox id="3">
            <VideoBg autoPlay loop muted src={Video} type="video/mp4" />
            <Page3Description>
              <Page3Title>
                <span>
                  <span style={{ color: "#CEE5D0" }}>복지 지도</span>를 통해
                </span>
                <br />
                <span>위치 정보를 한눈에!</span>
              </Page3Title>
              <Page3Content>
                <span>복Z세대는 복지 지도를 통해</span>
                <span
                  style={{
                    display: "flex",
                    justifyContent: "flex-start",
                    margin: "5px 0px",
                  }}
                >
                  {" "}
                  복지시설, 공공기관의 위치 정보를
                </span>
                <span> 한눈에 보기 쉽게 보여줍니다.</span>
              </Page3Content>
            </Page3Description>
            <div
              className="shortcut"
              style={{ position: "absolute", top: "700px", left: "830px" }}
            >
              <ShortCutButton
                style={{ backgroundColor: "#FED2AA" }}
                onClick={() => {
                  navigate(`/map`);
                }}
              >
                바로가기
              </ShortCutButton>
            </div>
          </HomeBox>
          <HomeBox id="4" style={{ backgroundColor: "#CEE5D0" }}>
            <Page3Description>
              <Page3Title>
                <span>
                  <span style={{ color: "#000" }}>음성 인식</span>을 통한
                </span>
                <br />
                <span>복지 제도 검색!</span>
              </Page3Title>
              <Page3Content style={{ color: "#7D7D7D" }}>
                <span>복Z세대는 음성 인식을 통한</span>
                <span
                  style={{
                    display: "flex",
                    justifyContent: "flex-start",
                    margin: "5px 0px",
                  }}
                >
                  복지 제도 검색을 제공하여,
                </span>
                <span>불필요한 키보드 및 마우스 사용을 줄여줍니다.</span>
              </Page3Content>
            </Page3Description>
            <Page4ImageSection>
              <img
                src={Page4Image01}
                style={{
                  width: "300px",
                  position: "absolute",
                  top: "45px",
                  left: "23px",
                }}
              ></img>
            </Page4ImageSection>
            <div
              className="shortcut"
              style={{ position: "absolute", top: "700px", left: "150px" }}
            >
              <ShortCutButton
                style={{ backgroundColor: "#F3F0D7" }}
                onClick={() => {
                  navigate(`/search`);
                }}
              >
                바로가기
              </ShortCutButton>
            </div>
          </HomeBox>
        </HomeContainer>
      </HomeView>
    </>
  );
}
