import React, { useEffect, useState } from "react";
import { KakaoMapTitleBox, KakaoMapView } from "./KakaoMap.styled";
import axios from "axios";

const { kakao } = window;

export default function KakaoMap() {
  const [welfareFacilities, setWelfareFacilities] = useState([]);

  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(37.496, 126.955),
      level: 5,
    };
    const map = new kakao.maps.Map(container, options);
  }, []);
  return (
    <>
      <KakaoMapTitleBox>
        <h2>복지 시설</h2>
        <p style={{ color: "gray" }}>주변의 복지 시설들을 확인해 보세요!</p>
      </KakaoMapTitleBox>
      <KakaoMapView>
        <div
          id="map"
          style={{
            width: "900px",
            height: "550px",
            border: "2px solid #cee5d0",
          }}
        ></div>
      </KakaoMapView>
    </>
  );
}
