import React, { useEffect, useState } from "react";
import { KakaoMapTitleBox, KakaoMapView } from "./PublicInstitutions.styled";

export default function PublicInstitutions() {
  const [publicInstitutions, setPublicInstitutions] = useState([]);

  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(37.496, 126.955),
      level: 5,
    };
    const map = new kakao.maps.Map(container, options);
    //여기까지가 지도 띄우기

    var places = new kakao.maps.services.Places();
    var callback = function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        setPublicInstitutions(result);
        result.forEach((institution) => {
          const markerPosition = new window.kakao.maps.LatLng(
            institution.y,
            institution.x
          );
          const marker = new window.kakao.maps.Marker({
            position: markerPosition,
          });
          marker.setMap(map);
        });
      }
    };
    // 공공기관 코드 검색
    places.categorySearch("PO3", callback, {
      // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
      location: new kakao.maps.LatLng(37.496, 126.955),
    });
  }, []);

  return (
    <>
      <KakaoMapTitleBox>
        <h2>공공 기관</h2>
        <p style={{ color: "gray" }}>주변의 공공 기관들을 확인해 보세요!</p>
      </KakaoMapTitleBox>
      <KakaoMapView>
        <div id="map" style={{ width: "900px", height: "550px" }}></div>
      </KakaoMapView>
    </>
  );
}
