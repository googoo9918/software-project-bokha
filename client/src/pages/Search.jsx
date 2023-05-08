import React from "react";
// import Container from "@mui/material/Container";
// import Box from "@mui/material/Box";
import Sidebar from "../components/Sidebar";
import SearchPage from "./SearchPage";
// import CategoryBox from "../components/CategoryBox/CategoryBox";
// import EtcBox from "../components/EtcBox/EtcBox";
// import Paper from "../components/Paper";

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
      <SearchPage />
    </>
  );
}
