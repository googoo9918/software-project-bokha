import { SearchContentContainer } from "./SearchContent.styled";
import CategoryContainer from "../CategoryContainer/CategoryContainer";
import ProgramContainer from "../ProgramContainer/ProgramContainer";
import { useState } from "react";

function SearchContent() {
  // const [programs, setPrograms] = useState([{
  //   program: {
  //     servNm: '복지제도 제목',
  //     servDgst: '복지제도에 대한 간략한 정보가 들어갑니다.',
  //     srvPvsnNm: '현금지급',
  //     bizChrDeptNm: '보건복지부',
  //     intrsThemaNmArray: '신체건강',
  //     lifeNmArray: '저소득',
  //     servId: 'WLF00002235'
  //   }
  // }]);
  const [programs, setPrograms] = useState();

  return(
    <SearchContentContainer>
      <CategoryContainer handler={setPrograms} />
      <ProgramContainer programs={programs} />
    </SearchContentContainer>
  )
}

export default SearchContent;