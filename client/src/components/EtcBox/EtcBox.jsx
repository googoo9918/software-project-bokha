import { 
  EtcBoxContainer, 
  EtcTitle,
  EtcContent,
  RegionSelect,
  AgeBox,
  RegionBox,
  KeywordBox
} from "./EtcBox.styled";
import { StyledEngineProvider } from '@mui/styled-engine'
import { 
  FormControl,
  InputLabel,
  MenuItem
} from '@mui/material';

function EtcBox() {
  const sido = ['서울특별시', '부산광역시', '대구광역시', '인천광역시',
                '광주광역시', '대전광역시', '울산광역시', '세종특별자치시',
                '경기도', '강원도', '충청북도', '충청남도', '전라북도',
                '전라남도', '경상북도', '경상남도', '제주특별자치도'];
  const seoulGu = ['종로구', '중구', '용산구', '성동구', '광진구', '동대문구',
                  '중랑구', '성북구', '강북구', '도봉구', '노원구', '은평구',
                  '서대문구', '마포구', '양천구', '강서구', '구로구', '금천구',
                  '영등포구', '동작구', '관악구', '서초구', '강남구', '송파구', '강동구'];
  

  return(
    <StyledEngineProvider injectFirst>
      <EtcBoxContainer>
        <EtcTitle>
          <div id="category-box__title">기타 선택</div>
        </EtcTitle>
        <EtcContent>
          <AgeBox>
            <div className="etc-label">나이</div>
            <div id="etc-age__box">
              <div className="etc-age__text" >만</div>
              <input type="text" id="etc-age__input" placeholder="0"></input>
              <div className="etc-age__text">세</div>
            </div>
          </AgeBox>
          <RegionBox>
            <div className="etc-label">지역</div>
            <FormControl size="small" className="etc-region__form">
              <InputLabel className="etc-region__input-label">시/도</InputLabel>
              <RegionSelect
                label="시/도"
              >
                {
                  sido &&
                  sido.map((el, idx) => (
                    <MenuItem value={idx} key={idx}>{el}</MenuItem>
                  ))
                }
              </RegionSelect>
            </FormControl>
            <FormControl size="small" className="etc-region__form">
              <InputLabel className="etc-region__input-label">시/군/구</InputLabel>
              <RegionSelect
                label="시/군/구"
              >
                {
                  seoulGu &&
                  seoulGu.map((el, idx) => (
                    <MenuItem value={idx} key={idx}>{el}</MenuItem>
                  ))
                }
              </RegionSelect>
            </FormControl>
          </RegionBox>
          <KeywordBox>
            <div className="etc-label">키워드</div>
            <input type="text" 
                id="etc-keyword__input"
                placeholder="검색어를 입력해주세요"></input>
          </KeywordBox>
        </EtcContent>
      </EtcBoxContainer>
    </StyledEngineProvider>
  )
}

export default EtcBox;