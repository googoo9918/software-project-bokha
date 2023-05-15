import React from "react";
import Sidebar from "../components/Sidebar";
import KakaoMap from "../components/KakaoMap/KakaoMap";

export default function Map() {
  return (
    <>
      <Sidebar
        items={[
          { title: "복지 지도 🗺️" },
          { title: "복지 시설" },
          { title: "공공 기관" },
        ]}
      />
      <KakaoMap />
    </>
  );
}
