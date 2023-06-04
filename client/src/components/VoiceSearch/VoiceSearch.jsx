import { 
  VoiceSearchContainer,
  VoiceSearchBox,
  VoiceCircle,
  VoiceBtnContainer,
  VoiceBtn
} from "./VoiceSearch.styled";
import microphone from '../../assets/microphone.png';

function VoiceSearch() {
  return(
    <VoiceSearchContainer>
      <VoiceSearchBox>
        <VoiceCircle>
          <img src={microphone} alt="microphone_icon" id="voice-search__icon" />
        </VoiceCircle>
        <VoiceBtnContainer>
          <VoiceBtn>음성 인식</VoiceBtn>
          <VoiceBtn className="end-btn">종료 후 검색</VoiceBtn>
        </VoiceBtnContainer>
      </VoiceSearchBox>
    </VoiceSearchContainer>
  )
}

export default VoiceSearch;