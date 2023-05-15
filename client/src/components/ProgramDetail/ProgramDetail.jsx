import { 
  ProgramDetailContainer,
  DetailHeader,
  DetailInfoBox,
  DetailInfoLine,
  DetailContent
} from "./ProgramDetail.styled";

function ProgramDetail () {
  // 나중에 programIdx 로 데이터 받아올듯
  // const { programIdx } = useParams();
  
  const data = {
    title: '복지 제도 제목',
    inquiries: '1577-6635',
    department: '보건복지부',
    organization: '노인정책과',
    household: '저소득',
    interest: '신체건강',
    detail: '복지 제도에 대한 세부 내용이 들어간다.'
  }

  return(
    <ProgramDetailContainer>
      <DetailHeader>
        <div id="program-title">{data.title}</div>
        <DetailInfoBox>
          <div id="program-info__content">
            <DetailInfoLine id="program-info__inquiries-line">
              <div className="program-info__content-title" id="program-info__inquiries">문의처</div>
              <div>{data.inquiries}</div>
            </DetailInfoLine>
            <DetailInfoLine>
              <div className="program-info__content-title">가구상황</div>
              <div>{data.household}</div>
            </DetailInfoLine>
            <DetailInfoLine>
              <div className="program-info__content-title">담당부처</div>
              <div>{data.department} {data.organization}</div>
            </DetailInfoLine>
            <DetailInfoLine>
              <div className="program-info__content-title">관심분야</div>
              <div>{data.interest}</div>
            </DetailInfoLine>
          </div>
        </DetailInfoBox>
      </DetailHeader>
      <DetailContent>
        {data.detail}
      </DetailContent>
    </ProgramDetailContainer>
  )
}

export default ProgramDetail;