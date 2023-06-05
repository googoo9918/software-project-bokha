import { SearchContentContainer } from "./SearchContent.styled";
import CategoryContainer from "../CategoryContainer/CategoryContainer";
import ProgramContainer from "../ProgramContainer/ProgramContainer";
import { useState } from "react";

function SearchContent() {
  const [programs, setPrograms] = useState();

  return(
    <SearchContentContainer>
      <CategoryContainer handler={setPrograms} />
      <ProgramContainer programs={programs} />
    </SearchContentContainer>
  )
}

export default SearchContent;