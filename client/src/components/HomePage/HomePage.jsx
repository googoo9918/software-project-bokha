import React, { useState, useEffect } from "react";
import {
  HomeView,
  HomeBox,
  HomeContainer,
  VideoBg,
  MainPageDescription,
  welSearchButtonDiv,
} from "./HomePage.styled";
// import Navbar from "../Navbar";
import Video from "../../video/video01.mp4";
import Side from "./Side";

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
            {/* <welSearchButtonDiv>
              <button>복지 검색</button>
            </welSearchButtonDiv> */}
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
