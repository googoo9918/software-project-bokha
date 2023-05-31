import { 
  ProgramWrapper,
  EmptyProgram
} from "./ProgramContainer.styled";
import ProgramCard from "../ProgramCard/ProgramCard";

function ProgramContainer(props) {

  return(
    <ProgramWrapper>
      {
        props.programs &&
        props.programs.map((el, idx) => (
          <ProgramCard program={el} key={idx} />
        ))
      }
      {
        !props.programs &&
        (
          <EmptyProgram>해당 옵션에 해당하는 복지 정보가 없습니다!</EmptyProgram>
        )
      }
    </ProgramWrapper>
  )
}

export default ProgramContainer;