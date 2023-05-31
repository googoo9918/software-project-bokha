import { 
  CardContainer,
  CardHeader,
  CardInfoList,
  CardInfo
} from "./ProgramCard.styled";
import empty_star from "../../assets/empty_star.png";
import { useNavigate } from "react-router-dom";

function ProgramCard(prop) {
  const navigate = useNavigate();

  function goDetail() {
    navigate(`/search/${prop.program.servId}`);
  }

  function reduceLen(len, str) {
    if(str.length >= len) {
      str = str.slice(0, len);
      str += '...';
      return str;
    }
    return str;
  }

  return(
    <CardContainer>
      <CardHeader>
        <div id="program-title">{prop.program.servNm ? reduceLen(6, prop.program.servNm) : '복지 제도 제목'}</div>
        <img id="program-star" src={empty_star} alt="star" />
      </CardHeader>
      <div id="program-detail-short">{prop.program.servDgst ? reduceLen(30, prop.program.servDgst) : '복지 제도에 대한 간략한 정보가 출력됩니다.'}</div>
      <CardInfoList>
        <CardInfo>
          <div className="program-info-entry">지급방법</div>
          <div className="program-info-content">{prop.program.srvPvsnNm ? reduceLen(7, prop.program.srvPvsnNm) : '현금지급'}</div>          
        </CardInfo>
        <CardInfo>
          <div className="program-info-entry">담당부처</div>
          <div className="program-info-content">{prop.program.bizChrDeptNm ? reduceLen(7, prop.program.bizChrDeptNm) : '보건복지부'}</div>          
        </CardInfo>
        <CardInfo>
          <div className="program-info-entry">가구상황</div>
          <div className="program-info-content">{prop.program.lifeNmArray ? reduceLen(7, prop.program.lifeNmArray) : '저소득'}</div>          
        </CardInfo>
        <CardInfo>
          <div className="program-info-entry">관심분야</div>
          <div className="program-info-content">{prop.program.intrsThemaNmArray ? reduceLen(7, prop.program.intrsThemaNmArray) : '신체건강'}</div>          
        </CardInfo>
      </CardInfoList>
      <button id="program-detail-btn" onClick={goDetail}>상세정보</button>
    </CardContainer>
  )
}

export default ProgramCard;