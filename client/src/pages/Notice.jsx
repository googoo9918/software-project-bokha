import React, { useState } from "react";
import Sidebar from "../components/Sidebar";
import NoticeTab from "../components/NoticeTab/NoticeTab";
import QnATab from "../components/QnATab/QnATab";

export default function Notice() {
  const [selectedItem, setSelectedItem] = useState("공지사항");
  return (
    <>
      <Sidebar
        items={[
          { title: "공지사항📍" },
          { title: "공지사항" },
          { title: "질의응답" },
        ]}
        setSelectedItem={setSelectedItem}
      />
      {selectedItem === "공지사항" ? <NoticeTab /> : <QnATab />}
    </>
  );
}
