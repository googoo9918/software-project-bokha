import React, { useState, useEffect } from "react";
import { useRecoilState } from "recoil";
import { isLoggedInState } from "../recoil/user";

export default function useAuth() {
  const [accessToken, setAccessToken] = useState("");
  const [refreshToken, setRefreshToken] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useRecoilState(isLoggedInState);

  useEffect(() => {
    // Local Storage에서 토큰 가져오기
    const storedAccessToken = localStorage.getItem("accessToken");
    const storedRefreshToken = localStorage.getItem("refreshToken");

    // 가져온 토큰 설정하기
    setAccessToken(storedAccessToken);
    setRefreshToken(storedRefreshToken);

    // 토큰이 있는 경우 로그인 상태로 설정
    if (storedAccessToken && storedRefreshToken) {
      setIsLoggedIn(true);
      console.log("로그인 되었음");
    }
  }, []);

  const login = (accessToken, refreshToken) => {
    // 로그인 시 호출되는 함수
    setAccessToken(accessToken);
    setRefreshToken(refreshToken);
    setIsLoggedIn(true);

    //Local Storage에 토큰 저장
    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);
  };

  const logout = () => {
    // 로그아웃 시 호출되는 함수
    setAccessToken("");
    setRefreshToken("");
    setIsLoggedIn(false);

    //Local Storage에서 토큰 삭제
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
  };

  const handleTokenRefresh = async () => {
    try {
      const response = await axios.post(
        `${import.meta.env.VITE_APP_HOST}/api/access-token/issue`,
        {
          headers: {
            Authorization: `Bearer ${refreshToken}`,
          },
        }
      );

      const { access_token } = response.data;

      // 새로운 access_token으로 상태와 Local Storage 업데이트
      setAccessToken(access_token);
      localStorage.setItem("accessToken", access_token);
    } catch (error) {
      console.error("토큰 재발급 실패:", error);
      logout(); // 토큰 재발급 실패 시 로그아웃 처리
    }
  };

  return { accessToken, refreshToken, login, logout };
}
