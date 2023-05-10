import { CategoryWrapper } from "./CategoryContainer.styled";
import CategoryBox from "../CategoryBox/CategoryBox";
import EtcBox from "../EtcBox/EtcBox";
import { useState } from "react";

export default function CategoryContainer() {
  const [household, setHousehold] = useState([]);
  const [interest, setInterest] = useState([]);
  const [age, setAge] = useState(0);
  // '시/도', '시/군/구' -> defaultValue
  const [sido, setSido] = useState("서울특별시");
  const [sigungu, setSigungu] = useState("동작구");
  const [keyword, setKeyword] = useState("");

  const householdList = [
    "저소득",
    "장애인",
    "한부모·조손",
    "다자녀",
    "다문화·탈북민",
    "보훈대상자",
  ];
  const interestList = [
    "신체건강",
    "정신건강",
    "생활지원",
    "주거",
    "일자리",
    "문화·여가",
    "안전·위기",
    "보육",
    "교육",
    "입양·위탁",
    "보호·돌봄",
    "서민금융",
    "법률",
  ];

  function submitHandler() {
    console.log(household);
    console.log(interest);
    console.log(age);
    console.log(sido);
    console.log(sigungu);
    console.log(keyword);
  }

  return (
    <CategoryWrapper>
      <CategoryBox
        title="가구상황"
        buttons={householdList}
        state={household}
        handler={setHousehold}
      ></CategoryBox>
      <CategoryBox
        title="관심분야"
        buttons={interestList}
        state={interest}
        handler={setInterest}
      ></CategoryBox>
      <EtcBox
        ageHandler={setAge}
        sidoHandler={setSido}
        sigunguHandler={setSigungu}
        keywordHandler={setKeyword}
        submitHandler={submitHandler}
      ></EtcBox>
    </CategoryWrapper>
  );
}