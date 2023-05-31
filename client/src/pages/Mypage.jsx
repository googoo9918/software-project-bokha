import React from "react";
import Sidebar from "../components/Sidebar";

export default function Mypage() {
  return (
    <Sidebar
      items={[
        { title: "마이페이지⭐" },
        { title: "즐겨찾기" },
        { title: "복지 추천" },
      ]}
    />
  );
}
