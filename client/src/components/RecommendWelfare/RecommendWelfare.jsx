import React, { useEffect, useState } from "react";
import ProgramCard from "../ProgramCard/ProgramCard";
import axios from "axios";
import {
  RecommendWelfareTitleBox,
  RecommendWelfareView,
} from "./RecommendWelfare.styled";

export default function RecommendWelfare() {
  const [recommendWelfare, setRecommendWelfare] = useState([]);
  const accessToken = localStorage.getItem("accessToken");

  useEffect(() => {
    axios
      .get(`${import.meta.env.VITE_APP_HOST}/api/programs/recommendlist`, {
        params: {
          serviceKey: `${import.meta.env.VITE_SERVICE_KEY}`,
          pageNo: 1,
          numOfRows: 30,
        },
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((res) => {
        console.log(res.data.data.servList);
        setRecommendWelfare(res.data.data.servList);
      });
  }, []);

  return (
    <>
      <RecommendWelfareTitleBox>
        <h2>복지 추천 목록</h2>
        <p>맞춤형 복지 추천 서비스 입니다!</p>
      </RecommendWelfareTitleBox>
      <RecommendWelfareView>
        {recommendWelfare.map((el, idx) => (
          <ProgramCard program={el} key={idx} />
        ))}
      </RecommendWelfareView>
    </>
  );
}
