import React, { useEffect, useState } from "react";
import { KakaoMapView } from "./KakaoMap.styled";
import axios from "axios";

const { kakao } = window;

export default function KakaoMap() {
  const [welfareFacilities, setWelfareFacilities] = useState([]);

  useEffect(() => {
    axios
      .get("복지 시설 위,경도 받아옴")
      .then((res) => setWelfareFacilities(res.data));
  }, []);

  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(37.496, 126.955),
      level: 3,
    };
    const map = new kakao.maps.Map(container, options);
    //마커가 표시될 위치
    let markerPosition = new kakao.maps.LatLng(37.496, 126.955);

    //마커 생성
    let marker = new kakao.maps.Marker({
      position: markerPosition,
    });

    marker.setMap(map);
  }, []);

  return (
    <KakaoMapView>
      <div id="map" style={{ width: "900px", height: "550px" }}></div>
    </KakaoMapView>
  );

  // render() {
  //     return (
  //       <div id="map" style={{ width: '500px', height: '500px' }}></div>
  //     );
  //   };
}
