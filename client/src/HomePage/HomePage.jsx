import React, { useState, useEffect } from "react";
import { HomeView, HomeBox, HomeContainer, VideoBg } from "./HomePage.styled";
import Navbar from "../components/Navbar";
import Video from "../video/video01.mp4";

export default function HomePage() {
  //   const [prevScrollPos, setPrevScrollPos] = useState(0);
  //   const [visible, setVisible] = useState(true);
  //   const [containerTransform, setContainerTransform] = useState("");

  //   const handleScroll = () => {
  //     const currentScrollPos = window.pageYOffset;
  //     if (currentScrollPos > 100) {
  //       setVisible(false);
  //     } else {
  //       setVisible(true);
  //     }
  //     setPrevScrollPos(currentScrollPos);
  //   };

  //   // const handleButtonClick = () => {
  //   //   setContainerTransform("translateY(-100vh)");
  //   //   console.log("클릭");
  //   // };

  //   useEffect(() => {
  //     window.addEventListener("scroll", handleScroll);
  //     return () => {
  //       window.removeEventListener("scroll", handleScroll);
  //     };
  //   }, []);

  return (
    <>
      <HomeView>
        <HomeContainer>
          {/* style={{ transform: containerTransform }} */}
          <HomeBox style={{ backgroundColor: "#CEE5D0" }}></HomeBox>
          <HomeBox style={{ backgroundColor: "#F3F0D7" }}></HomeBox>
          <HomeBox>
            <VideoBg autoPlay loop muted src={Video} type="video/mp4" />
          </HomeBox>
          <HomeBox style={{ backgroundColor: "#CEE5D0" }}></HomeBox>
        </HomeContainer>
      </HomeView>
    </>
  );
}
