import React, { useEffect, useState } from "react";
import { KakaoMapTitleBox, KakaoMapView } from "./KakaoMap.styled";
import axios from "axios";

const { kakao } = window;

export default function KakaoMap() {
  const [hospital, setHospital] = useState([]);

  useEffect(() => {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(37.497, 126.954),
      level: 3,
    };
    const map = new kakao.maps.Map(container, options);

    var places = new kakao.maps.services.Places();
    var callback = function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        setHospital(result);
        result.forEach((institution) => {
          const markerPosition = new window.kakao.maps.LatLng(
            institution.y,
            institution.x
          );
          const marker = new window.kakao.maps.Marker({
            position: markerPosition,
          });

          // const hospitalAddress = result.address_name;
          // console.log(hospitalAddress);

          var iwContent =
              '<div style="padding:5px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwRemoveable = true;

          // 인포윈도우를 생성합니다
          var infowindow = new kakao.maps.InfoWindow({
            // position: iwPosition,
            content: iwContent,
            removeable: iwRemoveable,
          });

          kakao.maps.event.addListener(marker, "click", function () {
            if (infowindow.getMap()) {
              infowindow.close();
            } else {
              infowindow.open(map, marker);
            }
          });
          marker.setMap(map);
        });
      }
    };
    // 공공기관 코드 검색
    places.categorySearch("HP8", callback, {
      // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
      location: new kakao.maps.LatLng(37.496, 126.955),
    });
  }, []);
  return (
    <>
      <KakaoMapTitleBox>
        <h2>병원 🏥</h2>
        <p style={{ color: "gray" }}>주변의 병원 위치 정보를 확인해 보세요!</p>
        <p style={{ color: "gray" }}>
          마커를 클릭하면 위치 정보를 확인하실 수 있습니다!
        </p>
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
