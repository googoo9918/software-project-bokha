import React, { useEffect, useState } from "react";
import { KakaoMapView } from "./KakaoMap.styled";
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
    <KakaoMapView>
      <div id="map" style={{ width: "900px", height: "550px" }}></div>
    </KakaoMapView>
  );
}
