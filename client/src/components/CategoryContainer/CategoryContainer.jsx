import { CategoryWrapper } from "./CategoryContainer.styled";
import CategoryBox from "../CategoryBox/CategoryBox";
import EtcBox from "../EtcBox/EtcBox";
import { useState } from "react";
import TitleBox from "../TitleBox/TitleBox";
import axios from "axios";
import { isLoggedInState } from "../../recoil/user";
import { useRecoilValue } from "recoil";
import useAuth from "../../hook/useAuth";

export default function CategoryContainer(props) {
  const [household, setHousehold] = useState([]);
  const [interest, setInterest] = useState([]);
  const [age, setAge] = useState(50);
  // '시/도', '시/군/구' -> defaultValue
  const [sido, setSido] = useState("서울특별시");
  const [sigungu, setSigungu] = useState("동작구");
  const [keyword, setKeyword] = useState("");
  const [page, setPage] = useState(1);
  const loginState = useRecoilValue(isLoggedInState);
  const { accessToken } = useAuth();
  const headers = loginState ? { Authorization: `Bearer ${accessToken}` } : {};

  const householdCode = {
    "다문화·탈북민": "010",
    "다자녀": "020",
    "보훈대상자": "030",
    "장애인": "040",
    "저소득": "050",
    "한부모·조손": "060"
  };
  
  const interestCode = {
    "신체건강": "010",
    "정신건강": "020",
    "생활지원": "030",
    "주거": "040",
    "일자리": "050",
    "문화·여가": "060",
    "안전·위기": "070",
    "임신·출산": "080",
    "보육": "090",
    "교육": "100",
    "입양·위탁": "110",
    "보호·돌봄": "120",
    "서민금융": "130",
    "법률": "140"
  }

  function submitHandler() {
    axios.get(`${import.meta.env.VITE_APP_HOST}/api/programs/list`, {
      params: {
        serviceKey: `${import.meta.env.VITE_SERVICE_KEY}`,
        pageNo: page,
        numOfRows: 9,
        lifeArray: '005,006',
        trgterIndvdlArray: household.join(','),
        intrsThemaArray: interest.join(','),
        age: (age == 0) ? '' : age,
        ctpvNm: sido,
        searchWrd: keyword
      },
      headers: headers
    }).then((res) => {
      props.handler(res.data.data.servList);
    })
    .catch((err) => console.log(err));
  }

  return (
    <CategoryWrapper>
      <TitleBox title="복지 검색" desc="적절한 옵션을 선택하여 검색해 보세요!" />
      <CategoryBox
        title="가구상황"
        buttons={Object.keys(householdCode)}
        state={household}
        handler={setHousehold}
      ></CategoryBox>
      <CategoryBox
        title="관심분야"
        buttons={Object.keys(interestCode)}
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
