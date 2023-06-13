import React from "react";
import Sidebar from "../components/Sidebar";
import SearchContent from "../components/SearchContent/SearchContent";
import VoiceSearch from "../components/VoiceSearch/VoiceSearch";
import { useState } from "react";

export default function Search() {
  const [selectedItem, setSelectedItem] = useState("복지 검색");

  return (
    <>
      <Sidebar
        items={[
          { title: "복지 검색 🔍" },
          { title: "복지 검색" },
          { title: "음성 검색" },
        ]}
        setSelectedItem={setSelectedItem}
      />
      { selectedItem === "복지 검색" ? <SearchContent /> : <VoiceSearch /> }
    </>
  );
}
