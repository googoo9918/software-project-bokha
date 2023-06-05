import styled from "styled-components";

export const VoiceSearchContainer = styled.div`
  width: 59.3125rem;
  height: 17.4375rem;
  position: absolute;
  top: 200px;
  left: 400px;
`;

export const VoiceSearchBox = styled.div`
  width: 59.3125rem;
  height: 17.4375rem;
  background-color: #ededed;
  border: 2px dashed #000000;
  border-radius: 20px;
`

export const VoiceCircle = styled.div`
  width: 9.375rem;
  height: 9.375rem;
  background-color: #fff;
  border-radius: 50%;
  position: absolute;
  top: 1.4375rem;
  left: 50%;
  transform: translateX(-50%);

  #voice-search__icon {
    width: 4.375rem;
    height: 4.375rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
`

export const VoiceBtnContainer = styled.div`
  width: 26rem;
  height: 3.0625rem;
  display: flex;
  justify-content: space-between;
  position: absolute;
  bottom: 1.5625rem;
  left: 50%;
  transform: translateX(-50%);

  .end-btn {
    background-color: #cee5d0;
    color: #000;
  }
`

export const VoiceBtn = styled.div`
  width: 12.4375rem;
  height: 3.0625rem;
  background-color: #bebebe;
  color: #fff;
  font-size: 21px;
  font-weight: 700;
  line-height: 3.0625rem;
  text-align: center;
  border-radius: 0.5rem;
`;