import React, { useState } from "react";
import Sidebar from "../components/Sidebar";
import KakaoMap from "../components/KakaoMap/KakaoMap";
import PublicInstitutions from "../components/PublicInstitutions/PublicInstitutions";

export default function Map() {
  const [selectedItem, setSelectedItem] = useState("병원 🏥");
  return (
    <>
      <Sidebar
        items={[
          { title: "복지 지도 🗺️" },
          { title: "병원 🏥" },
          { title: "공공 기관 🏢" },
        ]}
        setSelectedItem={setSelectedItem}
      />
      {selectedItem === "병원 🏥" ? <KakaoMap /> : <PublicInstitutions />}
    </>
  );
}
