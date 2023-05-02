import { 
  CategoryBoxContainer,
  CategoryTitle,
  CategoryContent,
  CategoryBtn
} from "./CategoryBox.styled";
import { StyledEngineProvider } from '@mui/styled-engine'

function CategoryBox(props) {
  return(
    <StyledEngineProvider injectFirst>
      <CategoryBoxContainer>
        <CategoryTitle>
          <div id="category-box__title">{props.title}</div>
        </CategoryTitle>
        <CategoryContent>
          {
            props.buttons &&
            (props.buttons).map(btn => (
              <CategoryBtn key={btn.id} >{btn}</CategoryBtn>
            ))
          }
        </CategoryContent>
      </CategoryBoxContainer>
    </StyledEngineProvider>
  )
}

export default CategoryBox;