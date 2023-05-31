import React, { useEffect, useState } from "react";
import {
  NoticeContainer,
  NoticeDate,
  NoticeTabView,
  NoticeTitle,
  NoticeTitleBox,
} from "./NoticeTab.styled";
import axios from "axios";

export default function NoticeTab() {
  const [noticeList, setNoticeList] = useState([]);

  useEffect(() => {
    axios.get(`/data/notice.json`).then((res) => setNoticeList(res.data.items));
  }, []);

  return (
    <>
      <NoticeTitleBox>
        <h1>공지사항</h1>
      </NoticeTitleBox>
      <NoticeContainer>
        {noticeList.map((notice, i) => (
          <NoticeBox notice={notice} key={i} />
        ))}
      </NoticeContainer>
    </>
  );
}

function NoticeBox(props) {
  const { title, publishedAt } = props.notice.snippet;
  return (
    <NoticeTabView>
      <NoticeTitle>{title}</NoticeTitle>
      <span style={{ color: "blue" }}>등록일</span>
      <NoticeDate>{publishedAt}</NoticeDate>
    </NoticeTabView>
  );
}
