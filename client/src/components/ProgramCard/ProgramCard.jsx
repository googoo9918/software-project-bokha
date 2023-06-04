import { 
  CardContainer,
  CardHeader,
  CardInfoList,
  CardInfo
} from "./ProgramCard.styled";
import empty_star from "../../assets/empty_star.png";
import filled_star from '../../assets/filled_star.png';
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import { isLoggedInState } from "../../recoil/user";
import { useRecoilValue } from "recoil";
import useAuth from "../../hook/useAuth";

function ProgramCard(prop) {
  const [likedStar, setLikedStar] = useState(false);
  const loginState = useRecoilValue(isLoggedInState);
  const { accessToken } = useAuth();
  const navigate = useNavigate();

  console.log(prop.program);

  useEffect(() => {
    if(prop.program.liked) {
      selectStar(prop.program.liked);
    }
  }, [])

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

  function selectStar(liked) {
    if(liked == true) {
      setLikedStar(true);
      return;
    }

    if(liked == false){
      setLikedStar(false);
      return;
    }
  }

  function toggleLiked() {
    if(likedStar == true) {
      setLikedStar(false);
      // 즐겨찾기 해제 로직
      return;
    }

    if(likedStar == false) {
      if(loginState) {
        // 즐겨찾기 등록 로직
        const pr = prop.program;

        const reqBody = {
          servId: pr.servId,
          servNm: pr.servNm,
          servDgst: pr.servDgst,
          sprtCycnm: pr.sprtCycNm,
          srvPvsnNm: pr.srvPvsnNm,
          aplyMtdNm: pr.aplyMtdNm,
          ctpvNm: pr.ctpvNm
        }

        console.log(reqBody);

        axios.post(`${import.meta.env.VITE_APP_HOST}/api/programs/save`, reqBody, {
          headers: {
            Authorization: `Bearer ${accessToken}`
          }
        }).then((res) => {
          console.log(res);
          setLikedStar(true);
        }).catch((err) => {
          console.log(err);
        });
        return;
      }
    }
  }

  return(
    <CardContainer>
      <CardHeader>
        <div id="program-title">{prop.program.servNm ? reduceLen(6, prop.program.servNm) : '복지 제도 제목'}</div>
        <img id="program-star" 
            src={likedStar ? filled_star : empty_star} 
            alt="star"
            onClick={toggleLiked} />
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