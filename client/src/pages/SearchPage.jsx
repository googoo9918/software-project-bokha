import CategoryBox from "../components/CategoryBox/CategoryBox";
import { useState } from "react";

function SearchPage() {
  const [household, setHousehold] = useState([]);
  const [interest, setInterest] = useState([]);

  const householdList = ['저소득', '장애인', '한부모·조손', '다자녀', '다문화·탈북민', '보훈대상자'];
  const interestList = ['신체건강', '정신건강', '생활지원', '주거', 
                    '일자리', '문화·여가', '안전·위기', '보육', 
                    '교육', '입양·위탁', '보호·돌봄', '서민금융', '법률'];

  return(
    <div id="search-page">
      <CategoryBox 
          title="가구상황"
          buttons={householdList}
          state={household}
          handler={setHousehold}></CategoryBox>
      <CategoryBox 
          title="관심분야"
          buttons={interestList}
          state={interest}
          handler={setInterest}></CategoryBox>
    </div>
  )
}

export default SearchPage;