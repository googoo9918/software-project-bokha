import React, { useState } from "react";
import { InputBox, SignInContainer, SignInForm } from "./SignIn.styled";
import axios from "axios";
import Button from "@mui/material/Button";

export default function SignIn() {
  const [Email, setEmail] = useState("");
  const [Password, setPassword] = useState("");
  const [button, setButton] = useState(false);

  const onEmailHandler = (e) => {
    setEmail(e.currentTarget.value);
  };
  const onPasswordHandler = (e) => {
    setPassword(e.currentTarget.value);
  };
  const onSubmitHandler = (e) => {
    e.preventDefault();
    console.log(button);
    if (button === true) {
      let body = {
        email: Email,
        password: Password,
        memberType: "DEFAULT",
      };
      console.log(body);

      // axios
      //   .post(`${import.meta.env.VITE_APP_HOST}/api/members/login`, body)
      //   .then((res) => {
      //     console.log(res.data);
      //   });
    } else {
      alert("빈칸을 채워주세요");
    }
  };

  const changeButton = () => {
    Email.includes("@") && Password.length >= 1
      ? setButton(true)
      : setButton(false);
  };

  return (
    <SignInContainer>
      <SignInForm onSubmit={onSubmitHandler}>
        <h1
          style={{
            display: "flex",
            justifyContent: "center",
            color: "#1b5e20",
          }}
        >
          로그인
        </h1>
        <InputBox
          placeholder="이메일을 입력해 주세요"
          value={Email}
          onChange={onEmailHandler}
          onKeyUp={changeButton}
        ></InputBox>
        <InputBox
          placeholder="비밀번호를 입력해 주세요"
          type="password"
          value={Password}
          onChange={onPasswordHandler}
          onKeyUp={changeButton}
        ></InputBox>
        <br />
        <Button type="submit" variant="contained" color="success">
          로그인
        </Button>
      </SignInForm>
    </SignInContainer>
  );
}
