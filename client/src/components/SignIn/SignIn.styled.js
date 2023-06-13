import styled from "styled-components";

export const SignInContainer = styled.div`
  position: absolute;
  top: 150px;
  left: 490px;
  // width: 100%;
  // height: 100vh;
`;

export const SignInForm = styled.form`
  display: flex;
  flex-direction: column;
  width: 30vw;
`;

export const InputBox = styled.input`
  border: 2px solid lightgray;
  border-radius: 5px;
  height: 30px;
  padding: 10px;
  margin: 10px 0;

  &:focus {
    border-color: #1b5e20;
    outline: none;
  }
`;
