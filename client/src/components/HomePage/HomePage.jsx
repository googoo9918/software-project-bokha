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
} from "./HomePage.styled";
// import Navbar from "../Navbar";
import { Link } from "react-scroll";
import Video from "../../video/video01.mp4";
import Side from "./Side";
import SearchIcon from "../../assets/검색 아이콘.png";
import LocationIcon from "../../assets/위치 아이콘.png";
import VoiceIcon from "../../assets/음성 아이콘.png";

export default function HomePage() {
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
          <HomeBox id="2" style={{ backgroundColor: "#F3F0D7" }}></HomeBox>
          <HomeBox id="3">
            <VideoBg autoPlay loop muted src={Video} type="video/mp4" />
          </HomeBox>
          <HomeBox id="4" style={{ backgroundColor: "#CEE5D0" }}></HomeBox>
        </HomeContainer>
      </HomeView>
    </>
  );
}
