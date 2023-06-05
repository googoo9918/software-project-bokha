import React, { useState } from "react";
import { InputBox, SignUpContainer, SignUpForm } from "./SignUp.styled";
import axios from "axios";
import { InputLabel } from "@mui/material";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router-dom";

export default function SignUp() {
  const [Email, setEmail] = useState("");
  const [Name, setName] = useState("");
  const [Password, setPassword] = useState("");
  const [ConfirmPassword, setConfirmPassword] = useState("");
  const [Age, setAge] = useState("");
  const [Region, setRegion] = useState("서울특별시");
  const [button, setButton] = useState(false);

  const navigate = useNavigate();

  const regionList = [
    "서울특별시",
    "부산광역시",
    "대구광역시",
    "인천광역시",
    "광주광역시",
    "대전광역시",
    "울산광역시",
    "세종특별자치시",
    "경기도",
    "강원도",
    "충청북도",
    "충청남도",
    "경상북도",
    "경상남도",
    "전라북도",
    "전라남도",
    "제주특별자치시",
  ];

  const onEmailHandler = (e) => {
    setEmail(e.currentTarget.value);
  };
  const onNameHandler = (e) => {
    setName(e.currentTarget.value);
  };
  const onPasswordHandler = (e) => {
    setPassword(e.currentTarget.value);
  };
  const onConfirmPasswordHandler = (e) => {
    setConfirmPassword(e.currentTarget.value);
  };
  const onAgeHandler = (e) => {
    setAge(e.currentTarget.value);
  };
  const onRegionHandler = (e) => {
    setRegion(e.currentTarget.value);
  };
  const onSubmitHandler = (e) => {
    e.preventDefault();
    if (button === true) {
      if (Password !== ConfirmPassword) {
        return alert("비밀번호와 비밀번호 확인이 같지 않습니다.");
      }

      let body = {
        email: Email,
        memberName: Name,
        password: Password,
        confirmPassword: ConfirmPassword,
        age: Age,
        region: Region,
      };
      console.log(body);

      axios
        .post(`${import.meta.env.VITE_APP_HOST}/api/members/new`, body)
        .then((res) => {
          console.log(res.data);
        });
      navigate("/signin");
    } else {
      alert("빈칸을 채워주세요");
    }
  };

  const changeButton = () => {
    console.log(Email);
    Email.includes("@") &&
    Password.length >= 1 &&
    Age.length >= 1 &&
    Name.length >= 1
      ? setButton(true)
      : setButton(false);
  };

  return (
    <SignUpContainer>
      <SignUpForm onSubmit={onSubmitHandler}>
        <h1
          style={{
            display: "flex",
            justifyContent: "center",
            color: "#1b5e20",
          }}
        >
          회원가입
        </h1>
        <InputBox
          placeholder="성함을 입력해 주세요"
          value={Name}
          onChange={onNameHandler}
        ></InputBox>
        <InputBox
          placeholder="이메일을 입력해 주세요"
          value={Email}
          onChange={onEmailHandler}
        ></InputBox>
        <InputBox
          placeholder="비밀번호를 입력해 주세요"
          type="password"
          value={Password}
          onChange={onPasswordHandler}
        ></InputBox>
        <InputBox
          placeholder="비밀번호를 확인해 주세요"
          type="password"
          value={ConfirmPassword}
          onChange={onConfirmPasswordHandler}
        ></InputBox>
        <InputBox
          placeholder="나이를 입력해 주세요"
          value={Age}
          onChange={onAgeHandler}
        ></InputBox>
        <select
          style={{ height: "30px", marginTop: "5px", borderRadius: "5px" }}
          name="region"
          onChange={onRegionHandler}
        >
          {regionList.map((region) => (
            <option key={region} value={region}>
              {region}
            </option>
          ))}
        </select>
        <br />
        <Button
          type="submit"
          variant="contained"
          color="success"
          onClick={() => {
            changeButton();
          }}
        >
          회원가입
        </Button>
      </SignUpForm>
    </SignUpContainer>
  );
}
