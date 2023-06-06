import React, { useState, useEffect } from "react";
import Box from "@mui/material/Box";
import Link from "@mui/material/Link";
import Toolbar from "./Toolbar";
import AppBar from "./Appbar";
import styled from "styled-components";
import useAuth from "../hook/useAuth";
import Logout from "./Logout/Logout";
import { useRecoilValue } from "recoil";
import { isLoggedInState } from "../recoil/user";

const rightLink = {
  fontSize: 24,
  color: "#000000",
  ml: 3,
};

const NavbarContainer = styled.nav`
  display: ${({ visible }) => (visible ? "" : "none")};
  background: #cee5d0;
  position: fixed;
  top: 0;
  z-index: 10;
  width: 100%;
`;

export default function Navbar() {
  const [prevScrollPos, setPrevScrollPos] = useState(0);
  const [visible, setVisible] = useState(true);
  const [containerTransform, setContainerTransform] = useState("");

  const isLoggedIn = useRecoilValue(isLoggedInState);

  // const { isLoggedIn, login, logout } = useAuth();

  // const handleScroll = () => {
  //   const currentScrollPos = window.pageYOffset;
  //   if (currentScrollPos > 50) {
  //     setVisible(false);
  //   } else {
  //     setVisible(true);
  //   }
  //   setPrevScrollPos(currentScrollPos);
  // };

  // const handleButtonClick = () => {
  //   setContainerTransform("translateY(-100vh)");
  //   console.log("클릭");
  // };

  useEffect(() => {
    let prevScrollPos = window.pageYOffset;

    const handleScroll = () => {
      const currentScrollPos = window.pageYOffset;
      const isScrollingUp = prevScrollPos > currentScrollPos;

      setVisible(isScrollingUp || currentScrollPos < 10);
      prevScrollPos = currentScrollPos;
    };
    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <NavbarContainer visible={visible}>
      {/* <AppBar position="fixed"> */}
      <Toolbar sx={{ justifyContent: "space-between" }}>
        <Link
          variant="h6"
          underline="none"
          color="#000000"
          href="/"
          sx={{ fontSize: 28 }}
        >
          {"복Z세대"}
        </Link>
        <Box
          sx={{
            flex: 1,
            display: "flex",
            justifyContent: "center",
            gap: 10,
          }}
        >
          <Link
            color="inherit"
            variant="h6"
            underline="none"
            href="/search"
            sx={rightLink}
          >
            {"복지 검색 🔍"}
          </Link>
          <Link
            color="inherit"
            variant="h6"
            underline="none"
            href="/map"
            sx={rightLink}
          >
            {"복지 지도 🗺️"}
          </Link>
          <Link
            color="inherit"
            variant="h6"
            underline="none"
            href="/notice"
            sx={rightLink}
          >
            {"공지사항 📍"}
          </Link>
          {isLoggedIn ? (
            <Link
              color="inherit"
              variant="h6"
              underline="none"
              href="/mypage"
              sx={rightLink}
            >
              {"마이페이지 ⭐"}
            </Link>
          ) : (
            ""
          )}
        </Box>
        {isLoggedIn ? (
          <Logout />
        ) : (
          <Box
            sx={{
              justifyContent: "flex-end",
              gap: 5,
            }}
          >
            <Link
              color="#000000"
              variant="h6"
              underline="none"
              href="/signin"
              sx={{ marginRight: 5 }}
            >
              {"로그인"}
            </Link>
            <Link
              color="#000000"
              variant="h6"
              underline="none"
              href="/signup"
              sx={{ rightLink }}
            >
              {"회원가입"}
            </Link>
          </Box>
        )}
      </Toolbar>
      {/* </AppBar> */}
    </NavbarContainer>
  );
}
