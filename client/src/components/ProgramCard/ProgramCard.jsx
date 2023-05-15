import { 
  CardContainer,
  CardHeader,
  CardInfoList,
  CardInfo
} from "./ProgramCard.styled";
import empty_star from "../../assets/empty_star.png";
import { useNavigate } from "react-router-dom";

function ProgramCard() {
  const navigate = useNavigate();

  // api 나왔을 때 변경이 쉽도록 데이터 동적 반영
  const props = {
    title: '복지 제도 제목',
    detail: '복지 제도에 대한 간략한 정보가 출력됩니다!',
    inquiries: '1577-6635',
    department: '보건복지부',
    household: '저소득',
    interest: '신체건강',
    programIdx: 1
  };

  function goDetail() {
    navigate(`/search/${props.programIdx}`);
  }

  return(
    <CardContainer>
      <CardHeader>
        <div id="program-title">{props.title}</div>
        <img id="program-star" src={empty_star} alt="star" />
      </CardHeader>
      <div id="program-detail-short">{props.detail}</div>
      <CardInfoList>
        <CardInfo>
          <div className="program-info-entry">문의처</div>
          <div className="program-info-content">{props.inquiries}</div>          
        </CardInfo>
        <CardInfo>
          <div className="program-info-entry">담당부처</div>
          <div className="program-info-content">{props.department}</div>          
        </CardInfo>
        <CardInfo>
          <div className="program-info-entry">가구상황</div>
          <div className="program-info-content">{props.household}</div>          
        </CardInfo>
        <CardInfo>
          <div className="program-info-entry">관심분야</div>
          <div className="program-info-content">{props.interest}</div>          
        </CardInfo>
      </CardInfoList>
      <button id="program-detail-btn" onClick={goDetail}>상세정보</button>
    </CardContainer>
  )
}

export default ProgramCard;