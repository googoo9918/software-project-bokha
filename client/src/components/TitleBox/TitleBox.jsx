import { TitleBoxContainer } from "./TitleBox.styled";

function TitleBox(props) {
  const { title, desc } = props;

  return(
    <TitleBoxContainer>
      <div id="title-box__title">{title}</div>
      <div id="title-box__desc">{desc}</div>
    </TitleBoxContainer>
  )
}

export default TitleBox;