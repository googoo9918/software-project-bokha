import React from "react";
import useAuth from "../../hook/useAuth";
import axios from "axios";
import { LogoutButton, LogoutContainer } from "./Logout.styled";

export default function Logout() {
  const { logout } = useAuth();
  const accessToken = localStorage.getItem("accessToken");

  const handleLogout = () => {
    axios.post(
      `${import.meta.env.VITE_APP_HOST}/api/logout`,
      {},
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }
    );
    logout();
  };

  return (
    <LogoutContainer>
      <LogoutButton onClick={handleLogout}>로그아웃</LogoutButton>
    </LogoutContainer>
  );
}
