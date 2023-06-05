import { 
  VoiceSearchContainer,
  VoiceSearchBox,
  VoiceCircle,
  VoiceBtnContainer,
  VoiceBtn
} from "./VoiceSearch.styled";
import microphone from '../../assets/microphone.png';
import { useEffect } from "react";

function VoiceSearch() {
  let chunks = [];
  let audioURL;

  navigator.mediaDevices.getUserMedia({ audio: true })
    .then(function(stream) {
      const startBtn = document.getElementById('voice-btn__start');
      const endBtn = document.getElementById('voice-btn__end');
      const mediaRecorder = new MediaRecorder(stream);

      mediaRecorder.ondataavailable = function(e) {
        chunks.push(e.data);
      };
    
      mediaRecorder.onstop = function(e) {
        console.log(chunks);
        const blob = new Blob(chunks, { 'type' : 'audio/wav; codecs=1' });
        chunks = [];
        audioURL = window.URL.createObjectURL(blob);
        console.log('blob: '+blob.size);
      };

      startBtn.addEventListener('click', () => {
        mediaRecorder.start();
      })

      endBtn.addEventListener('click', () => {
        mediaRecorder.stop();
      })

    })
    .catch(function(err) {
        console.log('The following error occurred: ' + err);
    });

  return(
    <VoiceSearchContainer>
      <VoiceSearchBox>
        <VoiceCircle>
          <img src={microphone} alt="microphone_icon" id="voice-search__icon" />
        </VoiceCircle>
        <VoiceBtnContainer>
          <VoiceBtn id="voice-btn__start" >음성 인식</VoiceBtn>
          <VoiceBtn id="voice-btn__end" className="end-btn">종료 후 검색</VoiceBtn>
        </VoiceBtnContainer>
      </VoiceSearchBox>
    </VoiceSearchContainer>
  )
}

export default VoiceSearch;