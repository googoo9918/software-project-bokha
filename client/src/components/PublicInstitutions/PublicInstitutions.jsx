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

          let iwContent =
          `<div style="width:250px; height:100px; padding:10px;">
            <div style="width:200px; font-weight:600; font-size:20px; margin-bottom:5px;" >${institution.place_name} <br></div>
            <div>주소 : ${institution.address_name}</div>
            <div>문의처 : ${institution.phone}</div>
            <a href="https://map.kakao.com/link/map/${institution.place_name},${institution.y},${institution.x}" style="color:blue" target="_blank">큰지도보기</a> 
            <a href="https://map.kakao.com/link/to/${institution.place_name},${institution.y}, ${institution.x}" style="color:blue" target="_blank">길찾기</a>
          </div>`,
          iwRemoveable = true;

        // 인포윈도우를 생성합니다
        let infowindow = new kakao.maps.InfoWindow({
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
    places.categorySearch("PO3", callback, {
      // Map 객체를 지정하지 않았으므로 좌표객체를 생성하여 넘겨준다.
      location: new kakao.maps.LatLng(37.496, 126.955),
    });
  }, []);

  return (
    <>
      <KakaoMapTitleBox>
        <h2>공공 기관 🏢</h2>
        <p style={{ color: "gray" }}>주변의 공공 기관들을 확인해 보세요!</p>
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
