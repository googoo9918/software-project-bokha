import React from "react";
import Sidebar from "../components/Sidebar";
import SearchContent from "../components/SearchContent/SearchContent";

export default function Search() {
  return (
    <>
      <Sidebar
        items={[
          { title: "복지 검색 🔍" },
          { title: "복지 검색" },
          { title: "음성 검색" },
        ]}
      />
      <SearchContent />
    </>
  );
}
