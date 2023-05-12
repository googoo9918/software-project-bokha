import React from "react";
import Sidebar from "../components/Sidebar";
import NoticeTab from "../components/NoticeTab/NoticeTab";

export default function Notice() {
  return (
    <>
      <Sidebar
        items={[
          { title: "공지사항📍" },
          { title: "공지사항" },
          { title: "질의응답" },
        ]}
      />
      <NoticeTab />
    </>
  );
}
