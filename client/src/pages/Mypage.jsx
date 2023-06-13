import React, { useState } from "react";
import Sidebar from "../components/Sidebar";
import Favorites from "../components/Favorites/Favorites";
import RecommendWelfare from "../components/RecommendWelfare/RecommendWelfare";

export default function Mypage() {
  const [selectedItem, setSelectedItem] = useState("즐겨찾기");
  return (
    <>
      <Sidebar
        items={[
          { title: "마이페이지 ⭐" },
          { title: "즐겨찾기" },
          { title: "복지 추천" },
        ]}
        setSelectedItem={setSelectedItem}
      />
      {selectedItem === "즐겨찾기" ? <Favorites /> : <RecommendWelfare />}
    </>
  );
}
