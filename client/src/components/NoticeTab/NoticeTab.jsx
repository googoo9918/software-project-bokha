import React, { useEffect, useState } from "react";
import {
  NoticeContainer,
  NoticeDate,
  NoticeTabView,
  NoticeTitle,
} from "./NoticeTab.styled";
import axios from "axios";

export default function NoticeTab() {
  const [noticeList, setNoticeList] = useState([]);

  useEffect(() => {
    axios.get(`/data/notice.json`).then((res) => setNoticeList(res.data.items));
  }, []);

  return (
    <NoticeContainer>
      {noticeList.map(
        (notice, i) => (
          <NoticeBox notice={notice} key={i} />
        )
        // console.log(notice)
      )}
    </NoticeContainer>
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
