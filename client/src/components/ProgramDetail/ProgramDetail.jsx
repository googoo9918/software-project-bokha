import { 
  ProgramDetailContainer,
  DetailHeader,
  DetailInfoBox,
  DetailInfoLine,
  DetailContent,
  DetailContentBlock
} from "./ProgramDetail.styled";
import { useParams } from 'react-router-dom';
import axios from "axios";
import { useState, useEffect } from "react";

function ProgramDetail () {
  const { programIdx } = useParams();
  const [detailInfo, setDetailInfo] = useState({
    servNm: '복지 제도 제목',
    servDgst: '복지 제도에 대한 세부 내용이 들어갑니다.',
    inqplCtadrList: [
      {
        wlfareInfoReldCn: '전화번호'
      }
    ],
    lifeNmArray: '가구상황',
    intrsThemaNmArray: '관심분야',
    bizChrDeptNm: '담당부처',
    alwServCn: '급여 정보',
    aplyMtdCn: '신청 방법',
    sprtTrgtCn: '지원 대상',
    slctCritCn: '선정 기준',
    basfrmList: [
      {
        wlfareInfoReldCn: 'https://www.naver.com/'
      }
    ]
  });
  
  function getDetail() {
    axios.get(`${import.meta.env.VITE_APP_HOST}/api/programs/detail`,{
      params: {
        serviceKey: `${import.meta.env.VITE_SERVICE_KEY}`,
        servId: programIdx
      }
    }).then((response) => {
      const res = response.data.data;
      console.log(res);
      const obj = {
        servNm: res.servNm,
        servDgst: res.servDgst,
        inqplCtadrList: res.inqplCtadrList,
        lifeNmArray: res.lifeNmArray,
        intrsThemaNmArray: res.intrsThemaNmArray,
        bizChrDeptNm: res.bizChrDeptNm,
        alwServCn: res.alwServCn,
        aplyMtdCn: res.aplyMtdCn,
        sprtTrgtCn: res.sprtTrgtCn,
        slctCritCn: res.slctCritCn,
        basfrmList: res.basfrmList
      }
      setDetailInfo(obj);
    })
    .catch((err) => console.log(err));
  }

  useEffect(() => {
    getDetail();
  }, [])

  function reduceLen(len, str) {
    if(str.length >= len) {
      str = str.slice(0, len);
      str += '...';
      return str;
    }
    return str;
  }

  return(
    <ProgramDetailContainer>
      <DetailHeader>
        <div id="program-title">{detailInfo.servNm}</div>
        <DetailInfoBox>
          <div id="program-info__content">
            <DetailInfoLine id="program-info__inquiries-line">
              <div className="program-info__content-title" id="program-info__inquiries">문의처</div>
              <div>{detailInfo.inqplCtadrList[0].wlfareInfoReldCn}</div>
            </DetailInfoLine>
            <DetailInfoLine>
              <div className="program-info__content-title">가구상황</div>
              <div>{reduceLen(10, detailInfo.lifeNmArray)}</div>
            </DetailInfoLine>
            <DetailInfoLine>
              <div className="program-info__content-title">담당부처</div>
              <div>{detailInfo.bizChrDeptNm}</div>
            </DetailInfoLine>
            <DetailInfoLine>
              <div className="program-info__content-title">관심분야</div>
              <div>{detailInfo.intrsThemaNmArray}</div>
            </DetailInfoLine>
          </div>
        </DetailInfoBox>
      </DetailHeader>
      <DetailContent>
        {/* {detailInfo.servDgst} */}
        <DetailContentBlock>
          <div className="detail-block__title">✔ 제도 정보</div>
          <div className="detail-block__desc">{detailInfo.servDgst}</div>
        </DetailContentBlock>
        <DetailContentBlock>
          <div className="detail-block__title">✔ 급여 정보</div>
          <div className="detail-block__desc">{detailInfo.alwServCn}</div>
        </DetailContentBlock>
        <DetailContentBlock>
          <div className="detail-block__title">✔ 신청 방법</div>
          <div className="detail-block__desc">{detailInfo.aplyMtdCn}</div>
        </DetailContentBlock>
        <DetailContentBlock>
          <div className="detail-block__title">✔ 지원 대상</div>
          <div className="detail-block__desc">{detailInfo.sprtTrgtCn}</div>
        </DetailContentBlock>
        <DetailContentBlock>
          <div className="detail-block__title">✔ 선정 기준</div>
          <div className="detail-block__desc">{detailInfo.slctCritCn}</div>
        </DetailContentBlock>
        <DetailContentBlock>
          <div className="detail-block__title" 
            id="detail-block__title-link"
            onClick={() => window.open(detailInfo.basfrmList[0].wlfareInfoReldCn, "_blank")}>✔ 상세 파일 다운로드</div>
        </DetailContentBlock>
      </DetailContent>
    </ProgramDetailContainer>
  )
}

export default ProgramDetail;