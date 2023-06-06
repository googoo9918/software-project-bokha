import { 
  VoiceSearchContainer,
  VoiceSearchBox,
  VoiceCircle,
  VoiceBtnContainer,
  VoiceBtn
} from "./VoiceSearch.styled";
import microphone from '../../assets/microphone.png';
import { useEffect } from "react";
import axios from "axios";

function VoiceSearch() {
  let mediaStream = null;
  let chunks = [];
  let blob = new Blob();

  navigator.mediaDevices.getUserMedia({ audio: true })
    .then(function(stream) {
      const startBtn = document.getElementById('voice-btn__start');
      const endBtn = document.getElementById('voice-btn__end');
      mediaStream = stream;
      const mediaRecorder = new MediaRecorder(stream);

      mediaRecorder.ondataavailable = function(e) {
        // chunks.push(e.data);
        blob = e.data;
        console.log(blob);
      };

      startBtn.addEventListener('click', () => {
        chunks = [];
        mediaRecorder.start();
      })

      endBtn.addEventListener('click', () => {
        mediaRecorder.stop();
        if(mediaStream) {
          mediaStream.getTracks().forEach(track => track.stop())
        }
        
        // console.log(chunks);
        
        // blob = new Blob(chunks[0], { 'type' : 'audio/webm' });
        // console.log(blob);

        console.log(blob);

        // const formData = new FormData();
        // formData.append('file', blob, 'audio.wav');

        // axios.post(`${import.meta.env.VITE_APP_HOST}/api/programs/searchByVoice`, formData)
        // .then((res) => console.log(res))
        // .catch((err) => console.log(err));

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