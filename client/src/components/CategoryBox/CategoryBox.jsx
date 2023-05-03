import { 
  CategoryBoxContainer,
  CategoryTitle,
  CategoryContent,
  CategoryBtn
} from "./CategoryBox.styled";
import { StyledEngineProvider } from '@mui/styled-engine'
import { useState } from "react";

function CategoryBox(props) {
  function btnClick(e) {
    if((e.target.classList).contains('active')){
      (e.target.classList).remove('active');
      const tempList = props.state.filter(el => el !== (e.target.innerText));
      props.handler(tempList);
      return;
    }

    e.target.classList.add('active');
    props.handler([e.target.innerText, ...props.state]);
    return;
  }

  return(
    <StyledEngineProvider injectFirst>
      <CategoryBoxContainer>
        <CategoryTitle>
          <div id="category-box__title">{props.title}</div>
        </CategoryTitle>
        <CategoryContent>
          {
            props.buttons &&
            (props.buttons).map((btn, idx) => (
              <CategoryBtn 
                key={idx} 
                onClick={btnClick}
                className="category-btn">{btn}</CategoryBtn>
            ))
          }
        </CategoryContent>
      </CategoryBoxContainer>
    </StyledEngineProvider>
  )
}

export default CategoryBox;