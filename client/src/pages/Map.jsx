import React, { useState } from "react";
import Sidebar from "../components/Sidebar";
import KakaoMap from "../components/KakaoMap/KakaoMap";
import PublicInstitutions from "../components/PublicInstitutions/PublicInstitutions";

export default function Map() {
  const [selectedItem, setSelectedItem] = useState("복지 시설");
  return (
    <>
      <Sidebar
        items={[
          { title: "복지 지도 🗺️" },
          { title: "복지 시설" },
          { title: "공공 기관" },
        ]}
        setSelectedItem={setSelectedItem}
      />
      {selectedItem === "복지 시설" ? <KakaoMap /> : <PublicInstitutions />}
    </>
  );
}
